package me.sta;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.retry.annotation.CircuitBreaker;

//import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @CreateBy admin
 * @CreateTime 2019/7/16
 * @Description
 */
@EnableEurekaClient
@EnableFeignClients
@EnableCircuitBreaker
@EnableAutoConfiguration
@ComponentScan("me.sta")
public class UserServiceApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
}
