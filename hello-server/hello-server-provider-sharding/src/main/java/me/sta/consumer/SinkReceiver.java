package me.sta.consumer;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * Created by luqingling on 2018/12/5.
 */
@EnableBinding(Sink.class)
public class SinkReceiver {


    @StreamListener(Sink.INPUT)
    public void receive(String payload) {
        System.out.println(payload);
    }
}
