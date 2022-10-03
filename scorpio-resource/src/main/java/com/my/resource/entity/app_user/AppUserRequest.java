package com.my.resource.entity.app_user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppUserRequest {
    private String name;
    private String username;
    private String pwd;
    private String displayname;
    private Integer gender;
    private String email;
}
