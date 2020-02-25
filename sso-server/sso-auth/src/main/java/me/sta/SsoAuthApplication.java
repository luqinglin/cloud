package me.sta;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer
@EnableEurekaClient
@ComponentScan("me.sta")
@MapperScan("me.sta.dao")
public class SsoAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(SsoAuthApplication.class, args);
	}
}
