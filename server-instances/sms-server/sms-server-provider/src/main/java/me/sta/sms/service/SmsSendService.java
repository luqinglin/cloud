package me.sta.sms.service;

import me.sta.sms.model.SmsResultModel;
import me.sta.sms.model.SmsRequestModel;

/**
 * @CreateBy admin
 * @CreateTime 2019/7/19
 * @Description
 */
public interface SmsSendService {
    /**
     * 使用模板发送短信
     *
     * @param requestModel 发送消息
     * @return
     */
    SmsResultModel sendSmsByTemplate(SmsRequestModel requestModel);

    /**
     * 不使用模板发送短信
     *
     * @param phoneNum 手机号码
     * @param content  发送内容
     * @return
     */
    SmsResultModel sendSmsWithoutTemplate(String phoneNum, String content);
}
