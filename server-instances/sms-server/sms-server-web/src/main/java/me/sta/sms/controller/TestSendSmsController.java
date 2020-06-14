package me.sta.sms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.sta.sms.model.SmsRequestModel;
import me.sta.sms.model.SmsResultModel;
import me.sta.sms.service.SmsSendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @CreateBy admin
 * @CreateTime 2019/7/18
 * @Description
 */
@Controller
public class TestSendSmsController {

    @Autowired
    SmsSendService smsSendService;


    Logger logger = LoggerFactory.getLogger(TestSendSmsController.class);

    @RequestMapping("/sendSms")
    @ResponseBody
    public Object sendSms(HttpServletRequest request, String data) {
        String addr = request.getRemoteAddr();
        if (addr != null && addr.contains("0:0:0:0:0:0:0:1")) {
            addr = "127.0.0.1";
        }
        logger.info("请求的IP地址为：" + addr);
        logger.info("接收到的数据为：" + data);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            SmsRequestModel smsRequestModel = objectMapper.readValue(data, SmsRequestModel.class);
            if (smsRequestModel != null) {
                smsRequestModel.setRequestIP(addr);
                return smsSendService.sendSmsByTemplate(smsRequestModel);
            } else {
                throw new NullPointerException("解析数据错误");
            }
        } catch (Exception e) {
            SmsResultModel smsResultModel = new SmsResultModel();
            smsResultModel.setCode(SmsResultModel.FAILURE);
            smsResultModel.setMsg(e.getMessage());
            return smsResultModel;
        }
    }

}
