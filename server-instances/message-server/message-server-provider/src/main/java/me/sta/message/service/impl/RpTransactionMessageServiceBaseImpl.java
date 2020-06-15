package me.sta.message.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import me.sta.enums.PublicEnum;
import me.sta.message.dao.RpTranscationMessageDao;
import me.sta.message.dto.PageBean;
import me.sta.message.dto.PageParam;
import me.sta.message.entity.RpTransactionMessage;
import me.sta.message.enums.MessageStatusEnum;
import me.sta.message.exceptions.MessageBizException;
import me.sta.message.service.RpTransactionMessageServiceBase;
import me.sta.utils.StringUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RpTransactionMessageServiceBaseImpl implements RpTransactionMessageServiceBase {

    private static final Log log = LogFactory.getLog(RpTransactionMessageServiceBaseImpl.class);

    @Autowired
    private RpTranscationMessageDao rpTransactionMessageDao;

    @Autowired
    private AmqpTemplate rabbitmqTemplate;

    public int saveMessageWaitingConfirm(RpTransactionMessage message) {

        if (message == null) {
            throw new MessageBizException(MessageBizException.SAVA_MESSAGE_IS_NULL, "保存的消息为空");
        }

        if (StringUtil.isEmpty(message.getConsumerQueue())) {
            throw new MessageBizException(MessageBizException.MESSAGE_CONSUMER_QUEUE_IS_NULL, "消息的消费队列不能为空 ");
        }

        message.setEditTime(new Date());
        message.setStatus(MessageStatusEnum.WAITING_CONFIRM.name());
        message.setAreadlyDead(PublicEnum.NO.name());
        message.setMessageSendTimes(0);
        return rpTransactionMessageDao.insert(message);
    }


    public void confirmAndSendMessage(String messageId) {
        final RpTransactionMessage message = getMessageByMessageId(messageId);
        if (message == null) {
            throw new MessageBizException(MessageBizException.SAVA_MESSAGE_IS_NULL, "根据消息id查找的消息为空");
        }

        message.setStatus(MessageStatusEnum.SENDING.name());
        message.setEditTime(new Date());
        rpTransactionMessageDao.updateByPrimaryKeySelective(message);
        rabbitmqTemplate.convertAndSend(message.getConsumerQueue(), message.getMessageBody());
    }


    public int saveAndSendMessage(RpTransactionMessage message) {

        if (message == null) {
            throw new MessageBizException(MessageBizException.SAVA_MESSAGE_IS_NULL, "保存的消息为空");
        }

        if (StringUtil.isEmpty(message.getConsumerQueue())) {
            throw new MessageBizException(MessageBizException.MESSAGE_CONSUMER_QUEUE_IS_NULL, "消息的消费队列不能为空 ");
        }

        message.setStatus(MessageStatusEnum.SENDING.name());
        message.setAreadlyDead(PublicEnum.NO.name());
        message.setMessageSendTimes(0);
        message.setEditTime(new Date());
        int result = rpTransactionMessageDao.insert(message);

        rabbitmqTemplate.convertAndSend(message.getConsumerQueue(), message.getMessageBody());

        return result;
    }


    public void directSendMessage(final RpTransactionMessage message) {

        if (message == null) {
            throw new MessageBizException(MessageBizException.SAVA_MESSAGE_IS_NULL, "保存的消息为空");
        }

        if (StringUtil.isEmpty(message.getConsumerQueue())) {
            throw new MessageBizException(MessageBizException.MESSAGE_CONSUMER_QUEUE_IS_NULL, "消息的消费队列不能为空 ");
        }

        rabbitmqTemplate.convertAndSend(message.getConsumerQueue(), message.getMessageBody());
    }


    public void reSendMessage(final RpTransactionMessage message) {

        if (message == null) {
            throw new MessageBizException(MessageBizException.SAVA_MESSAGE_IS_NULL, "保存的消息为空");
        }

        if (StringUtil.isEmpty(message.getConsumerQueue())) {
            throw new MessageBizException(MessageBizException.MESSAGE_CONSUMER_QUEUE_IS_NULL, "消息的消费队列不能为空 ");
        }

        message.addSendTimes();
        message.setEditTime(new Date());
        rpTransactionMessageDao.updateByPrimaryKeySelective(message);

        rabbitmqTemplate.convertAndSend(message.getConsumerQueue(), message.getMessageBody());
    }


    public void reSendMessageByMessageId(String messageId) {
        final RpTransactionMessage message = getMessageByMessageId(messageId);
        if (message == null) {
            throw new MessageBizException(MessageBizException.SAVA_MESSAGE_IS_NULL, "根据消息id查找的消息为空");
        }

        int maxTimes = Integer.valueOf(5);
        if (message.getMessageSendTimes() >= maxTimes) {
            message.setAreadlyDead(PublicEnum.YES.name());
        }

        message.setEditTime(new Date());
        message.setMessageSendTimes(message.getMessageSendTimes() + 1);
        rpTransactionMessageDao.updateByPrimaryKeySelective(message);

        rabbitmqTemplate.convertAndSend(message.getConsumerQueue(), message.getMessageBody());
    }


    public void setMessageToAreadlyDead(String messageId) {
        RpTransactionMessage message = getMessageByMessageId(messageId);
        if (message == null) {
            throw new MessageBizException(MessageBizException.SAVA_MESSAGE_IS_NULL, "根据消息id查找的消息为空");
        }

        message.setAreadlyDead(PublicEnum.YES.name());
        message.setEditTime(new Date());
        rpTransactionMessageDao.updateByPrimaryKeySelective(message);
    }


    public RpTransactionMessage getMessageByMessageId(String messageId) {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("messageId", messageId);

        return rpTransactionMessageDao.getBy(paramMap);
    }


    public void deleteMessageByMessageId(String messageId) {
        rpTransactionMessageDao.deleteMessageByMessageId(messageId);
    }


    @SuppressWarnings("unchecked")
    public void reSendAllDeadMessageByQueueName(String queueName, int batchSize) {
        log.info("==>reSendAllDeadMessageByQueueName");

        int numPerPage = 1000;
        if (batchSize > 0 && batchSize < 100) {
            numPerPage = 100;
        } else if (batchSize > 100 && batchSize < 5000) {
            numPerPage = batchSize;
        } else if (batchSize > 5000) {
            numPerPage = 5000;
        } else {
            numPerPage = 1000;
        }

        int pageNum = 1;
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("consumerQueue", queueName);
        paramMap.put("areadlyDead", PublicEnum.YES.name());
        paramMap.put("listPageSortType", "ASC");


        Map<String, RpTransactionMessage> messageMap = new HashMap<String, RpTransactionMessage>();
        List<RpTransactionMessage> recordList = new ArrayList<RpTransactionMessage>();
        long pageCount = 1;


        PageHelper.startPage(pageNum, numPerPage);
        List<RpTransactionMessage> list = rpTransactionMessageDao.listPage(paramMap);
        PageInfo<RpTransactionMessage> pageInfo = new PageInfo<RpTransactionMessage>(list);


        if (recordList == null || recordList.isEmpty()) {
            log.info("==>recordList is empty");
            return;
        }
        pageCount = pageInfo.getTotal();
        for (final Object obj : list) {
            final RpTransactionMessage message = (RpTransactionMessage) obj;
            messageMap.put(message.getMessageId(), message);
        }

        for (pageNum = 2; pageNum <= pageCount; pageNum++) {
            PageHelper.startPage(pageNum, numPerPage);
            list = rpTransactionMessageDao.listPage(paramMap);
            if (recordList == null || recordList.isEmpty()) {
                break;
            }

            for (final Object obj : list) {
                final RpTransactionMessage message = (RpTransactionMessage) obj;
                messageMap.put(message.getMessageId(), message);
            }
        }

        recordList = null;
        list = null;

        for (Map.Entry<String, RpTransactionMessage> entry : messageMap.entrySet()) {
            final RpTransactionMessage message = entry.getValue();

            message.setEditTime(new Date());
            message.setMessageSendTimes(message.getMessageSendTimes() + 1);
            rpTransactionMessageDao.updateByPrimaryKeySelective(message);

            rabbitmqTemplate.convertAndSend(message.getConsumerQueue(), message.getMessageBody());
        }

    }

    @Override
    public PageBean listPage(PageParam pageParam, Map<String, Object> paramMap) throws MessageBizException {
        PageHelper.startPage(pageParam.getPageNum(), pageParam.getNumPerPage());
        List<RpTransactionMessage> list = rpTransactionMessageDao.listPage(paramMap);
        PageInfo<RpTransactionMessage> pageInfo = new PageInfo<RpTransactionMessage>(list);
        PageBean<RpTransactionMessage> r = new PageBean<>();
        r.setRecordList(pageInfo.getList());
        r.setTotalCount(Integer.valueOf(pageInfo.getTotal()+""));
        r.setNumPerPage(pageInfo.getPageSize());
        r.setCurrentPage(pageInfo.getPageNum());
        return r;
    }
}
