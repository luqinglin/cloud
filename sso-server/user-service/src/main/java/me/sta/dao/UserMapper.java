package me.sta.dao;

import me.sta.entity.User;

public interface UserMapper{
    User findByUsername(String username);
}
