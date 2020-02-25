/*package me.sta.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import me.sta.service.impl.UserServiceImpl;


@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserServiceImpl userServiceImpl;

    *//**
     * 自定义验证方式
     *//*
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        UserDetails  user = userServiceImpl.loadUserByUsername(username);
        if(user.getPassword().equals(password)) {
            return new UsernamePasswordAuthenticationToken(username, null);
        }
        if(BPwdEncoderUtil.matches(password, user.getPassword())) {
            return new UsernamePasswordAuthenticationToken(username, null);
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> arg0) {
        return true;
    }

}*/
