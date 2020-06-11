package me.sta.redis;

import me.sta.redis.service.RedisServerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Created by lorne on 2017/7/5.
 */

@Configuration
public class RedisConfig {

    private static Logger logger = LoggerFactory.getLogger(RedisConfig.class);

    @Bean
    public RedisServerService redisServerService(RedisTemplate redisTemplate) {
        RedisServerService redisServerService = new RedisServerService();
        redisServerService.setRedisTemplate(redisTemplate);
        return redisServerService;
    }
}
