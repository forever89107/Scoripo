package com.my.openfeign.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.my.common", "com.my.openfeign.provider"})
public class ProviderOpenfeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderOpenfeignApplication.class, args);
    }

}