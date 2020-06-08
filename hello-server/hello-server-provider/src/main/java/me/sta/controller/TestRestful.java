package me.sta.controller;

import me.sta.helloservice.ServiceApi;
import me.sta.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class TestRestful implements ServiceApi {

    @Value("${form}")
    private String form;
    @Value("${form1}")
    private String form1;
    @Value("${server.port:8080}")
    private String port;
    @Autowired
    private TestService testService;

    @Override
    public String home(String username,String passwd){
        String s = "{\"id\":"+form+",\"form1\":"+form1+","+" port:"+ port+"}";
        return s;
    }
    @Override
    public String home1(String username,String passwd){
        System.out.println(username);
        System.out.println(passwd);
        String s = "{\"id\":1}";
        return s;
    }

    @Override
    public int save(String name,Integer userId) {
        return testService.save(name,userId);
    }


}