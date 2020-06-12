package me.sta.sms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("me.sta")
@MapperScan("me.sta.sms.dao")
@SpringBootApplication
public class SmsServerProviderApplication {
    public static void main(String[] args) {
        try{

            SpringApplication.run(SmsServerProviderApplication.class, args);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
