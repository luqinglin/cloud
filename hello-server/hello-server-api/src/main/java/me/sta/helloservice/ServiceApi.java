package me.sta.helloservice;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by luqingling on 2018/11/4.
 */
public interface ServiceApi {

    @RequestMapping(value = "/a/hello", method = RequestMethod.GET)
    public String home(@RequestParam("username") String username, @RequestParam("passwd") String passwd);

    @RequestMapping(value = "/a/hello1", method = RequestMethod.GET)
    public String home1(@RequestParam("username") String username, @RequestParam("passwd") String passwd);

    @RequestMapping(value = "/a/saveTest", method = RequestMethod.GET)
    public int save(@RequestParam("name") String name, @RequestParam("userId") Integer userId);


//    @RequestMapping(value = "/saveTest",method = RequestMethod.GET,consumes= MediaType.APPLICATION_JSON_VALUE)
//    default int save(@RequestParam("name") User name){
//        return 0;
//    }
}
