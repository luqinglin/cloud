package me.sta.auth.service.impl;

import feign.hystrix.FallbackFactory;
import me.sta.auth.service.UserInfoService;
import me.sta.dto.RestResult;
import me.sta.user.dto.UserInfoDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UserInfoServiceImpl implements FallbackFactory<UserInfoService> {
    private Logger logger = LoggerFactory.getLogger(UserInfoServiceImpl.class);

    @Override
    public UserInfoService create(Throwable throwable) {
        throwable.printStackTrace();
        return new UserInfoService() {


            @Override
            public RestResult register(String username, String password) {
                logger.error("error ----注册失败");
                return RestResult.buildError("注册失败");
            }

            @Override
            public UserInfoDto getUserInfoByName(String username) {
                logger.error("error ----查询数据库异常");
                return null;
            }
        };
    }
}