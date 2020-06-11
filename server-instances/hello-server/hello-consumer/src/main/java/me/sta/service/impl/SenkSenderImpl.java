package me.sta.service.impl;

import me.sta.service.IMessageProvider;
import me.sta.service.SinkSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;

/**
 * Created by luqingling on 2018/12/5.
 */
@EnableBinding(SinkSender.class)
public class SenkSenderImpl implements IMessageProvider {

    @Autowired
    private SinkSender senkSender;
    @Override
    public void send(String a){
        boolean send = senkSender.output().send(MessageBuilder.withPayload(a).build());
        System.out.println(send);
    }
}
