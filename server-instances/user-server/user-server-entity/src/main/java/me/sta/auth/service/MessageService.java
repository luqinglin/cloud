package me.sta.auth.service;

import me.sta.auth.service.impl.MessageServiceImpl;
import me.sta.message.service.RpTransactionMessageService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "message-service", fallbackFactory = MessageServiceImpl.class)
public interface MessageService extends RpTransactionMessageService {
}
