package me.sta.bytetcc.controller;

import me.sta.bytetcc.dao.UserAccountMapper;
import me.sta.bytetcc.entity.UserAccount;
import me.sta.bytetccApi.UserAccountServiceApi;
import org.bytesoft.compensable.Compensable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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

    @Override
    @Transactional
    public UserAccount find(Integer id) {
        return userAccountMapper.selectByPrimaryKey(id);
    }
}
