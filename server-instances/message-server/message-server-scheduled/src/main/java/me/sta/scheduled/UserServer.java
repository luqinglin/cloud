package me.sta.scheduled;

import me.sta.scheduled.impl.UserServerImpl;
import me.sta.user.service.UserService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "user-service", fallbackFactory = UserServerImpl.class)
public interface UserServer extends UserService {
}
