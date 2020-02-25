package me.sta.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import me.sta.entity.JWT;
import me.sta.entity.UserLoginDTO;
import me.sta.service.UserService;

@Controller
@RequestMapping("/")
public class MainController {
	@Autowired
	UserService userService;

	@RequestMapping("/")
    public String index(ModelAndView modelAndView, HttpServletRequest request) {
//		modelAndView.setViewName("index");
//        return modelAndView;
		return "index";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
    public ModelAndView toLogin(ModelAndView modelAndView, HttpServletRequest request) {
		modelAndView.setViewName("userLogin");
        return modelAndView;
	}
	
	/*@RequestMapping(value="/error")
    public String error(ModelAndView modelAndView, HttpServletRequest request) {
        return "error";
	}*/
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
    public UserLoginDTO login(@RequestParam("userName")String userName,@RequestParam("password")String password,HttpServletRequest request) {
		UserLoginDTO ult = userService.login(userName, password);
        return ult;
	}
	
	
	@RequestMapping(value="/indexPage")
    public ModelAndView indexPage(ModelAndView modelAndView, @RequestParam("access_token")String accessToken,HttpServletRequest request) {
		System.out.println(accessToken);
		modelAndView.setViewName("index1");
        return modelAndView;
	}
	
	@RequestMapping(value="oauth/refreshToken")
	@ResponseBody
    public JWT refreshToken(@RequestParam("refresh_token")String refreshToken,HttpServletRequest request) {
		JWT jwt = userService.refreshToken(refreshToken); 
        return jwt;
	}
	
	@RequestMapping(value="/logout")
	@ResponseBody
    public String logout(@RequestParam("access_token")String accessToken,HttpServletRequest request) {
		userService.logout(request); 
        return "success";
	}
	
}
