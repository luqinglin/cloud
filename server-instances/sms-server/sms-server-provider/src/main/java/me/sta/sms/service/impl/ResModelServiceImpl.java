package me.sta.sms.service.impl;

import me.sta.sms.dao.ResModelMapper;
import me.sta.sms.pojo.ResModel;
import me.sta.sms.service.ResModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @CreateBy admin
 * @CreateTime 2019/7/16
 * @Description
 */
@Service
public class ResModelServiceImpl implements ResModelService {

    @Autowired
    ResModelMapper resModelMapper;


    @Override
    public List<ResModel> findLeftMenu(Long pid) {
        return resModelMapper.findLeftMenu();
    }
}
