package me.sta.auth.service;

import me.sta.auth.service.impl.UserInfoServiceImpl;
import me.sta.user.service.UserServiceBase;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "auth-service", fallback = UserInfoServiceImpl.class)
public interface UserInfoService extends UserServiceBase {

}
