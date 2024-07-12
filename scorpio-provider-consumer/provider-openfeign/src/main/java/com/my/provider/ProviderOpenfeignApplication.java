package com.my.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.my.common", "com.my.provider"})
public class ProviderOpenfeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderOpenfeignApplication.class, args);
    }

}