package me.sta.bytetcc.service;


import me.sta.bytetcc.dao.UserAccountMapper;
import me.sta.bytetcc.entity.UserAccount;
import me.sta.bytetccApi.UserAccountServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * UserAccountServiceApiCancel
 *
 * @author xudong
 * @date 2019/7/9
 */
@Service("userAccountServiceCancel")
public class UserAccountServiceApiCancel implements UserAccountServiceApi {

    @Autowired
    private UserAccountMapper userAccountMapper;

    @Override
    @Transactional
    public String increase(Integer id, BigDecimal amount){
        UserAccount userAccount = userAccountMapper.selectByPrimaryKeyLock(id);
        userAccount.setAvailValue(userAccount.getAvailValue().subtract(amount));
        userAccount.setValue(userAccount.getValue().subtract(amount));
        int i = userAccountMapper.updateByPrimaryKeySelective(userAccount);
        System.out.println("cancel");
        return i+"";
    }

    @Override
    @Transactional
    public String decrease(Integer id, BigDecimal amount) {
        UserAccount userAccount = userAccountMapper.selectByPrimaryKeyLock(id);
        userAccount.setFrozenValue(userAccount.getFrozenValue().subtract(amount));
        int i = userAccountMapper.updateByPrimaryKeySelective(userAccount);
        System.out.println("cancel");
        return i+"";
    }

    @Override
    @Transactional
    public UserAccount find(Integer id) {
        System.out.println("findCancel");
        return null;
    }
}
