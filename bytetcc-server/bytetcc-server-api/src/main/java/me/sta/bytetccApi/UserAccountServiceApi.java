package me.sta.bytetccApi;

import me.sta.bytetcc.entity.UserAccount;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * UserAccountServiceApi
 *
 * @author xudong
 * @date 2019/7/9
 */
public interface UserAccountServiceApi {

    @RequestMapping(value = "account/increase",method = RequestMethod.GET)
    public String increase(@RequestParam("id") Integer id, @RequestParam("amount") BigDecimal amount);

    @RequestMapping(value = "account/decrease",method = RequestMethod.GET)
    public String decrease(@RequestParam("id") Integer id, @RequestParam("amount") BigDecimal amount);

    @RequestMapping(value = "account/find",method = RequestMethod.GET)
    public UserAccount find(@RequestParam("id") Integer id);

}
