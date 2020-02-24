package me.sta.helloservice;

import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by luqingling on 2018/11/4.
 */
public interface ServiceApi {

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String home(@RequestHeader("username") String username, @RequestHeader("passwd") String passwd);

    @RequestMapping(value = "/hello1",method = RequestMethod.GET)
    public String home1(@RequestHeader("username") String username,@RequestHeader("passwd") String passwd);

    @RequestMapping(value = "/saveTest",method = RequestMethod.GET)
    public int save(@RequestParam("name") String name,@RequestParam("userId") Integer userId);
}
