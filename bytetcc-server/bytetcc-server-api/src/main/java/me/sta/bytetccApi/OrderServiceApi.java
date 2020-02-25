package me.sta.bytetccApi;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * OrderServiceApi
 *
 * @author xudong
 * @date 2019/8/2
 */
public interface OrderServiceApi {

    @RequestMapping(value = "order/transfer",method = RequestMethod.GET)
    public void transfer(Integer id);
}
