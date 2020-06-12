package me.sta.sms.core;

import me.sta.sms.model.CompanyTag;
import me.sta.sms.model.SmsResultModel;
import me.sta.sms.model.TemplateSendModel;

/**
 * 平台公司类
 *
 * @CreateBy admin
 * @CreateTime 2019/7/16
 * @Description
 */
public abstract class SmsCompany {

    public abstract CompanyTag getCompanyTag();

    /**
     * 发送短信
     *
     * @param templeate 发送内容
     * @return
     */
    protected abstract SmsResultModel sendSmsCodeReal(TemplateSendModel templeate, String phoneNum);    /**
     * 发送短信
     *
     * @param templeate 发送内容
     * @return
     */
    protected abstract SmsResultModel sendSmsCodeReal(String phoneNum,String content);

    /**
     * 分为：使用模板 需要替换参数
     * 不使用模板 直接发送 内容为params的第一个参数
     *
     * @param templeate
     * @return
     */
    public SmsResultModel sendSmsCode(TemplateSendModel templeate, String phoneNum) {

        //替换参数
        SmsResultModel result = templeate.replaceParam();
        if (result != null) {
            return result;
        }
        return sendSmsCodeReal(templeate, phoneNum);
    }

    /**
     * 分为：使用模板 需要替换参数
     * 不使用模板 直接发送 内容为params的第一个参数
     *
     * @param templeate
     * @return
     */
    public SmsResultModel send(String phoneNum,String content) {
        return sendSmsCodeReal(phoneNum,content);
    }


    /**
     * 是否需要自定义短信签名
     *
     * @return
     */
    public abstract boolean isNeedSmsHeader();
}
