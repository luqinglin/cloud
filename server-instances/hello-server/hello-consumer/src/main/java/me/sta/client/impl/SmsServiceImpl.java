package me.sta.client.impl;

import me.sta.client.SmsService;
import me.sta.sms.model.SmsRequestModel;
import me.sta.sms.model.SmsResultModel;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class SmsServiceImpl implements SmsService {

    @Override
    public SmsResultModel sendSmsWithoutTemplate(@RequestParam("phoneNum") String phoneNum, @RequestParam("content")  String content) {
        return null;
    }
}
