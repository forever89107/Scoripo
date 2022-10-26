package com.my.security.admin;

import com.my.core.assertions.ServerAssert;
import com.my.core.error.ErrorCode;
import com.my.security.SecurityService;
import com.my.security.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Slf4j
@Service("AdminServiceImpl")
public class AdminServiceImpl implements UserDetailsService, SecurityService {

    protected static final Map<String, String> admin_user = Collections.singletonMap("sys_admin", "@#!123456");

    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public AdminServiceImpl(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public UserDetails getUserByUsername(String username) {
        return new AdminDetails(username, admin_user.get(username));
    }

    @Override
    public String login(String username, String password) {
        UserDetails userDetails = getUserByUsername(username);
        ServerAssert.isTrue(userDetails.getPassword().equals(password), ErrorCode.PWD_ERROR);
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtTokenUtil.generateToken(userDetails);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = getUserByUsername(username);
        if (userDetails != null) {
            return userDetails;
        }
        throw new UsernameNotFoundException("Can not found Username");
    }

    public Boolean isAdmin(String username) {
        return admin_user.containsKey(username);
    }

}
