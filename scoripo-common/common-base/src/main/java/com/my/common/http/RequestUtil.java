package com.my.common.http;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.URL;

@Slf4j
public class RequestUtil {

    @Getter
    private static final RequestUtil instance = new RequestUtil();

    private RequestUtil() {

    }

    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (!StringUtils.hasText(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (!StringUtils.hasText(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (!StringUtils.hasText(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (!StringUtils.hasText(ip) || "unknown".equalsIgnoreCase(ip)) {
            return null;
        }
        if (ip.contains(",")) {
            return ip.split(",")[0].trim();
        } else {
            return ip;
        }
    }

    public String getUrl(HttpServletRequest request) {
        String url = request.getHeader("x-forwarded-host");
        if (!StringUtils.hasText(url)) {
            url = request.getHeader("origin");
        }
        if (!StringUtils.hasText(url)) {
            url = request.getRequestURI();
        }
        if (!StringUtils.hasText(url)) {
            return null;
        }
        if (url.contains("://")) {
            try {
                URL uri = new URL(url);
                url = uri.getHost();
            } catch (Exception ex) {
                log.error("RequestUtil getUrl error", ex);
            }
        }
        return url.toLowerCase();
    }

}