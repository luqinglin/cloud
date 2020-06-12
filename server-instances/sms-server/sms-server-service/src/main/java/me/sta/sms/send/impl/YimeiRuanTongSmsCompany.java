package me.sta.sms.send.impl;

import me.sta.sms.core.SmsCompany;
import me.sta.sms.core.SmsUtils;
import me.sta.sms.model.CompanyTag;
import me.sta.sms.model.SmsResultModel;
import me.sta.sms.model.TemplateSendModel;
import org.springframework.stereotype.Component;

/**
 * 亿美软通
 *
 * @CreateBy admin
 * @CreateTime 2019/7/16
 * @Description
 */
@Component
public class YimeiRuanTongSmsCompany extends SmsCompany {

    @Override
    public CompanyTag getCompanyTag() {
        return CompanyTag.YI_MEI_RUAN_TONG;
    }


    @Override
    protected SmsResultModel sendSmsCodeReal(TemplateSendModel templeate, String phoneNum) {
        //todo 具体实现
        SmsResultModel result = new SmsResultModel();
        result.setCode(SmsResultModel.SUCCESS);
        result.setMsg("ok");
        return result;
    }

    @Override
    protected SmsResultModel sendSmsCodeReal(String phoneNum, String content) {
        return SmsUtils.sendContent(phoneNum,content);
    }

    @Override
    public boolean isNeedSmsHeader() {
        return false;
    }


}
