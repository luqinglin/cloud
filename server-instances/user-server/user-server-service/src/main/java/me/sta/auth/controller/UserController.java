package me.sta.auth.controller;

import me.sta.auth.service.AuthServiceClient;
import me.sta.auth.service.UserInfoService;
import me.sta.dto.RestResult;
import me.sta.user.dto.UserLoginDTO;
import me.sta.user.exception.UserLoginException;
import me.sta.user.model.JWT;
import me.sta.user.service.UserService;
import me.sta.user.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;


@RestController
public class UserController implements UserService {

    private static final String TOKEN_AS_SESSION = "session:jwt:token:";
    private static final String RETOKEN_AS_SESSION = "session:jwt:retoken:";

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private AuthServiceClient authServiceClient;
    @Autowired
    private RedisTemplate redisTemplate;



    @Override
    public RestResult register(@RequestParam("username") String username,
                                @RequestParam("password") String password) {
        RestResult user = userInfoService.register(username, password);
        return user;
    }


    @Override
    public RestResult login(@RequestParam("username") String username,
                              @RequestParam("password") String password) {
        // 从auth-service获取JWT
        JWT jwt = authServiceClient.getToken("Basic dXNlci1zZXJ2aWNlOjEyMzQ1Ng==", "password", username, password);
        if(jwt == null){
            throw new UserLoginException("error internal");
        }


        redisTemplate.opsForValue().set(TOKEN_AS_SESSION+jwt.getUserId(),jwt.getAccess_token(),jwt.getExpires_in(), TimeUnit.SECONDS);
        redisTemplate.opsForValue().set(RETOKEN_AS_SESSION+jwt.getUserId(),jwt.getRefresh_token(),jwt.getExpires_in(), TimeUnit.SECONDS);

        UserLoginDTO userLoginDTO=new UserLoginDTO();
        userLoginDTO.setAccess_token(jwt.getAccess_token());
        userLoginDTO.setRefresh_token(jwt.getRefresh_token());
        return RestResult.buildSuccess(userLoginDTO);
    }

    @Override
    public RestResult refreshToken(String refreshToken) {
        Boolean tokenExpired = JwtUtils.isTokenExpired(refreshToken);
        if (tokenExpired){
            return RestResult.buildError("token已过期");
        }
        Integer userId = JwtUtils.getUserId(refreshToken);
        if (userId==null){
            return RestResult.buildError("无效的token");
        }
        Object tokenLocal = redisTemplate.opsForValue().get(RETOKEN_AS_SESSION + userId);
        if (tokenLocal==null){
            return RestResult.buildError("无效的token");
        }
        String localToken = tokenLocal.toString();
        if (!localToken.equals(refreshToken)){
            return RestResult.buildError("无效的token");
        }

        JWT jwt = authServiceClient.refreshToken("Basic dXNlci1zZXJ2aWNlOjEyMzQ1Ng==", "refresh_token",refreshToken);

        redisTemplate.opsForValue().set(TOKEN_AS_SESSION+jwt.getUserId(),jwt.getAccess_token(),jwt.getExpires_in(), TimeUnit.SECONDS);
        redisTemplate.opsForValue().set(RETOKEN_AS_SESSION+jwt.getUserId(),jwt.getRefresh_token(),jwt.getExpires_in(), TimeUnit.SECONDS);
        return RestResult.buildSuccess(jwt);
    }

    @Override
    public RestResult verifyToken(String token) {
        Boolean tokenExpired = JwtUtils.isTokenExpired(token);
        if (tokenExpired){
            return RestResult.buildError("token已过期");
        }

        Integer userId = JwtUtils.getUserId(token);
        if (userId==null){
            return RestResult.buildError("无效的token");
        }
        Object tokenLocal = redisTemplate.opsForValue().get(TOKEN_AS_SESSION + userId);
        if (tokenLocal==null){
            return RestResult.buildError("无效的token");
        }
        String localToken = tokenLocal.toString();
        if (!localToken.equals(token)){
            return RestResult.buildError("无效的token");
        }
        return RestResult.buildSuccess("active");
    }

    @Override
    public RestResult revokeToken(String token) {
        Boolean tokenExpired = JwtUtils.isTokenExpired(token);
        if (tokenExpired){
            return RestResult.buildError("token已过期");
        }

        Integer userId = JwtUtils.getUserId(token);
        if (userId==null){
            return RestResult.buildError("无效的token");
        }
        Object tokenLocal = redisTemplate.opsForValue().get(TOKEN_AS_SESSION + userId);
        if (tokenLocal==null){
            return RestResult.buildError("无效的token");
        }
        String localToken = tokenLocal.toString();
        if (!localToken.equals(token)){
            return RestResult.buildError("无效的token");
        }
        redisTemplate.delete(TOKEN_AS_SESSION+userId);
        redisTemplate.delete(RETOKEN_AS_SESSION+userId);
        return RestResult.buildSuccess();
    }


}