package me.sta.auth.service;


import me.sta.auth.pojo.UserInfo;

public interface UserInfoService {

    UserInfo insertUser(String username, String password);

    UserInfo getUserInfoByName(String username);

}
