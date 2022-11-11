package com.my.security.user;

import com.my.core.assertions.ServerAssert;
import com.my.core.constant.Operator;
import com.my.core.error.ErrorCode;
import com.my.core.util.JwtTokenUtil;
import com.my.core.util.MD5Util;
import com.my.resource.generator.entity.OrmUser;
import com.my.resource.generator.service.AppUserService;
import com.my.security.SecurityService;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service("UserServiceImpl")
public class UserServiceImpl implements UserDetailsService, SecurityService {

    // jwt secretKey
//    @Value("${jwt-secretKey}")
    private final String KEY="3PTR9q7D@zgxm4bXRZmev#Rk2VBUH3g3";


    private final AppUserService appUserService;

    @Autowired
    public UserServiceImpl(AppUserService appUserService) {
        this.appUserService = appUserService;
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
        return generateToken(userDetails.getUsername());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = getUserByUsername(username);
        if (userDetails != null) {
            return userDetails;
        }
        throw new UsernameNotFoundException("Can not found Username");
    }

    @Override
    public Boolean validateToken(String token, UserDetails userDetails) {
        String username = getUserNameFromToken(token);
        return username.equals(userDetails.getUsername()) && JwtTokenUtil.isTokenExpired(KEY, token);
    }


    @Override
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(Claims.SUBJECT, username);
        claims.put(Claims.ISSUER, Operator.SYSTEM.name());
        claims.put("created", new Date());
        return JwtTokenUtil.generateToken(KEY, claims, JwtTokenUtil.generateExpirationDate(Calendar.DATE, 365));
    }

    @Override
    public String generateToken(String username, Date expireTime) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(Claims.SUBJECT, username);
        claims.put(Claims.ISSUER, Operator.SYSTEM.name());
        claims.put(Claims.EXPIRATION, expireTime);
        claims.put("created", new Date());
        return JwtTokenUtil.generateToken(KEY, claims, expireTime);
    }

    @Override
    public String getUserNameFromToken(String token) {
        String username;
        try {
            Claims claims = JwtTokenUtil.getClaimsFromToken(KEY, token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }
}
