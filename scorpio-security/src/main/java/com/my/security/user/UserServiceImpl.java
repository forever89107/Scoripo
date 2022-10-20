package com.my.security.user;

import com.my.core.assertions.ServerAssert;
import com.my.core.error.ErrorCode;
import com.my.core.util.MD5Util;
import com.my.resource.generator.entity.OrmUser;
import com.my.resource.generator.service.AppUserService;
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

@Slf4j
@Service("UserServiceImpl")
public class UserServiceImpl implements UserDetailsService, SecurityService {

    private final AppUserService appUserService;
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public UserServiceImpl(AppUserService appUserService, JwtTokenUtil jwtTokenUtil) {
        this.appUserService = appUserService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public UserDetails getUserByUsername(String username) {
        OrmUser emplBo = appUserService.getUserByUsername(username);
        return new UserDlDetails(emplBo);
    }

    @Override
    public String login(String username, String password) {
        UserDetails userDetails = getUserByUsername(username);
        ServerAssert.notNull(userDetails, ErrorCode.ACCOUNT_NOT_FOUND);
        ServerAssert.isTrue(userDetails.getPassword().equals(MD5Util.encrypt(password)),
                ErrorCode.PWD_ERROR);
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
}
