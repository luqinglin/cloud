package com.codingapi.tx.listener.service.impl;

import com.codingapi.tx.config.ConfigReader;
import com.codingapi.tx.listener.service.InitService;
import com.codingapi.tx.netty.service.NettyService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InitServiceImpl implements InitService {


    @Autowired
    private NettyService nettyService;
    @Autowired
    private ConfigReader configReader;


    @Override
    public void start() {
        if (StringUtils.isEmpty(configReader.getTxUrl())){
            return;
        }
        nettyService.start();
    }

}