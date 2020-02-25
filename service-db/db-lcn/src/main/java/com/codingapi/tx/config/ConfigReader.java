package com.codingapi.tx.config;

import com.codingapi.tx.config.service.TxManagerTxUrlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * create by lorne on 2017/11/13
 */
@Component
@ConfigurationProperties(prefix = "tx")
@Validated
public class ConfigReader {


    private Logger logger = LoggerFactory.getLogger(ConfigReader.class);


    private TxManagerTxUrlService txManagerTxUrlService;
    @Autowired
    private ApplicationContext spring;
    @Nullable
    private String url;


    public String getTxUrl() {

        try {
            txManagerTxUrlService =  spring.getBean(TxManagerTxUrlService.class);
        }catch (Exception e){
            logger.debug("load default txManagerTxUrlService ");
        }

        if(txManagerTxUrlService == null){
            txManagerTxUrlService = new TxManagerTxUrlService() {
                @Override
                public String getTxUrl() {
                    return url;
                }
            };

            logger.debug("load default txManagerTxUrlService");
        }else{
            logger.debug("load txManagerTxUrlService");
        }

        return txManagerTxUrlService.getTxUrl();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
