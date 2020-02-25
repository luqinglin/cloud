package me.sta.bytetcc.client;

import me.sta.bytetcc.client.impl.UserAccountHystrix;
import me.sta.bytetcc.entity.UserAccount;
import me.sta.bytetccApi.UserAccountServiceApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * UserAccountServiceApi
 *
 * @author xudong
 * @date 2019/7/30
 */
@FeignClient(value = "bytetccServer",fallback = UserAccountHystrix.class)
public interface UserAccountService extends UserAccountServiceApi{

}
