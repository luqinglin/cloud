package me.sta.redis.service;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

/**
 * create by lorne on 2017/11/11
 */
public class  RedisServerService {

    private RedisTemplate<String, Object> redisTemplate;

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Long generatorKey(){
       String key = "generatorKey:sharding";
       return redisTemplate.opsForValue().increment(key,1);
   }


}
