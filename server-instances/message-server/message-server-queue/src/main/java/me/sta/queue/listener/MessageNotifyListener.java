package me.sta.queue.listener;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import me.sta.message.entity.RpTransactionMessage;
import me.sta.queue.service.SmsServer;
import me.sta.utils.StringUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component public class MessageNotifyListener {

    @Autowired
    SmsServer smsServer;

    @RabbitHandler
    @RabbitListener(queues = "MESSAGE_NOTIFY")
    public void execute(String messageBody){
        JSONObject jsonObject = JSONUtil.toBean(messageBody, JSONObject.class);
        String phone = jsonObject.getStr("phone");
        String content = jsonObject.getStr("content");

        smsServer.sendSmsWithoutTemplate(phone,content);
    }
}
