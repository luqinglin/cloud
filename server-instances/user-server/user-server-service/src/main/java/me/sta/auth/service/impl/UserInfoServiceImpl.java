package me.sta.auth.service.impl;

import me.sta.auth.service.UserInfoService;
import me.sta.dto.RestResult;
import me.sta.user.dto.UserInfoDto;
import org.springframework.stereotype.Component;

@Component
public class UserInfoServiceImpl implements UserInfoService {


    @Override
    public RestResult register(String username, String password) {
        return null;
    }

    @Override
    public UserInfoDto getUserInfoByName(String username) {
        return null;
    }
}