package com.my.security.admin;

import com.my.core.assertions.ServerAssert;
import com.my.core.constant.Operator;
import com.my.core.error.ErrorCode;
import com.my.core.util.JwtTokenUtil;
import com.my.security.SecurityService;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service("AdminServiceImpl")
public class AdminServiceImpl implements UserDetailsService, SecurityService {

    // jwt secretKey
    @Value("${jwt-secretKey}")
    private String KEY;

    protected static final Map<String, String> admin_user = Collections.singletonMap("sys_admin", "pgtalk168");

    @Override
    public UserDetails getUserByUsername(String username) {
        return new AdminDetails(username, admin_user.get(username));
    }

    @Override
    public String login(String username, String password) {
        UserDetails userDetails = getUserByUsername(username);
        ServerAssert.notNull(userDetails, ErrorCode.ACCOUNT_NOT_FOUND);
        ServerAssert.isTrue(userDetails.getPassword().equals(password), ErrorCode.PWD_ERROR);
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return generateToken(userDetails.getUsername());
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
        return JwtTokenUtil.generateToken(KEY, claims, JwtTokenUtil.generateExpirationDate(Calendar.HOUR, 1));
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


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = getUserByUsername(username);
        if (userDetails != null) {
            return userDetails;
        }
        throw new UsernameNotFoundException("Can not found Username");
    }
}
