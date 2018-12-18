package me.sta;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


@EnableFeignClients
@EnableEurekaClient
@EnableCircuitBreaker
@SpringBootApplication
@ComponentScan("me.sta")
@MapperScan("me.sta.dao")
public class HelloConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloConsumerApplication.class, args);
	}
}
