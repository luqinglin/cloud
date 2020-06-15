package me.sta;

import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @CreateBy admin
 * @CreateTime 2019/7/16
 * @Description
 */
@EnableFeignClients
@EnableEurekaClient
@MapperScan("me.sta.auth.dao")
@SpringBootApplication
public class UserAuthServerApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        try{

            SpringApplication.run(UserAuthServerApplication.class, args);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
