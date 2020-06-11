package me.sta.sms.service;

import me.sta.sms.pojo.ResModel;

import java.util.List;

/**
 * @CreateBy admin
 * @CreateTime 2019/7/16
 * @Description
 */
public interface ResModelService {
    List<ResModel> findLeftMenu(Long pid);
}
