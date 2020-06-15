package me.sta.queue.configuration;

/**
 * topic队列消费者
 *
 * @author onlinever
 * @date 2018/8/17
 */
public interface TopicConsumer {
    /**
     * 消费的队列
     *
     * @return 队列
     */
    RabbitQueueEnum getQueueEnum();

    /**
     * 具体消费者的实现
     *
     * @param message 消息
     */
    void handleMessage(String message);
}