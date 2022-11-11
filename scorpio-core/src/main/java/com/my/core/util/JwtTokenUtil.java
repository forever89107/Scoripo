package com.my.core.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * JwtToken Util
 */
@SuppressWarnings("unused")
public abstract class JwtTokenUtil {

    private static final String CLAIM_KEY_CREATED = "created";

    /**
     * generate token
     */
    public static String generateToken(String key, Map<String, Object> claims, Date expireTime) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(expireTime)
                .signWith(Keys.hmacShaKeyFor(key.getBytes()))
                .compact();
    }

    /**
     * Get Claims
     */
    public static Claims getClaimsFromToken(String key, String token) {
        JwtParser parser = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(key.getBytes()))
                .build();

        return parser
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * generate expire time
     */
    public static Date generateExpirationDate(int field, int value) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(field, value);
        return calendar.getTime();
    }


    /**
     * is token  Expired
     */
    public static boolean isTokenExpired(String key, String token) {
        Date expiredDate = getExpiredDateFromToken(key, token);
        return !expiredDate.before(new Date());
    }

    /**
     * Get Expired time from token
     */
    public static Date getExpiredDateFromToken(String key, String token) {
        Claims claims = getClaimsFromToken(key, token);
        return claims.getExpiration();
    }


    /**
     * is token can be refresh
     */
    public static boolean canRefresh(String key, String token) {
        return isTokenExpired(key, token);
    }

    /**
     * refresh token
     */
    public static String refreshToken(String key, String token) {
        Claims claims = getClaimsFromToken(key, token);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(key, claims, generateExpirationDate(Calendar.DATE, 7));
    }


}
