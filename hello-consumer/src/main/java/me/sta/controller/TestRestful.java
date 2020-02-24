package me.sta.controller;

import me.sta.client.HelloService;
import me.sta.service.IMessageProvider;
import me.sta.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestful {

    @Autowired
    HelloService helloService;
    @Autowired
    IMessageProvider iMessageProvider;
    @Autowired
    TestService testService;

    @RequestMapping(value = "/feign-consumer", method = RequestMethod.GET)
    public String helloConsumer() {


        return helloService.home("1","2");
    }


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public void test(String name,Integer userId) {
         testService.save(name,userId);
    }

    @RequestMapping(value = "/feign-consumer1", method = RequestMethod.GET)
    public String helloConsumer1() {
        iMessageProvider.send("1000");
        return null;
    }
}