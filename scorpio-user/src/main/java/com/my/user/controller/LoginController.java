package com.my.user.controller;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.my.core.assertions.ServerAssert;
import com.my.core.error.ErrorCode;
import com.my.core.result.Result;
import com.my.resource.entity.app_user.AppUserRequest;
import com.my.resource.entity.app_user.AppUserResponse;
import com.my.resource.generator.entity.OrmUser;
import com.my.resource.generator.service.AppUserService;
import com.my.security.service.SecurityService;
import com.my.user.converter.AppUserConverter;
import com.my.user.dto.LoginDto;
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
@RequestMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
public class LoginController {
    private final AppUserService appUserService;
    private final SecurityService securityService;

    @Autowired
    public LoginController(SecurityService securityService, AppUserService service) {
        this.securityService = securityService;
        this.appUserService = service;
    }


    @PostMapping
    public String register(@RequestBody LoginDto dto) {
        String access_token = securityService.login(dto.getUsername(), dto.getPwd());
        JsonObject obj = new JsonObject();
        obj.addProperty("access_token", access_token);

        return new Gson().toJson(obj);
    }
}

