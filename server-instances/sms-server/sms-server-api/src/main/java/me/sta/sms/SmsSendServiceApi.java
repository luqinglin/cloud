package me.sta.sms;

import me.sta.sms.model.SmsRequestModel;
import me.sta.sms.model.SmsResultModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public interface SmsSendServiceApi {

//    /**
//     * 使用模板发送短信
//     *
//     * @param requestModel 发送消息
//     * @return
//     */
//    @RequestMapping(value = "/sms/sendSmsByTemplate", method = RequestMethod.GET,consumes= MediaType.APPLICATION_JSON_VALUE)
//    SmsResultModel sendSmsByTemplate(SmsRequestModel requestModel);

    /**
     * 不使用模板发送短信
     *
     * @param phoneNum 手机号码
     * @param content  发送内容
     * @return
     */
    @RequestMapping(value = "/sms/sendSmsWithoutTemplate", method = RequestMethod.GET)
    SmsResultModel sendSmsWithoutTemplate(@RequestParam("phoneNum") String phoneNum, @RequestParam("content")  String content);
}
