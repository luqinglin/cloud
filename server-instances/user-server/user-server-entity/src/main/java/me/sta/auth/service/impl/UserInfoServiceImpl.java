package me.sta.auth.service.impl;

import me.sta.auth.dao.UserRepository;
import me.sta.auth.pojo.UserInfo;
import me.sta.auth.service.UserInfoService;
import me.sta.auth.utils.BPwdEncoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public UserInfo insertUser(String username, String  password){
        UserInfo userInfo = new UserInfo();
        userInfo.setName(username);
        userInfo.setPwd(BPwdEncoderUtil.BCryptPassword(password));
        userRepository.insertSelective(userInfo);
        return userInfo;
    }
}