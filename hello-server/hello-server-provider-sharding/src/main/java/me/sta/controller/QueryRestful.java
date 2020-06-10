package me.sta.controller;

import com.alibaba.fastjson.JSONObject;
import me.sta.entity.Test;
import me.sta.helloservice.ServiceApi;
import me.sta.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class QueryRestful {

    @Autowired
    private TestService testService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(String userId, String testId) {
        Test test = testService.findByUserIdAndId(Integer.parseInt(userId), Long.parseLong(testId));
        return JSONObject.toJSONString(test);
    }


}