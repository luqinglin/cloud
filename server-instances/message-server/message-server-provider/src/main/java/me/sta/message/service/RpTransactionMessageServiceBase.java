package me.sta.message.service;

import me.sta.message.dto.PageBean;
import me.sta.message.dto.PageParam;
import me.sta.message.entity.RpTransactionMessage;
import me.sta.message.exceptions.MessageBizException;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

public interface RpTransactionMessageServiceBase {

    /**
     * 预存储消息.
     */
    public int saveMessageWaitingConfirm(RpTransactionMessage rpTransactionMessage) throws MessageBizException;


    /**
     * 确认并发送消息.
     */
    public void confirmAndSendMessage(String messageId) throws MessageBizException;


    /**
     * 存储并发送消息.
     */
    public int saveAndSendMessage(RpTransactionMessage rpTransactionMessage) throws MessageBizException;


    /**
     * 直接发送消息.
     */
    public void directSendMessage(RpTransactionMessage rpTransactionMessage) throws MessageBizException;


    /**
     * 重发消息.
     */
    public void reSendMessage(RpTransactionMessage rpTransactionMessage) throws MessageBizException;


    /**
     * 根据messageId重发某条消息.
     */
    public void reSendMessageByMessageId(String messageId) throws MessageBizException;


    /**
     * 将消息标记为死亡消息.
     */
    public void setMessageToAreadlyDead(String messageId) throws MessageBizException;


    /**
     * 根据消息ID获取消息
     */
    public RpTransactionMessage getMessageByMessageId(String messageId) throws MessageBizException;

    /**
     * 根据消息ID删除消息
     */
    public void deleteMessageByMessageId(String messageId) throws MessageBizException;


    /**
     * 重发某个消息队列中的全部已死亡的消息.
     */
    public void reSendAllDeadMessageByQueueName(String queueName, int batchSize) throws MessageBizException;


    PageBean listPage( PageParam pageParam, Map<String, Object> paramMap) throws MessageBizException;

}
