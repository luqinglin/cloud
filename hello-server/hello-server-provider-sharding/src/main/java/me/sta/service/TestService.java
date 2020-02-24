package me.sta.service;

import me.sta.entity.Test;

/**
 * Created by luqingling on 2018/12/13.
 */
public interface TestService {

    int save(String name,Integer userId);

    Test findByUserIdAndId(int userId, int id);
}
