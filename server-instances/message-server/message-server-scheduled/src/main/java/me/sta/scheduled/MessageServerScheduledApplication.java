package me.sta.scheduled;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableEurekaClient
@EnableFeignClients
@EnableCircuitBreaker
@EnableAutoConfiguration
@ComponentScan("me.sta")
public class MessageServerScheduledApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessageServerScheduledApplication.class, args);
    }

}
