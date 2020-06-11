package me.sta.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MainController {

	@RequestMapping("/test")
    public String login(){
        return "可以直接访问的方法";
    }

	@RequestMapping("/testLogin")
    public String newTasks(){
        return "需要登录才能访问的方法";
    }
	@RequestMapping("/")
    public String logout(){
        return "退出成功";
    }
}
