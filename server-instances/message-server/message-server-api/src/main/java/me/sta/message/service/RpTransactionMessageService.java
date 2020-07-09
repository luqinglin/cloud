package me.sta.message.service;

import feign.Headers;
import feign.RequestLine;
import me.sta.message.dto.PageBean;
import me.sta.message.dto.PageDto;
import me.sta.message.dto.PageParam;
import me.sta.message.entity.RpTransactionMessage;
import me.sta.message.exceptions.MessageBizException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

public interface RpTransactionMessageService {

    /**
     * 预存储消息.
     */
    @RequestMapping(value = "/message/saveMessageWaitingConfirm", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public int saveMessageWaitingConfirm(@RequestBody RpTransactionMessage rpTransactionMessage) throws MessageBizException;


    /**
     * 确认并发送消息.
     */
    @RequestMapping(value = "/message/confirmAndSendMessage", method = RequestMethod.GET)
    public void confirmAndSendMessage(@RequestParam String messageId) throws MessageBizException;


    /**
     * 存储并发送消息.
     */
    @RequestMapping(value = "/message/saveAndSendMessage", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public int saveAndSendMessage(@RequestBody RpTransactionMessage rpTransactionMessage) throws MessageBizException;


    /**
     * 直接发送消息.
     */
    @RequestMapping(value = "/message/directSendMessage", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void directSendMessage(@RequestBody RpTransactionMessage rpTransactionMessage) throws MessageBizException;


    /**
     * 重发消息.
     */
    @RequestMapping(value = "/message/reSendMessage", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void reSendMessage(@RequestBody RpTransactionMessage rpTransactionMessage) throws MessageBizException;


    /**
     * 根据messageId重发某条消息.
     */
    @RequestMapping(value = "/message/reSendMessageByMessageId", method = RequestMethod.GET)
    public void reSendMessageByMessageId(@RequestParam String messageId) throws MessageBizException;


    /**
     * 将消息标记为死亡消息.
     */
    @RequestMapping(value = "/message/setMessageToAreadlyDead", method = RequestMethod.GET)
    public void setMessageToAreadlyDead(@RequestParam String messageId) throws MessageBizException;


    /**
     * 根据消息ID获取消息
     */
    @RequestMapping(value = "/message/getMessageByMessageId", method = RequestMethod.GET)
    public RpTransactionMessage getMessageByMessageId(@RequestParam String messageId) throws MessageBizException;

    /**
     * 根据消息ID删除消息
     */
    @RequestMapping(value = "/message/deleteMessageByMessageId", method = RequestMethod.GET)
    public void deleteMessageByMessageId(@RequestParam String messageId) throws MessageBizException;


    /**
     * 重发某个消息队列中的全部已死亡的消息.
     */
    @RequestMapping(value = "/message/reSendAllDeadMessageByQueueName", method = RequestMethod.GET)
    public void reSendAllDeadMessageByQueueName(@RequestParam("queueName") String queueName, @RequestParam("batchSize") int batchSize) throws MessageBizException;

    /**
     * 获取分页数据
     */
    @RequestMapping(value = "/message/listPage")
    PageBean listPage(@RequestBody PageDto pageDto) throws MessageBizException;

}

