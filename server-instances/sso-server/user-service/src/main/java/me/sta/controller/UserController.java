package me.sta.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import me.sta.entity.UserLoginDTO;
import me.sta.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	 @Autowired
	 UserService userService;
	
	 @RequestMapping("/login")
	public UserLoginDTO login(@RequestParam("username") String username,
	                          @RequestParam("password") String password) {
	    return userService.login(username,password);
	}
	 
	 @RequestMapping("/logout")
		public String logout(HttpServletRequest request) {
		    userService.logout(request);
		    return "success";
		}
	 
	 @RequestMapping("/test")
		public String test() {
		    return UUID.randomUUID().toString();
		}
		 
	 
	 /**
	  * 用户权限测试
	  * @return
	  */
	 /*@RequestMapping(value = "/foo")
	 @PreAuthorize("hasAuthority('ROLE_ADMIN')")
	 public String getFoo() {
	     return "i'm foo, " + UUID.randomUUID().toString();
	 }*/
}
