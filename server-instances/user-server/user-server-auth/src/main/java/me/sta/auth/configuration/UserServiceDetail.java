package me.sta.auth.configuration;

import me.sta.auth.dao.UserRepository;
import me.sta.auth.model.User;
import me.sta.auth.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceDetail implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userRepository.findByName(username);
        return new User(userInfo.getId(),userInfo.getName(),userInfo.getPwd(),null);
    }
}