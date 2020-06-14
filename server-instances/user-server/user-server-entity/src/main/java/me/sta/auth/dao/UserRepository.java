package me.sta.auth.dao;

import me.sta.auth.pojo.UserInfo;
import org.springframework.stereotype.Repository;
import tk.mapper.MyMapper;


@Repository
public interface UserRepository extends MyMapper<UserInfo> {

    UserInfo findByName(String username);


}
