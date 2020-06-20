package me.sta.auth.service.impl;

import feign.hystrix.FallbackFactory;
import me.sta.auth.service.MessageService;
import me.sta.message.dto.PageBean;
import me.sta.message.dto.PageDto;
import me.sta.message.dto.PageParam;
import me.sta.message.entity.RpTransactionMessage;
import me.sta.message.exceptions.MessageBizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MessageServiceImpl implements FallbackFactory<MessageService> {
    private Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);

    @Override
    public MessageService create(Throwable throwable) {
        throwable.printStackTrace();
        return new MessageService() {

            @Override
            public int saveMessageWaitingConfirm(RpTransactionMessage rpTransactionMessage) throws MessageBizException {
                logger.error("error 【"+rpTransactionMessage.getMessageId()+"】----发送待确认消息异常");
                return 0;
            }

            @Override
            public void confirmAndSendMessage(String messageId) throws MessageBizException {
                logger.error("error 【"+messageId+"】 ----确认并发送消息异常");

            }

            @Override
            public int saveAndSendMessage(RpTransactionMessage rpTransactionMessage) throws MessageBizException {
                logger.error("error 【"+rpTransactionMessage.getMessageId()+"】----保存并发送待确认消息异常");
                return 0;
            }

            @Override
            public void directSendMessage(RpTransactionMessage rpTransactionMessage) throws MessageBizException {
                logger.error("error 【"+rpTransactionMessage.getMessageId()+"】----发送消息队列异常");
            }

            @Override
            public void reSendMessage(RpTransactionMessage rpTransactionMessage) throws MessageBizException {
                logger.error("error 【"+rpTransactionMessage.getMessageId()+"】----重新发送消息异常");
            }

            @Override
            public void reSendMessageByMessageId(String messageId) throws MessageBizException {

            }

            @Override
            public void setMessageToAreadlyDead(String messageId) throws MessageBizException {

            }

            @Override
            public RpTransactionMessage getMessageByMessageId(String messageId) throws MessageBizException {
                return null;
            }

            @Override
            public void deleteMessageByMessageId(String messageId) throws MessageBizException {
                logger.error("error 【"+messageId+"】----删除消息异常");
            }

            @Override
            public void reSendAllDeadMessageByQueueName(String queueName, int batchSize) throws MessageBizException {

            }

            @Override
            public PageBean listPage(PageDto pageDto) throws MessageBizException {

                return null;
            }
        };
    }
}
