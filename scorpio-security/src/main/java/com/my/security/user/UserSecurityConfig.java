package com.my.security.user;

import com.my.security.component.RestAuthenticationEntryPoint;
import com.my.security.component.RestfulAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;

@Configuration
@Order(2)
public class UserSecurityConfig {

    private final RestfulAccessDeniedHandler restfulAccessDeniedHandler;
    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    private final UserJwtFilter jwtTokenFilter;
    private final UserAuthenticationProvider userAuthenticationProvider;


    @Autowired
    public UserSecurityConfig(UserJwtFilter userJwtFilter,
                              UserAuthenticationProvider userAuthenticationProvider,
                              RestfulAccessDeniedHandler restfulAccessDeniedHandler,
                              RestAuthenticationEntryPoint restAuthenticationEntryPoint) {
        this.jwtTokenFilter = userJwtFilter;
        this.userAuthenticationProvider = userAuthenticationProvider;
        this.restfulAccessDeniedHandler = restfulAccessDeniedHandler;
        this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
    }

    @Bean
    public SecurityFilterChain userFilterChain(HttpSecurity http) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http
                .authorizeRequests();

        http.authenticationProvider(userAuthenticationProvider);
        //允許跨域請求的OPTIONS
        registry.antMatchers(HttpMethod.OPTIONS)
                .permitAll();
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                /*.antMatchers().permitAll() login path*/
                .anyRequest().authenticated();
        // 禁用 cache
        http.headers().cacheControl();

        // JWT filter
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        // 自訂義未授權、未登入結果
        http.exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandler)
                .authenticationEntryPoint(restAuthenticationEntryPoint);
        // cors security
        http.cors().configurationSource(userCorsConfigurationSource());
        return http.build();
    }


    @Bean
    CorsConfigurationSource userCorsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedHeaders(Collections.singletonList("*"));
        configuration.setAllowedMethods(Collections.singletonList("*"));
        configuration.setAllowedOrigins(Collections.singletonList("*"));
        configuration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


}
