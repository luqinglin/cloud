package com.codingapi.tx.config;

import com.codingapi.tx.config.service.TxManagerTxUrlService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * create by lorne on 2017/11/13
 */
@Component
public class ConfigReader {


    private Logger logger = LoggerFactory.getLogger(ConfigReader.class);


    private TxManagerTxUrlService txManagerTxUrlService;

    @Autowired
    private ApplicationContext spring;
    @Value("${tx.url}")
    private String url;


    public String getTxUrl() {
        if (StringUtils.isEmpty(url)){
            return null;
        }

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


}
