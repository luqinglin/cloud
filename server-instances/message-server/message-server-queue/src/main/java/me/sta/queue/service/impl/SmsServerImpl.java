package me.sta.queue.service.impl;

import feign.hystrix.FallbackFactory;
import me.sta.queue.service.SmsServer;
import me.sta.sms.model.SmsResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class SmsServerImpl implements FallbackFactory<SmsServer> {

      @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public SmsServer create(Throwable throwable) {
        throwable.printStackTrace();
        return new SmsServer() {
            @Override
            public SmsResultModel sendSmsWithoutTemplate(String phoneNum, String content) {
                return null;
            }
        };
    }
}
