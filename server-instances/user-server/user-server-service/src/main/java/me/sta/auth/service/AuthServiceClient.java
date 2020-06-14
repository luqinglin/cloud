package me.sta.auth.service;

import feign.hystrix.FallbackFactory;
import me.sta.user.model.JWT;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(value = "auth-service", fallbackFactory = AuthServiceClientFallbackFactory.class)
public interface AuthServiceClient {
    @PostMapping("/oauth/token")
    JWT getToken(@RequestHeader("Authorization") String authorization,
                 @RequestParam("grant_type") String type,
                 @RequestParam("username") String username,
                 @RequestParam("password") String password);




    @PostMapping("/oauth/token")
    JWT refreshToken(@RequestHeader("Authorization") String authorization,
                 @RequestParam("grant_type") String type,
                 @RequestParam("refresh_token") String refreshToken);


}

@Component
class AuthServiceClientFallbackFactory implements FallbackFactory<AuthServiceClient> {
    @Override
    public AuthServiceClient create(Throwable throwable) {
        return new AuthServiceClient() {
            @Override
            public JWT getToken(String authorization, String type, String username, String password) {
                return null;
            }


            @Override
            public JWT refreshToken(String authorization, String type, String refreshToken) {
                return null;
            }
        };
    }
}