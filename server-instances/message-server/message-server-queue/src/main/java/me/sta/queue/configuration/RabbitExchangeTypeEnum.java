package me.sta.queue.configuration;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.TopicExchange;

/**
 * @author onlinever
 * @date 2018/09/06
 */
public enum RabbitExchangeTypeEnum {

    /**
     * 死信转发方式延迟队列
     */
    TTL_QUEUE(1, DirectExchange.class),
    /**
     * 正常队列
     */
    NORMAL_QUEUE(2, DirectExchange.class),
    /**
     * 广播队列
     */
    FANOUT_QUEUE(3, FanoutExchange.class),
    /**
     * topic队列
     */
    TOPIC_QUEUE(4, TopicExchange.class);


    /**
     * 队列routeKey
     */
    private int index;

    /**
     * 交换机class
     */
    private Class exchangeClazz;


    RabbitExchangeTypeEnum(int index, Class exchangeClazz) {
        this.index = index;
        this.exchangeClazz = exchangeClazz;
    }

    public int getIndex() {
        return index;
    }

    public Class getExchangeClazz() {
        return exchangeClazz;
    }
}