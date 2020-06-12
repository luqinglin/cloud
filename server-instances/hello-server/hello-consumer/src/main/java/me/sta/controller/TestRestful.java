package me.sta.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import me.sta.client.HelloService;
import me.sta.client.SmsService;
import me.sta.service.IMessageProvider;
import me.sta.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class TestRestful {

    @Autowired
    HelloService helloService;
    @Autowired
    SmsService smsService;
    @Autowired
    IMessageProvider iMessageProvider;
    @Autowired
    TestService testService;
    @RequestMapping(value = "/feign-consumer/sendMsg", method = RequestMethod.GET)
    public void sendMsg(){
        smsService.sendSmsWithoutTemplate("18911710751","11111");
    }

    @RequestMapping(value = "/feign-consumer/{id}", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "helloConsumerHandler", commandProperties = {
        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2500")
    })
    @SentinelResource(value="helloConsumer",blockHandler = "helloConsumerBlockHandler",fallback = "helloConsumerHandler")
    public String helloConsumer(@PathVariable("id") String id) {
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        int a = 9/0;
        return helloService.home(id,"2");
//        if (id!="")
//            throw new RuntimeException();
//        return "sss";
    }

    public String helloConsumerBlockHandler(String id, BlockException block) {
        return "客户端降级处理了，sentinel方式";
    }
    public String helloConsumerHandler(String id) {
        return "客户端降级处理了，hystrix";
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