package me.sta.service.impl;

import com.codingapi.tx.annotation.ITxTransaction;
import me.sta.dao.TestMapper;
import me.sta.entity.Test;
import me.sta.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by luqingling on 2018/12/13.
 */
@Component
public class TestServiceImpl implements TestService,ITxTransaction {

    @Autowired
    private TestMapper testMapper;

    @Override
    @Transactional
    public int save(String name,Integer userId) {
        //测试分布式事务
        //int a = 100/0;
        Test test = new Test();
        test.setName(name);
        return testMapper.insert(test);
    }
}
