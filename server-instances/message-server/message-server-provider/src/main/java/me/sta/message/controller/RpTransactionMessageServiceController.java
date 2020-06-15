package me.sta.message.controller;

import me.sta.message.dto.PageBean;
import me.sta.message.dto.PageDto;
import me.sta.message.dto.PageParam;
import me.sta.message.entity.RpTransactionMessage;
import me.sta.message.enums.NotifyDestinationNameEnum;
import me.sta.message.exceptions.MessageBizException;
import me.sta.message.service.RpTransactionMessageService;
import me.sta.message.service.RpTransactionMessageServiceBase;
import me.sta.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class RpTransactionMessageServiceController implements RpTransactionMessageService {

    @Autowired
    private RpTransactionMessageServiceBase rpTransactionMessageServiceBase;

    @Override
    public int saveMessageWaitingConfirm(RpTransactionMessage rpTransactionMessage) throws MessageBizException {
        return rpTransactionMessageServiceBase.saveMessageWaitingConfirm(rpTransactionMessage);
    }

    @Override
    public void confirmAndSendMessage(String messageId) throws MessageBizException {
        rpTransactionMessageServiceBase.confirmAndSendMessage(messageId);
    }

    @Override
    public int saveAndSendMessage(RpTransactionMessage rpTransactionMessage) throws MessageBizException {
        return rpTransactionMessageServiceBase.saveAndSendMessage(rpTransactionMessage);
    }

    @Override
    public void directSendMessage(RpTransactionMessage rpTransactionMessage) throws MessageBizException {
        rpTransactionMessageServiceBase.directSendMessage(rpTransactionMessage);
    }

    @Override
    public void reSendMessage(RpTransactionMessage rpTransactionMessage) throws MessageBizException {
        rpTransactionMessageServiceBase.reSendMessage(rpTransactionMessage);
    }

    @Override
    public void reSendMessageByMessageId(String messageId) throws MessageBizException {
        rpTransactionMessageServiceBase.reSendMessageByMessageId(messageId);
    }

    @Override
    public void setMessageToAreadlyDead(String messageId) throws MessageBizException {
        rpTransactionMessageServiceBase.setMessageToAreadlyDead(messageId);
    }

    @Override
    public RpTransactionMessage getMessageByMessageId(String messageId) throws MessageBizException {
        return rpTransactionMessageServiceBase.getMessageByMessageId(messageId);
    }

    @Override
    public void deleteMessageByMessageId(String messageId) throws MessageBizException {
        rpTransactionMessageServiceBase.deleteMessageByMessageId(messageId);
    }

    @Override
    public void reSendAllDeadMessageByQueueName(String queueName, int batchSize) throws MessageBizException {
        rpTransactionMessageServiceBase.reSendAllDeadMessageByQueueName(queueName, batchSize);
    }

    @Override
    public PageBean listPage(PageDto pageDto) throws MessageBizException {
        return rpTransactionMessageServiceBase.listPage(pageDto.getPageParam(),pageDto.getParamMap());
    }
}
