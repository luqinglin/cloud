package me.sta.service;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.MessageChannel;

/**
 * Created by luqingling on 2018/12/6.
 */
public interface SinkSender {

    @Output(Sink.INPUT)
    MessageChannel output();
}
