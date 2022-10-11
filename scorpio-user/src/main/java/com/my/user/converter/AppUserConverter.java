package com.my.user.converter;

import com.my.core.util.MD5Util;
import com.my.resource.entity.app_user.AppUserRequest;
import com.my.resource.entity.app_user.AppUserResponse;
import com.my.resource.generator.entity.OrmUser;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class AppUserConverter {

    private AppUserConverter() {

    }

    public static OrmUser toOrmUser(AppUserRequest req) {
        OrmUser user = new OrmUser();
        user.setUuid(MD5Util.encrypt(UUID.randomUUID().toString()));
        user.setName(req.getName());
        user.setPassword(MD5Util.encrypt(req.getPwd()));
        user.setDisplayname(req.getDisplayname());
        user.setGender(req.getGender());
        user.setEmail(req.getEmail());

        return user;
    }

    public static AppUserResponse toAppUserResponse(OrmUser user) {
        AppUserResponse res = new AppUserResponse();
        res.setId(user.getUserId());
        res.setName(user.getName());
        res.setDisplayname(user.getDisplayname());
        String gender = (user.getGender() == 0) ? "female" : (user.getGender() == 1 ? "male" : "other");
        res.setGender(gender);
        res.setEmail(user.getEmail());

        return res;
    }

    public static List<AppUserResponse> toAppUserResponses(List<OrmUser> users) {
        return users.stream()
                .map(AppUserConverter::toAppUserResponse)
                .collect(Collectors.toList());
    }
}
