package me.sta.bytetcc.client;

import feign.hystrix.FallbackFactory;
import me.sta.bytetcc.client.impl.UserAccountHystrix;
import me.sta.bytetcc.entity.UserAccount;
import me.sta.bytetccApi.UserAccountServiceApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * UserAccountServiceApi
 *
 * @author xudong
 * @date 2019/7/30
 */
@FeignClient(value = "bytetccServer",fallback = UserAccountHystrix.class)
@EnableAspectJAutoProxy(proxyTargetClass = true)
public interface UserAccountService extends UserAccountServiceApi{
}
