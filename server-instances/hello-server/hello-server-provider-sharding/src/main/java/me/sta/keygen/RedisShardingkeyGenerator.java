package me.sta.keygen;

import me.sta.configuration.SpringContextUtil;
import me.sta.redis.service.RedisServerService;
import org.apache.shardingsphere.spi.keygen.ShardingKeyGenerator;

import java.util.Properties;

public class RedisShardingkeyGenerator implements ShardingKeyGenerator {

    @Override
    public Comparable<?> generateKey() {
        RedisServerService redisServerService = SpringContextUtil.getBean(RedisServerService.class);
        return redisServerService.generatorKey();
    }

    @Override
    public String getType() {
        return "REDIS";
    }

    @Override
    public Properties getProperties() {
        return null;
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
