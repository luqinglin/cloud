package me.sta.sms.controller;

import me.sta.sms.SmsSendServiceApi;
import me.sta.sms.model.SmsRequestModel;
import me.sta.sms.model.SmsResultModel;
import me.sta.sms.service.SmsSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SmsSendServiceController implements SmsSendServiceApi {
    @Autowired
    private SmsSendService smsSendService;
//    @Override
//    public SmsResultModel sendSmsByTemplate(SmsRequestModel requestModel) {
//        return smsSendService.sendSmsByTemplate(requestModel);
//    }

    @Override
    public SmsResultModel sendSmsWithoutTemplate(String phoneNum, String content) {
        return smsSendService.sendSmsWithoutTemplate(phoneNum,content);
    }
}
