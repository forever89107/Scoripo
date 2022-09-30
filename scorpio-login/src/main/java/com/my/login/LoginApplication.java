package com.my.login;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableEncryptableProperties
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.my")
@MapperScan(basePackages ="com.my.resource.generator.mapper")
public class LoginApplication {
	private static final Logger LOG = LoggerFactory.getLogger(LoginApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(LoginApplication.class, args);
		LOG.info("RegisterApplication 啟動成功");
	}

}
