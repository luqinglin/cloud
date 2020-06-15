package me.sta.queue.service;

import me.sta.message.service.RpTransactionMessageService;
import me.sta.queue.service.impl.MessageServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "message-service", fallbackFactory = MessageServiceImpl.class)
public interface MessageService extends RpTransactionMessageService {
}
