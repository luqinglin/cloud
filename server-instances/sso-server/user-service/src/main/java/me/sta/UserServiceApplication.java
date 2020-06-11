package me.sta;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableFeignClients("me.sta")
@EnableDiscoveryClient
@ComponentScan("me.sta")
@MapperScan("me.sta.dao")
@EnableAspectJAutoProxy(exposeProxy = true)
public class UserServiceApplication extends SpringBootServletInitializer{
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
}
