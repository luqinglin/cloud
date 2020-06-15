package me.sta.user.service;

import me.sta.dto.RestResult;
import me.sta.user.dto.UserInfoDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * user-service 提供服务,会包装user-auth服务
 */
public interface UserService {


    @PostMapping("/user/login")
    RestResult login(@RequestParam("username") String username,
                     @RequestParam("password") String password) ;


    @PostMapping("/user/refreshToken")
    RestResult refreshToken(@RequestParam("refreshToken") String refreshToken);


    @PostMapping("/user/verifyToken")
    RestResult verifyToken(@RequestParam("token") String token) ;


    @PostMapping("/user/revokeToken")
    RestResult revokeToken(@RequestParam("token") String token) ;


    @PostMapping("/user/register")
    RestResult register(@RequestParam("username") String username,
                        @RequestParam("password") String password);


    @PostMapping("/user/getUserInfoByName")
    UserInfoDto getUserInfoByName(@RequestParam("username") String username);

}
