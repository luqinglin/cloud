package me.sta.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.codingapi.tx.annotation.ITxTransaction;

import me.sta.dao.UserMapper;
import me.sta.service.UserService;
@Component
public class UserServiceImpl implements UserService,UserDetailsService,ITxTransaction{

	@Autowired
    private UserMapper userMapper;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		System.out.println(username+"-----------------------");
		return userMapper.findByUsername(username);
	}
}
