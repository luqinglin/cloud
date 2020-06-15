package me.sta.user.service;

import me.sta.dto.RestResult;
import me.sta.user.dto.UserInfoDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
/**
 * user-auth 提供服务
 */
public interface UserServiceBase {

    @PostMapping("/user/register")
    RestResult register(@RequestParam("username") String username,
                        @RequestParam("password") String password);


    @PostMapping("/user/getUserInfoByName")
    UserInfoDto getUserInfoByName(@RequestParam("username") String username);


}
