package com.my.user;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.my")
@MapperScan(basePackages ="com.my.resource.generator.mapper")
public class UserApplication {
	private static final Logger LOG = LoggerFactory.getLogger(UserApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
		LOG.info("UserApplication 啟動成功");
	}

}
