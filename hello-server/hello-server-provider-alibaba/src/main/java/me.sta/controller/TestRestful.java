package me.sta.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import me.sta.helloservice.ServiceApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RefreshScope
public class TestRestful implements ServiceApi {


    @Value("${server.port:8080}")
    private String port;

    @Value("${from:80}")
    private String from;

    @SentinelResource(value="hello",blockHandlerClass = CustomerBlockHandler.class,blockHandler = "homeHandler")
    @Override
    public String home(String username, String passwd) {
        if (username.indexOf("-") != -1) {
            throw new RuntimeException();
        }

        String s = "{port:" + port + ","+from+"}";
        return s;
    }



    public String defaultHandler() {
        return "线程池：  " + Thread.currentThread().getName() + " defaultHandler";
    }

    @Override
    public String home1(String username, String passwd) {
        System.out.println(username);
        System.out.println(passwd);
        String s = "{\"id\":1}";
        return s;
    }

    @Override
    public int save(String name, Integer userId) {
        return 0;
    }


}