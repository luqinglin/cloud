package me.sta;

import me.rule.MyRule;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


@EnableFeignClients
@EnableEurekaClient
@EnableCircuitBreaker
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan("me.sta")
@MapperScan("me.sta.dao")
//@RibbonClient(name = "helloServer",configuration = MyRule.class)
public class HelloConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloConsumerApplication.class, args);
	}
}
