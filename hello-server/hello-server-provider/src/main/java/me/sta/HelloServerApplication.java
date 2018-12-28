package me.sta;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan("me.sta")
@MapperScan("me.sta.dao")
public class HelloServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloServerApplication.class, args);
	}
}
