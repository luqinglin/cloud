package me.sta.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import me.sta.entity.JWT;
import me.sta.service.AuthServiceClient;

@Component
public class AuthServiceHystrix implements AuthServiceClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthServiceHystrix.class);

    @Override
    public JWT getToken(String authorization, String type, String username, String password) {
    	System.out.println(authorization+type+username+password);
        LOGGER.warn("获取token熔断了");
        return null;
    }

	@Override
	public void logout(HttpServletRequest request) {
		// TODO Auto-generated method stub
		LOGGER.warn("登出熔断了");
	}

	@Override
	public JWT getToken(String authorization, String type,String refreshToken) {
		// TODO Auto-generated method stub
		System.out.println(authorization+type+refreshToken+"----------------------");
        LOGGER.warn("刷新token熔断了");
		return null;
	}

}
