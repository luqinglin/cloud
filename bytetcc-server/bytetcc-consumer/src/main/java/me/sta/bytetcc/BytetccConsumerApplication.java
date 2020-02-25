package me.sta.bytetcc;

import org.bytesoft.bytetcc.supports.springcloud.config.SpringCloudSecondaryConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;


@EnableFeignClients
@EnableEurekaClient
@EnableCircuitBreaker
@SpringBootApplication
@ComponentScan("me.sta")
@MapperScan("me.sta.bytetcc.dao")
@Import(SpringCloudSecondaryConfiguration.class)
@EnableAutoConfiguration(exclude = { MongoAutoConfiguration.class })
public class BytetccConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BytetccConsumerApplication.class, args);
	}
}
