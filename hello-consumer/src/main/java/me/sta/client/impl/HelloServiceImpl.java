package me.sta.client.impl;

import me.sta.client.HelloService;
import me.sta.exception.ApplicationCode;
import me.sta.exception.SystemException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by luqingling on 2018/12/24.
 */
@Component
public class HelloServiceImpl implements HelloService {
    @Override
    public String home(@RequestHeader("username") String username, @RequestHeader("passwd") String passwd) {
        return username+","+passwd+",这里被熔断了";
    }

    @Override
    public String home1(@RequestHeader("username") String username, @RequestHeader("passwd") String passwd) {
        return username+","+passwd+",这里被熔断了";
    }

    @Override
    public int save(@RequestParam("name") String name,@RequestParam("userId") Integer userId) {
        System.out.println("熔断了。。。。");
        throw new SystemException(ApplicationCode.INTERNAL_SERVER_ERROR);
        //return 0;
    }
}
