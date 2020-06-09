package me.sta.client;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import me.sta.client.impl.HelloServiceImpl;
import me.sta.helloservice.ServiceApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by luqingling on 2018/11/2.
 */
@FeignClient(value = "helloServer",fallback = HelloServiceImpl.class)
public interface HelloService extends ServiceApi{}
