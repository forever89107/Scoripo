package com.my.security;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

public interface SecurityService {
    /**
     * longin by username & password
     */
    String login(String username, String password);

    /**
     * get UserDetails by username
     */
    UserDetails getUserByUsername(String username);

    /**
     * validate token
     */
    Boolean validateToken(String token, UserDetails userDetails);

    /**
     * generate token
     */
    String generateToken(String username);

    String generateToken(String username, Date expireTime);

    /**
     * get UserName from token
     */
    String getUserNameFromToken(String token);
}
