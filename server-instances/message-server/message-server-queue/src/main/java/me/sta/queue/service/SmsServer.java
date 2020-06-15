package me.sta.queue.service;

import me.sta.queue.service.impl.SmsServerImpl;
import me.sta.sms.SmsSendServiceApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "smsServer", fallbackFactory = SmsServerImpl.class)
public interface SmsServer extends SmsSendServiceApi {
}
