package me.sta.sms.listener;

import me.sta.sms.model.CompanyTag;
import me.sta.sms.model.SmsCompany;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @CreateBy admin
 * @CreateTime 2019/7/10
 * @Description
 */
@Component
public class BootFinishListener implements ApplicationListener<ApplicationEvent> {
    private static LinkedList<CompanyTag> companyList;
    private static HashMap<String, SmsCompany> companyHashMap = new HashMap<>();
    Logger logger = LoggerFactory.getLogger(BootFinishListener.class);

    @Autowired
    ApplicationContext ioc;

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        loadSmsCompanies();
    }

    /**
     * 加载程序中配置的公司类
     */
    private void loadSmsCompanies() {
        if (companyList == null) {
            synchronized (BootFinishListener.class) {
                if (companyList == null) {
                    companyList = new LinkedList<>();
                }
                Map<String, SmsCompany> beansOfType = ioc.getBeansOfType(SmsCompany.class);
                for (String key : beansOfType.keySet()) {
                    SmsCompany smsService = beansOfType.get(key);
                    CompanyTag tag = smsService.getCompanyTag();
                    if (!companyList.contains(tag)) {
                        companyList.add(tag);
                        companyHashMap.put(tag.getTag(), smsService);
                    } else {
                        logger.info("#############################################################");
                        logger.error("sdk的tag重复了  tag:" + tag + " class:" + smsService.getClass().getName());
                        logger.info("#############################################################");
                        throw new RuntimeException("sdk的tag重复了  tag:" + tag + " 重复的类为:" + smsService.getClass().getName());
                    }
                }
                logger.info("程序配置的公司加载完毕");
            }
        }
    }

    /**
     * 获取支持平台的taglist
     *
     * @return
     */
    public List<CompanyTag> companyTags() {
        return companyList;
    }


    public SmsCompany getSmsCompanyByTag(String tag) {
        return companyHashMap.get(tag);
    }
}
