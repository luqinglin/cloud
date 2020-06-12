package me.sta.configuration;

import com.alibaba.csp.sentinel.command.handler.ModifyParamFlowRulesCommandHandler;
import com.alibaba.csp.sentinel.config.SentinelConfig;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.WritableDataSource;
import com.alibaba.csp.sentinel.datasource.redis.RedisDataSource;
import com.alibaba.csp.sentinel.datasource.redis.config.RedisConnectionConfig;
import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRule;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRuleManager;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRuleManager;
import com.alibaba.csp.sentinel.slots.system.SystemRule;
import com.alibaba.csp.sentinel.slots.system.SystemRuleManager;
import com.alibaba.csp.sentinel.transport.util.WritableDataSourceRegistry;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.util.List;

@Configuration
@ConditionalOnProperty(name = "feign.sentinel.enabled", havingValue = "true")
public class SentinelRedisInit {

    @Value("${spring.redis.host}")
    private String redisHost;
    @Value("${spring.redis.port}")
    private Integer redisPort;
//    @Value("${spring.redis.password}")
//    private String password;
    @Value("${spring.cloud.sentinel.transport.port}")
    private String sentinelPort;

    //限流规则key前缀
    public final String RULE_FLOW = "sentinel_rule_flow_";
    public final String RULE_FLOW_CHANNEL = "sentinel_rule_flow_channel";
    //降级规则key前缀
    public final String RULE_DEGRADE = "sentinel_rule_degrade_";
    public final String RULE_DEGRADE_CHANNEL = "sentinel_rule_degrade_channel";
    //系统规则key前缀
    public final String RULE_SYSTEM = "sentinel_rule_system_";
    public final String RULE_SYSTEM_CHANNEL = "sentinel_rule_system_channel";
    //热点规则
    public final String RULE_PARAM = "sentinel_rule_param_";
    public final String RULE_PARAM_CHANNEL = "sentinel_rule_param_channel";
    //授权规则
    public final String RULE_AUTH_FLOW = "sentinel_rule_auth_flow_";
    public final String RULE_AUTH_FLOW_CHANNEL = "sentinel_rule_auth_flow_channel";

    @PostConstruct
    public void init() throws Exception {
        if (StringUtils.isEmpty(redisHost)){
            return;
        }
        RedisConnectionConfig config = RedisConnectionConfig.builder()
                .withHost(redisHost)
                .withPort(redisPort)
                .build();
        InetAddress host = InetAddress.getLocalHost();
        initFlowRule(config, host);
        initDegradeRule(config, host);
        initSystemRule(config, host);
        initParamFlowRuleDefinition(config, host);
        initAuthorityRuleRule(config, host);

    }

    private void initFlowRule(RedisConnectionConfig config, InetAddress host){
        Converter<String, List<FlowRule>> parser = source -> JSON.parseObject(source,new TypeReference<List<FlowRule>>() {});
        ReadableDataSource<String, List<FlowRule>> redisDataSource = new RedisDataSource<>(config, RULE_FLOW+ SentinelConfig.getAppName()+":"+host.getHostAddress()+":"+sentinelPort, RULE_FLOW_CHANNEL, parser);
        FlowRuleManager.register2Property(redisDataSource.getProperty());

        WritableDataSource<List<FlowRule>> writableDataSource = new RedisWritableDataSource<>(config, RULE_FLOW+ SentinelConfig.getAppName()+":"+host.getHostAddress()+":"+sentinelPort);
        WritableDataSourceRegistry.registerFlowDataSource(writableDataSource);
    }

    private void initDegradeRule(RedisConnectionConfig config, InetAddress host){
        Converter<String, List<DegradeRule>> parser1 = source -> JSON.parseObject(source,new TypeReference<List<DegradeRule>>() {});
        ReadableDataSource<String, List<DegradeRule>> redisDataSource1 = new RedisDataSource<>(config, RULE_DEGRADE+SentinelConfig.getAppName()+":"+host.getHostAddress()+":"+sentinelPort, RULE_DEGRADE_CHANNEL, parser1);
        DegradeRuleManager.register2Property(redisDataSource1.getProperty());

        WritableDataSource<List<DegradeRule>> writableDataSource = new RedisWritableDataSource<>(config, RULE_DEGRADE+ SentinelConfig.getAppName()+":"+host.getHostAddress()+":"+sentinelPort);
        WritableDataSourceRegistry.registerDegradeDataSource(writableDataSource);
    }

    private void initSystemRule(RedisConnectionConfig config, InetAddress host){
        Converter<String, List<SystemRule>> parser2 = source -> JSON.parseObject(source,new TypeReference<List<SystemRule>>() {});
        ReadableDataSource<String, List<SystemRule>> redisDataSource2 = new RedisDataSource<>(config, RULE_SYSTEM+SentinelConfig.getAppName()+":"+host.getHostAddress()+":"+sentinelPort, RULE_SYSTEM_CHANNEL, parser2);
        SystemRuleManager.register2Property(redisDataSource2.getProperty());

        WritableDataSource<List<SystemRule>> writableDataSource = new RedisWritableDataSource<>(config, RULE_SYSTEM+ SentinelConfig.getAppName()+":"+host.getHostAddress()+":"+sentinelPort);
        WritableDataSourceRegistry.registerSystemDataSource(writableDataSource);
    }

    private void initParamFlowRuleDefinition(RedisConnectionConfig config, InetAddress host) throws Exception {
        Converter<String, List<ParamFlowRule>> parser3 = source -> JSON.parseObject(source,new TypeReference<List<ParamFlowRule>>() {});
        ReadableDataSource<String, List<ParamFlowRule>> redisDataSource3 = new RedisDataSource<>(config, RULE_PARAM+SentinelConfig.getAppName()+":"+host.getHostAddress()+":"+sentinelPort, RULE_PARAM_CHANNEL, parser3);
        ParamFlowRuleManager.register2Property(redisDataSource3.getProperty());

        WritableDataSource<List<ParamFlowRule>> writableDataSource = new RedisWritableDataSource<>(config, RULE_PARAM+SentinelConfig.getAppName()+":"+host.getHostAddress()+":"+sentinelPort);
        ModifyParamFlowRulesCommandHandler.setWritableDataSource(writableDataSource);

    }

    private void initAuthorityRuleRule(RedisConnectionConfig config, InetAddress host) throws Exception {
        Converter<String, List<AuthorityRule>> parser4 = source -> JSON.parseObject(source,new TypeReference<List<AuthorityRule>>() {});
        ReadableDataSource<String, List<AuthorityRule>> redisDataSource4 = new RedisDataSource<>(config, RULE_AUTH_FLOW+SentinelConfig.getAppName()+":"+host.getHostAddress()+":"+sentinelPort, RULE_AUTH_FLOW_CHANNEL, parser4);
        AuthorityRuleManager.register2Property(redisDataSource4.getProperty());

        WritableDataSource<List<AuthorityRule>> writableDataSource = new RedisWritableDataSource<>(config, RULE_AUTH_FLOW+SentinelConfig.getAppName()+":"+host.getHostAddress()+":"+sentinelPort);
        WritableDataSourceRegistry.registerAuthorityDataSource(writableDataSource);
    }
}
