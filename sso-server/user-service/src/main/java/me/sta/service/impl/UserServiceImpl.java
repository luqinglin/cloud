package me.sta.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import me.sta.dao.UserMapper;
import me.sta.entity.JWT;
import me.sta.entity.User;
import me.sta.entity.UserLoginDTO;
import me.sta.exception.UserLoginException;
import me.sta.service.AuthServiceClient;
import me.sta.service.UserService;
import me.sta.util.BPwdEncoderUtil;

@Component
public class UserServiceImpl implements UserService{

	
	@Autowired
    private AuthServiceClient client;
	
	@Autowired
    private UserMapper userMapper;
	
	/*@Value("${tokenUrl}")
	private String tokenUrl;*/
	
	@Override
    public UserLoginDTO login(String username, String password) {
        // 查询数据库
        User user = userMapper.findByUsername(username);
        if (user == null) {
            throw new UserLoginException("error username");
        }

        if(!BPwdEncoderUtil.matches(password,user.getPassword())){
            throw new UserLoginException("error password");
        }

        // 从sso-auth获取JWT
        JWT jwt = client.getToken("Basic c3NvLWF1dGg6MTIzNDU2", "password", username, password);
        /*Map<String, String> params = new HashMap<String, String>();
        params.put("grant_type", "password");
        params.put("client_id", "sso-auth");
        params.put("username", username);
        params.put("password", password);
        Response response = RestAssured.given().auth().preemptive().basic("sso-auth", "123456").and().with().params(params).when().post("http://127.0.0.1:3000/oauth/token");
        String token = response.jsonPath().getString("access_token");
        System.out.println(token);
        JWT jwt = new JWT();
        jwt.setAccess_token(token);*/
        UserLoginDTO userLoginDTO=new UserLoginDTO();
        userLoginDTO.setJwt(jwt);
        userLoginDTO.setUser(user);
        return userLoginDTO;
    }

	@Override
	public void logout(HttpServletRequest request) {
		// TODO Auto-generated method stub
		client.logout(request);
	}

	@Override
	public JWT refreshToken(String refreshToken) {
		// TODO Auto-generated method stub
		return client.getToken("Basic c3NvLWF1dGg6MTIzNDU2", "refresh_token", refreshToken);
		
	}
}
