package com.my.user.controller;


import com.my.core.assertions.ServerAssert;
import com.my.core.error.ErrorCode;
import com.my.core.result.Result;
import com.my.resource.entity.app_user.AppUserRequest;
import com.my.resource.entity.app_user.AppUserResponse;
import com.my.resource.generator.entity.OrmUser;
import com.my.resource.generator.service.AppUserService;
import com.my.user.converter.AppUserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author MBG
 * @since 2022-10-03 02:27:38
 */
@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    private final AppUserService appUserService;

    @Autowired
    public UserController(AppUserService service) {
        this.appUserService = service;
    }


    @PostMapping
    public Result<?> register(@RequestBody AppUserRequest request) {
        int res = appUserService.createUser(request);
        return (res > 0) ? new Result<>() : new Result<>(ErrorCode.INTERNAL_SERVER_ERROR);
    }

    @GetMapping
    public Result<?> getUserById(@RequestParam(value = "id") Integer id) {
        return appUserService.getUserById(id);
    }

    @GetMapping("/username")
    public Result<?> getUserById(@RequestParam(value = "username") String username) {
        OrmUser ormUser = appUserService.getUserByUsername(username);
        ServerAssert.notNull(ormUser, ErrorCode.ACCOUNT_NOT_FOUND);
        AppUserResponse response = AppUserConverter.toAppUserResponse(ormUser);
        return new Result<>(response);
    }

    @GetMapping("/all")
    public Result<?> getAllUser(@RequestParam(value = "page") int page,
                                 @RequestParam(value = "pageSize") int pageSize) {
        return appUserService.getAllUser(page, pageSize);
    }

    @PutMapping
    public Result<?> putUser(@RequestBody AppUserRequest request) {
        int res = appUserService.putUser(request);
        return (res > 0) ? new Result<>() : new Result<>(ErrorCode.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping
    public Result<?> deleteUser(@RequestBody AppUserRequest request) {
        int res = appUserService.deleteById(request);
        return (res > 0) ? new Result<>() : new Result<>(ErrorCode.INTERNAL_SERVER_ERROR);
    }
}

