package me.sta.apollotest;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableApolloConfig(value = "application")
public class ApolloTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApolloTestApplication.class, args);
    }

}
