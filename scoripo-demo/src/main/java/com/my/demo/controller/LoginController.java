package com.my.demo.controller;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.my.core.assertions.ServerAssert;
import com.my.core.error.ErrorCode;
import com.my.core.util.MD5Util;
import com.my.resource.generator.entity.OrmUser;
import com.my.resource.generator.service.AppUserService;
import com.my.security.SecurityService;
import com.my.user.dto.LoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/_scoripo/login", produces = MediaType.APPLICATION_JSON_VALUE)
public class LoginController {
    private final AppUserService appUserService;
    private final SecurityService securityService;

    @Autowired
    public LoginController(@Qualifier("UserServiceImpl") SecurityService securityService, AppUserService service) {
        this.securityService = securityService;
        this.appUserService = service;
    }


    @PostMapping
    public String login(@RequestBody LoginDto dto) {
        // verify account
        OrmUser ormUser = appUserService.getUserByUsername(dto.getUsername());
        ServerAssert.notNull(ormUser, ErrorCode.ACCOUNT_NOT_FOUND);
        ServerAssert.isTrue(ormUser.getPassword().equals(MD5Util.encrypt(dto.getPwd())), ErrorCode.PWD_ERROR);
        // issue token
        String access_token = securityService.login(dto.getUsername(), dto.getPwd());
        JsonObject obj = new JsonObject();
        obj.addProperty("access_token", access_token);

        return new Gson().toJson(obj);
    }
}

