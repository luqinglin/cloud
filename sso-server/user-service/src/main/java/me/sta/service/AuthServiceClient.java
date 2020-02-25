package me.sta.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import me.sta.entity.JWT;
import me.sta.service.impl.AuthServiceHystrix;

@FeignClient(value = "ssoAuth", fallback = AuthServiceHystrix.class)
public interface AuthServiceClient {
    @PostMapping("/oauth/token")
    JWT getToken(@RequestHeader("Authorization") String authorization,
                 @RequestParam("grant_type") String type,
                 @RequestParam("username") String username,
                 @RequestParam("password") String password);
    @DeleteMapping("/oauth/token")
    void logout(HttpServletRequest request);
    @PostMapping("/oauth/token")
    JWT getToken(@RequestHeader("Authorization") String authorization,
                 @RequestParam("grant_type") String type,
                 @RequestParam("refresh_token") String refreshToken);
}
