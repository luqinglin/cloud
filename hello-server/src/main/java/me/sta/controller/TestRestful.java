package me.sta.controller;

import me.sta.helloservice.ServiceApi;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestful implements ServiceApi {


    @Override
    public String home(String username,String passwd){
        System.out.println(username);
        System.out.println(passwd);
        String s = "{\"id\":1}";
        return s;
    }
    @Override
    public String home1(String username,String passwd){
        System.out.println(username);
        System.out.println(passwd);
        String s = "{\"id\":1}";
        return s;
    }
}