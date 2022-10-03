package com.my.security.config;

import com.my.security.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 員工 UserDetailsService 設定
 */
@Configuration
public class UserConfiguration {
    private final SecurityService securityService;

    @Autowired
    public UserConfiguration(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        //Get Login User Info
        return username -> {
            UserDetails userDetails = securityService.getUserByUsername(username);
            if (userDetails != null) {
                return userDetails;
            }
            throw new UsernameNotFoundException("Can not found Username");
        };
    }
}
