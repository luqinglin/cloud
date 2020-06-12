package me.sta.client;

import me.sta.client.impl.SmsServiceImpl;
import me.sta.sms.SmsSendServiceApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by luqingling on 2018/11/2.
 */
@FeignClient(value = "smsServer",fallback = SmsServiceImpl.class)
public interface SmsService extends SmsSendServiceApi {}


