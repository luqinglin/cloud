package me.sta.servicegateway.service;

import me.sta.servicegateway.service.impl.UserServerImpl;
import me.sta.user.service.UserService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "user-service", fallbackFactory = UserServerImpl.class)
public interface UserServer extends UserService {
}
