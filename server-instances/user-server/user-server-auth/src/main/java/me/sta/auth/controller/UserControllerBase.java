package me.sta.auth.controller;

import me.sta.auth.pojo.UserInfo;
import me.sta.auth.service.UserInfoService;
import me.sta.dto.RestResult;
import me.sta.user.dto.UserInfoDto;
import me.sta.user.service.UserServiceBase;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserControllerBase implements UserServiceBase {

    @Autowired
    UserInfoService userInfoService;

    @Override
    public RestResult register(@RequestParam("username") String username,
                               @RequestParam("password") String password){
        UserInfo user = userInfoService.insertUser(username, password);
        if (user==null) return RestResult.buildError();
        UserInfoDto dto = new UserInfoDto();
        BeanUtils.copyProperties(user,dto);
        return RestResult.buildSuccess(dto);
    }

    @Override
    public UserInfoDto getUserInfoByName(String username) {
        UserInfo user = userInfoService.getUserInfoByName(username);
        if (user==null) return null;
        UserInfoDto dto = new UserInfoDto();
        BeanUtils.copyProperties(user,dto);
        return dto;
    }


}
