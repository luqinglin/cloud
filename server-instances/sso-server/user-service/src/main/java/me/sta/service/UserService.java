package me.sta.service;

import javax.servlet.http.HttpServletRequest;

import me.sta.entity.JWT;
import me.sta.entity.UserLoginDTO;

public interface UserService {

	public UserLoginDTO login(String username, String password);
	
	public void logout(HttpServletRequest request);
	
	public JWT refreshToken(String refreshToken);
}
