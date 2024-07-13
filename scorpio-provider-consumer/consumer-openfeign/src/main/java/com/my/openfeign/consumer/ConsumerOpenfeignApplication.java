package com.my.openfeign.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"com.my.common", "com.my.openfeign.consumer"})
@EnableFeignClients
@EnableDiscoveryClient
public class ConsumerOpenfeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerOpenfeignApplication.class, args);
    }

}