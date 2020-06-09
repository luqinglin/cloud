package me.sta.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import me.sta.helloservice.ServiceApi;
import me.sta.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
@DefaultProperties(defaultFallback = "defaultHandler" ,commandProperties = {
        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
})
public class TestRestful implements ServiceApi {

    @Value("${form}")
    private String form;
    @Value("${form1}")
    private String form1;
    @Value("${server.port:8080}")
    private String port;
    @Autowired
    private TestService testService;
    @HystrixCommand(fallbackMethod = "homeHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000"),
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
    })
    @Override
    public String home(String username,String passwd){
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        if (username.indexOf("-")!=-1){
            throw new RuntimeException();
        }

        String s = "{\"id\":"+form+",\"form1\":"+form1+","+" port:"+ port+"}";
        return s;
    }

    public String homeHandler(String username,String passwd) {
        return "线程池：  " + Thread.currentThread().getName() + " homeHandler";
    }

    public String defaultHandler() {
        return "线程池：  " + Thread.currentThread().getName() + " defaultHandler";
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