package com.codingapi.tx.config;

import com.codingapi.tx.config.service.TxManagerTxUrlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * create by lorne on 2017/11/13
 */

@Configuration
public class ConfigReader {


    private Logger logger = LoggerFactory.getLogger(ConfigReader.class);


    private TxManagerTxUrlService txManagerTxUrlService;
    @Autowired
    private ApplicationContext spring;
    @Value("${tx.url}")
    private String url;


    public String getTxUrl() {
        return url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
