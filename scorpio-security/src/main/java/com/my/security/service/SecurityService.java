package com.my.security.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface SecurityService {

    UserDetails getUserByUsername(String username);

    /**
     * longin by username & password
     */
    String login(String username, String password);
}
