package com.my.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(scanBasePackages = {"com.my.common", "com.my.consumer"})
@EnableFeignClients
@EnableDiscoveryClient
public class ConsumerOpenfeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerOpenfeignApplication.class, args);
    }

}