package me.sta.queue.listener;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import me.sta.message.entity.RpTransactionMessage;
import me.sta.message.enums.NotifyDestinationNameEnum;
import me.sta.queue.service.MessageService;
import me.sta.queue.service.SmsServer;
import me.sta.utils.StringUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class MessageNotifyListener {




    @Autowired
    SmsServer smsServer;
    @Autowired
    MessageService messageService;
    @Autowired
    private RedisTemplate redisTemplate;
    @RabbitHandler
    @RabbitListener(queues = "MESSAGE_NOTIFY")
    public void execute(String messageBody){
        JSONObject jsonObject = JSONUtil.toBean(messageBody, JSONObject.class);
        String messageId = jsonObject.getStr("messageId");
        Boolean result = redisTemplate.opsForValue().setIfAbsent(NotifyDestinationNameEnum.MESSAGE_NOTIFY + ":" +
                messageId, "1");
        if (result){
            String phone = jsonObject.getStr("phone");
            String content = jsonObject.getStr("content");
            smsServer.sendSmsWithoutTemplate(phone,content);
            redisTemplate.delete(NotifyDestinationNameEnum.MESSAGE_NOTIFY + ":" + messageId);
        }

        messageService.deleteMessageByMessageId(messageId);
    }
}
