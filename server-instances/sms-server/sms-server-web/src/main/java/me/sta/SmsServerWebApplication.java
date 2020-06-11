package me.sta;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @CreateBy admin
 * @CreateTime 2019/7/16
 * @Description
 */
@SpringBootApplication
@MapperScan("me.sta.sms.dao")
public class SmsServerWebApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        try {
            SpringApplication.run(SmsServerWebApplication.class, args);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
