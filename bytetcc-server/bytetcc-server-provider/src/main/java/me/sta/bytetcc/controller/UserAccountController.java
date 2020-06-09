package me.sta.bytetcc.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import me.sta.bytetcc.dao.UserAccountMapper;
import me.sta.bytetcc.entity.UserAccount;
import me.sta.bytetccApi.UserAccountServiceApi;
import org.bytesoft.compensable.Compensable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * UserAccountControllerApi
 *
 * @author xudong
 * @date 2019/7/9
 */
@RestController
@Compensable(interfaceClass = UserAccountServiceApi.class, confirmableKey = "userAccountServiceConfirm", cancellableKey = "userAccountServiceCancel")
public class UserAccountController implements UserAccountServiceApi {


    @Autowired
    private UserAccountMapper userAccountMapper;

    @Override
    @Transactional
    public String increase(Integer id, BigDecimal amount){
        UserAccount userAccount = userAccountMapper.selectByPrimaryKeyLock(id);
        userAccount.setAvailValue(userAccount.getAvailValue().add(amount));
        userAccount.setValue(userAccount.getValue().add(amount));
        int i = userAccountMapper.updateByPrimaryKeySelective(userAccount);
        System.out.println("impl");
        return i+"";
    }

    @Override
    @Transactional
    public String decrease(Integer id, BigDecimal amount) {
        UserAccount userAccount = userAccountMapper.selectByPrimaryKeyLock(id);
        userAccount.setFrozenValue(userAccount.getFrozenValue().add(amount));
        int i = userAccountMapper.updateByPrimaryKeySelective(userAccount);
        System.out.println("impl");
        return i+"";
    }
    @HystrixCommand(fallbackMethod = "findHander", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000"),
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
    })
    @Override
    @Transactional
    public UserAccount find(Integer id) {
        return userAccountMapper.selectByPrimaryKey(id);
    }

    public UserAccount findHander(Integer id) {
        System.out.println("本地降级服务");
       return null;
    }
}
