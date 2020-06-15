package me.sta.message;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;
import tk.mybatis.spring.annotation.MapperScan;

@EnableEurekaClient
@MapperScan("me.sta.message.dao")
@SpringBootApplication
public class MessageApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(MessageApplication.class, args);
    }


}
