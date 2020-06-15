package me.sta.scheduled.impl;

import feign.hystrix.FallbackFactory;
import me.sta.dto.RestResult;
import me.sta.scheduled.UserServer;
import me.sta.user.dto.UserInfoDto;
import org.springframework.stereotype.Component;

@Component
public class UserServerImpl implements FallbackFactory<UserServer> {
    @Override
    public UserServer create(Throwable throwable) {
        throwable.printStackTrace();
        return new UserServer() {
            @Override
            public RestResult login(String username, String password) {
                return null;
            }

            @Override
            public RestResult refreshToken(String refreshToken) {
                return null;
            }

            @Override
            public RestResult verifyToken(String token) {
                return null;
            }

            @Override
            public RestResult revokeToken(String token) {
                return null;
            }

            @Override
            public RestResult register(String username, String password) {
                return null;
            }

            @Override
            public UserInfoDto getUserInfoByName(String username) {
                return null;
            }
        };
    }
}
