package me.sta.bytetcc.client.impl;


import me.sta.bytetcc.client.UserAccountService;
import me.sta.bytetcc.entity.UserAccount;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * UserAccountServiceImpl
 *
 * @author xudong
 * @date 2019/7/30
 */
@Component
public class UserAccountHystrix implements UserAccountService{

    @Override
    public String increase(Integer id, BigDecimal amount) {
        System.out.println("increase降级");
        return null;
    }

    @Override
    public String decrease(Integer id, BigDecimal amount) {
        System.out.println("decrease降级");
        return null;
    }

    @Override
    public UserAccount find(Integer id) {
        System.out.println("find降级");
        return null;
    }
}
