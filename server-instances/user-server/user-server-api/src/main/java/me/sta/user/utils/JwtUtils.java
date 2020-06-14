package me.sta.user.utils;

import cn.hutool.core.date.DateUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.util.FileCopyUtils;

import java.security.PublicKey;
import java.util.Date;
import java.util.Map;

/**
 * @author:
 * 生成token以及校验token相关方法
 */
public class JwtUtils {

    /**
     * 公钥解析token
     *
     * @param token     用户请求中的token
     * @param publicKey 公钥
     * @return Jws<Claims>
     */
    public static Jws<Claims> parserToken(String token, PublicKey publicKey) {
        return Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token);
    }

    public static Boolean isTokenExpired(String token){
        try {
            Jwt jwt = getClaimsFromToken(token);
            Map<String, Object> map = JsonUtils.toMap(jwt.getClaims(), String.class, Object.class);
            Object exp = map.get("exp");
            Long timestamp = Long.parseLong(exp.toString())*1000;

            return new Date(timestamp).before(new Date());
        }catch (Exception e){
            return false;
        }
    }


    public static String getUserName(String token){
        try {
            Jwt jwt = getClaimsFromToken(token);
            Map<String, Object> map = JsonUtils.toMap(jwt.getClaims(), String.class, Object.class);
            Object exp = map.get("user_name");
            return exp.toString();
        }catch (Exception e){
            return null;
        }
    }

    public static Integer getUserId(String token){
        try {
            Jwt jwt = getClaimsFromToken(token);
            Map<String, Object> map = JsonUtils.toMap(jwt.getClaims(), String.class, Object.class);
            Object exp = map.get("userId");
            return Integer.valueOf(exp.toString());
        }catch (Exception e){
            return null;
        }
    }


    private static Jwt getClaimsFromToken(String token) {
        Resource resource = new ClassPathResource("public.cert");//拿到配置的公钥文件
        String publicKey;
        try {
            //生成公钥
            publicKey = new String(FileCopyUtils.copyToByteArray(resource.getInputStream()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Jwt jwt = JwtHelper.decodeAndVerify(token, new RsaVerifier(publicKey));
        return jwt;
    }

    public static void main(String[] args) {
        String s = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1OTIxMTgxNTcsInVzZXJJZCI6ODYsInVzZXJfbmFtZSI6IjEyMyIsImp0aSI6IjA2OTYzNzBkLWUwOWQtNDQwMC1hYjUzLTUwMzYzZjc1ZTlmYiIsImNsaWVudF9pZCI6InVzZXItc2VydmljZSIsInNjb3BlIjpbImFsbCJdfQ.mOCSRChxhAbzxojfWU-pOPSqckutqomixUuJGHzvx8bVfdz6b71QNDWBdGdwCvGG0z-RQNYkucFXWp6jrvVO8ySEgGdq1hIkEduRJDIBfdoooP5fqnwjWn5kUwhirMqn9_zNO82qtXzBEnr4eFOII93-ofv9k2K-k6ctGOcx5EWvRnI40VnuoU5Hag_LuLUJwDjZdEGC0Y-ehM6v2lh5TlfR6xSK9EkJ3SynoMVLup_XWhQJp0ef8YEqdJ8lDlOQJ46VD04irZZXkATlftmI-sRQTAcXNUQRSIwkJFhMhYP79QywK4bwWMYHKdOE4NBFi_k-ytf5j4-S4hMf1uMRkQ";

        System.out.println(isTokenExpired(s));
        System.out.println(getUserId(s));
        System.out.println(getUserName(s));

        System.out.println(getClaimsFromToken(s).getClaims());
    }

}