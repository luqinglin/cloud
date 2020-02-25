package me.sta.bytetcc.controller;

import me.sta.bytetcc.client.UserAccountService;
import me.sta.bytetcc.dao.OrderInfoMapper;
import me.sta.bytetcc.dao.UserAccountMapper;
import me.sta.bytetcc.entity.OrderInfo;
import me.sta.bytetcc.entity.UserAccount;
import me.sta.bytetccApi.OrderServiceApi;
import org.bytesoft.compensable.Compensable;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * OrderInfoController
 *
 * @author xudong
 * @date 2019/8/1
 *
 * 如果你要调用的服务是分布式事务那你也就必须是分布式事务
 */
@RestController
@Compensable(interfaceClass = OrderServiceApi.class)
public class OrderInfoController implements OrderServiceApi{

    @Autowired
    OrderInfoMapper orderInfoMapper;

    @Autowired
    UserAccountService userAccountService;

    @Autowired
    UserAccountMapper userAccountMapper;


    @Transactional
    public void transfer(Integer id){
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setId(id);
        orderInfo.setStatus("1");
        orderInfoMapper.updateByPrimaryKeySelective(orderInfo);
        System.out.println("impl");
        userAccountService.decrease(1, new BigDecimal("20"));
        if(id == 1){
            throw new ServiceException("aaaa");
        }
        System.out.println("account");
    }

    /**
     * 有事务管理  但是不是分布式事务方法 调用另一个服务 事务失败
     * @param id
     */
    @RequestMapping("order/transfer1")
    @Transactional
    public void transfer1(Integer id){
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setId(id);
        orderInfo.setStatus("1");
        orderInfoMapper.updateByPrimaryKeySelective(orderInfo);
        System.out.println("impl");
        userAccountService.decrease(1, new BigDecimal("20"));
        if(id == 1){
            throw new ServiceException("aaaa");
        }
        System.out.println("test");
    }

    /**
     * 没有事务管理
     * @param id
     */
    @RequestMapping("order/transfer2")
    public void transfer2(Integer id){
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setId(id);
        orderInfo.setStatus("5");
        orderInfoMapper.updateByPrimaryKeySelective(orderInfo);
        System.out.println("impl");
        UserAccount userAccount = new UserAccount();
        userAccount.setId(1);
        userAccount.setStatus("2");
        userAccountMapper.updateByPrimaryKeySelective(userAccount);
        System.out.println("test");
    }

    @RequestMapping("order/findUser")
    public void find(Integer id){
        UserAccount userAccount = userAccountService.find(id);
        System.out.println(userAccount.getAvailValue());
    }
}
