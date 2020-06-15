package me.sta.auth.service.impl;

import feign.hystrix.FallbackFactory;
import me.sta.auth.service.MessageService;
import me.sta.message.dto.PageBean;
import me.sta.message.dto.PageDto;
import me.sta.message.dto.PageParam;
import me.sta.message.entity.RpTransactionMessage;
import me.sta.message.exceptions.MessageBizException;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MessageServiceImpl implements FallbackFactory<MessageService> {

    @Override
    public MessageService create(Throwable throwable) {
        return new MessageService() {


            @Override
            public int saveMessageWaitingConfirm(RpTransactionMessage rpTransactionMessage) throws MessageBizException {
                return 0;
            }

            @Override
            public void confirmAndSendMessage(String messageId) throws MessageBizException {

            }

            @Override
            public int saveAndSendMessage(RpTransactionMessage rpTransactionMessage) throws MessageBizException {
                throwable.printStackTrace();
                return 0;
            }

            @Override
            public void directSendMessage(RpTransactionMessage rpTransactionMessage) throws MessageBizException {

            }

            @Override
            public void reSendMessage(RpTransactionMessage rpTransactionMessage) throws MessageBizException {

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
