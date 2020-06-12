package me.sta.configuration;

import com.alibaba.csp.sentinel.datasource.WritableDataSource;
import com.alibaba.csp.sentinel.datasource.redis.config.RedisConnectionConfig;
import com.alibaba.csp.sentinel.log.RecordLog;
import com.alibaba.csp.sentinel.util.AssertUtil;
import com.alibaba.csp.sentinel.util.StringUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.sync.RedisCommands;

import java.time.Duration;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class RedisWritableDataSource<T> implements WritableDataSource<T> {

    private final RedisClient redisClient;
    private String key;

    public RedisWritableDataSource(RedisConnectionConfig config, String key){
        AssertUtil.notNull(config, "Redis connection config can not be null");
        AssertUtil.notEmpty(key, "Redis ruleKey can not be empty");
        this.redisClient = this.getRedisClient(config);
        this.key = key;
    }

    private RedisClient getRedisClient(RedisConnectionConfig connectionConfig) {
        if (connectionConfig.getRedisSentinels().size() == 0) {
            RecordLog.info("[RedisDataSource] Creating stand-alone mode Redis client", new Object[0]);
            return this.getRedisStandaloneClient(connectionConfig);
        } else {
            RecordLog.info("[RedisDataSource] Creating Redis Sentinel mode Redis client", new Object[0]);
            return this.getRedisSentinelClient(connectionConfig);
        }
    }

    private RedisClient getRedisStandaloneClient(RedisConnectionConfig connectionConfig) {
        char[] password = connectionConfig.getPassword();
        String clientName = connectionConfig.getClientName();
        RedisURI.Builder redisUriBuilder = RedisURI.builder();
        redisUriBuilder.withHost(connectionConfig.getHost()).withPort(connectionConfig.getPort()).withDatabase(connectionConfig.getDatabase()).withTimeout(Duration.ofMillis(connectionConfig.getTimeout()));
        if (password != null) {
            redisUriBuilder.withPassword(connectionConfig.getPassword());
        }

        if (StringUtil.isNotEmpty(connectionConfig.getClientName())) {
            redisUriBuilder.withClientName(clientName);
        }

        return RedisClient.create(redisUriBuilder.build());
    }

    private RedisClient getRedisSentinelClient(RedisConnectionConfig connectionConfig) {
        char[] password = connectionConfig.getPassword();
        String clientName = connectionConfig.getClientName();
        RedisURI.Builder sentinelRedisUriBuilder = RedisURI.builder();
        Iterator var5 = connectionConfig.getRedisSentinels().iterator();

        while(var5.hasNext()) {
            RedisConnectionConfig config = (RedisConnectionConfig)var5.next();
            sentinelRedisUriBuilder.withSentinel(config.getHost(), config.getPort());
        }

        if (password != null) {
            sentinelRedisUriBuilder.withPassword(connectionConfig.getPassword());
        }

        if (StringUtil.isNotEmpty(connectionConfig.getClientName())) {
            sentinelRedisUriBuilder.withClientName(clientName);
        }

        sentinelRedisUriBuilder.withSentinelMasterId(connectionConfig.getRedisSentinelMasterId()).withTimeout(connectionConfig.getTimeout(), TimeUnit.MILLISECONDS);
        return RedisClient.create(sentinelRedisUriBuilder.build());
    }

    @Override
    public void write(T t) throws Exception {
        RedisCommands<String, String> stringRedisCommands = this.redisClient.connect().sync();
        stringRedisCommands.set(key, JSON.toJSONString(t, SerializerFeature.WriteClassName));
    }

    @Override
    public void close() throws Exception {
        this.redisClient.shutdown();
    }
}