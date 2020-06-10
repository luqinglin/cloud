package me.sta.consumer;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

public interface ReceiveService {
    @Input("exchange")
    MessageChannel input();
}
