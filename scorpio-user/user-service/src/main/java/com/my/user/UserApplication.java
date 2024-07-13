package com.my.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;
import tk.mybatis.spring.annotation.MapperScan;

@EnableAsync(proxyTargetClass = true)
@EnableDiscoveryClient
@MapperScan(value = {"com.my.user.mapper"})
@SpringBootApplication(scanBasePackages = {"com.my.common", "com.my.user"})
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}