package com.my.security.component;

import com.google.gson.Gson;
import com.my.core.result.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * customized to non login or token is expired response
 */
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        Result<?> result = new Result<>();
        result.noPermissionMg();
        response.getWriter().println(new Gson().toJson(result));
        response.getWriter().flush();
    }
}
