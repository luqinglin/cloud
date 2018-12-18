package me.sta.service;

import me.sta.helloservice.ServiceApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by luqingling on 2018/11/2.
 */
@FeignClient("helloServer")
public interface HelloService extends ServiceApi{}
