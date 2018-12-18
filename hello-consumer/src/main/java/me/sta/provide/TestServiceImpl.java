package me.sta.provide;

import com.codingapi.tx.annotation.TxTransaction;
import me.sta.dao.TestMapper;
import me.sta.entity.Test;
import me.sta.service.HelloService;
import me.sta.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by luqingling on 2018/12/12.
 */
@Component
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;
    @Autowired
    private HelloService helloService;

    @Override
    @Transactional
    @TxTransaction(isStart = true)
    public void save(String name) {
        System.out.println(11);

        Test test = new Test();
        test.setName(name);
        testMapper.insert(test);

        helloService.save(name);
        //测试分布式事务
        // int a = 100/0;
        System.out.println(22);
    }
}
