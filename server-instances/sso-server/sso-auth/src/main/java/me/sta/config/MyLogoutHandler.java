package me.sta.config;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

@Component
public class MyLogoutHandler implements LogoutHandler{

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		// TODO Auto-generated method stub
		try {
			if(request.getParameter("logoutUrl")!=null && !request.getParameter("logoutUrl").equals("")) {
				String logoutUrl = request.getParameter("logoutUrl");//logoutUrl即为前端传来自定义跳转url地址
				response.sendRedirect(logoutUrl);//实现自定义重定向
			}else {
				response.sendRedirect("/");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
