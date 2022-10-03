package com.my.security.auth;


import com.my.security.auth.constant.UserAuthority;

import java.util.List;


public class AbstrUser {

    protected String username;
    protected String password;
    protected List<UserAuthority> authorities;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<UserAuthority> authorities) {
        this.authorities = authorities;
    }

}
