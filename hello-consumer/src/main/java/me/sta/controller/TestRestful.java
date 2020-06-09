package me.sta.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import me.sta.client.HelloService;
import me.sta.helloservice.ServiceApi;
import me.sta.service.IMessageProvider;
import me.sta.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@RestController
@RefreshScope
public class TestRestful {

    @Autowired
    HelloService helloService;
    @Autowired
    IMessageProvider iMessageProvider;
    @Autowired
    TestService testService;

    @RequestMapping(value = "/feign-consumer/{id}", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "helloConsumerHandler", commandProperties = {
        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
    })
    public String helloConsumer(@PathVariable("id") String id) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        return helloService.home(id,"2");
//        if (id!="")
//            throw new RuntimeException();
        return "sss";
    }
    public String helloConsumerHandler(@PathVariable("id") String id) {
        return "客户端降级处理了";
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