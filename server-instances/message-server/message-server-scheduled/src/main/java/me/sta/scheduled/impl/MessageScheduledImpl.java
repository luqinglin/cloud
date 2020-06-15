package me.sta.scheduled.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.sta.configuration.Starter;
import me.sta.enums.PublicEnum;
import me.sta.message.dto.PageBean;
import me.sta.message.dto.PageDto;
import me.sta.message.dto.PageParam;
import me.sta.message.entity.RpTransactionMessage;
import me.sta.message.enums.MessageStatusEnum;
import me.sta.scheduled.MessageScheduled;
import me.sta.scheduled.MessageService;
import me.sta.scheduled.UserServer;
import me.sta.user.dto.UserInfoDto;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class MessageScheduledImpl implements MessageScheduled {
    private static final Log log = LogFactory.getLog(MessageScheduledImpl.class);

    @Value("${message.handle.duration}")
    private Integer duration;

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserServer userServer;

    @Override
    public void handleWaitingConfirmTimeOutMessages() {
        //todo 消息确认发送确认，需要业务方提供消息是否有效，如果有效则发送，无效则删除
        // 单条消息处理（如果后期有业务扩充，需做队列判断，做对应的业务处理。）

        int numPerPage = 2000; // 每页条数
        int maxHandlePageCount = 3; // 一次最多处理页数

        Map<String, Object> paramMap = new HashMap<String, Object>(); // 查询条件
        //paramMap.put("consumerQueue", queueName); // 队列名（可以按不同业务队列分开处理）
        paramMap.put("listPageSortType", "ASC"); // 分页查询的排序方式，正向排序
        // 获取配置的开始处理的时间
        String dateStr = getCreateTimeBefore();
        paramMap.put("createTimeBefore", dateStr);// 取存放了多久的消息
        paramMap.put("status", MessageStatusEnum.WAITING_CONFIRM.name());// 取状态为“待确认”的消息


        Map<String, RpTransactionMessage> messageMap = getMessageMap(numPerPage, maxHandlePageCount, paramMap);

        handleWaitingConfirmTimeOutMessages(messageMap);
    }

    private void handleWaitingConfirmTimeOutMessages(Map<String, RpTransactionMessage> messageMap) {
        log.debug("开始处理[waiting_confirm]状态的消息,总条数[" + messageMap.size() + "]");
        // 单条消息处理（目前该状态的消息，消费队列全部是accounting，如果后期有业务扩充，需做队列判断，做对应的业务处理。）
        for (Map.Entry<String, RpTransactionMessage> entry : messageMap.entrySet()) {
            RpTransactionMessage message = entry.getValue();
            try {

                log.debug("开始处理[waiting_confirm]消息ID为[" + message.getMessageId() + "]的消息");
                String username = message.getField1();
                UserInfoDto userInfo = userServer.getUserInfoByName(username);
                // 如果订单成功，把消息改为待处理，并发送消息
                if (userInfo!=null) {
                    // 确认并发送消息
                    messageService.confirmAndSendMessage(message.getMessageId());

                } else{
                    // 订单状态是等到支付，可以直接删除数据
                    log.debug("订单没有支付成功,删除[waiting_confirm]消息id[" + message.getMessageId() + "]的消息");
                    messageService.deleteMessageByMessageId(message.getMessageId());
                }

                log.debug("结束处理[waiting_confirm]消息ID为[" + message.getMessageId() + "]的消息");
            } catch (Exception e) {
                log.error("处理[waiting_confirm]消息ID为[" + message.getMessageId() + "]的消息异常：", e);
            }
        }

    }

    @Override
    public void handleSendingTimeOutMessage() {
        try {

            int numPerPage = 2000; // 每页条数
            int maxHandlePageCount = 3; // 一次最多处理页数

            Map<String, Object> paramMap = new HashMap<String, Object>(); // 查询条件
            //paramMap.put("consumerQueue", queueName); // 队列名（可以按不同业务队列分开处理）
            paramMap.put("listPageSortType", "ASC"); // 分页查询的排序方式，正向排序
            // 获取配置的开始处理的时间
            String dateStr = getCreateTimeBefore();
            paramMap.put("createTimeBefore", dateStr);// 取存放了多久的消息
            paramMap.put("status", MessageStatusEnum.SENDING.name());// 取状态为发送中的消息
            paramMap.put("areadlyDead", PublicEnum.NO.name());// 取存活的发送中消息

            Map<String, RpTransactionMessage> messageMap = getMessageMap(numPerPage, maxHandlePageCount, paramMap);

            handleSendingTimeOutMessage(messageMap);
        } catch (Exception e) {
            log.error("处理发送中的消息异常" + e);
        }

    }

    /**
     * 根据分页参数及查询条件批量获取消息数据.
     * @param numPerPage 每页记录数.
     * @param maxHandlePageCount 最多获取页数.
     * @param paramMap 查询参数.
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private Map<String, RpTransactionMessage> getMessageMap(int numPerPage, int maxHandlePageCount, Map<String, Object> paramMap){

        int pageNum = 1; // 当前页

        Map<String, RpTransactionMessage> messageMap = new HashMap<String, RpTransactionMessage>(); // 转换成map
        int pageCount = 1; // 总页数

        log.info("==>pageNum:" + pageNum + ", numPerPage:" + numPerPage);
        PageBean pageBean = messageService.listPage(new PageDto(new PageParam(pageNum, numPerPage),paramMap));
        if (pageBean==null) return messageMap;
        ObjectMapper mapper = new ObjectMapper();

        List<RpTransactionMessage> recordList= mapper.convertValue(pageBean.getRecordList(), new TypeReference<List<RpTransactionMessage>>() { });
        if (recordList == null || recordList.isEmpty()) {
            log.info("==>recordList is empty");
            return messageMap;
        }
        log.info("==>now page size:" + recordList.size());

        for (RpTransactionMessage message : recordList) {
            messageMap.put(message.getMessageId(), message);
        }

        pageCount = pageBean.getTotalPage(); // 总页数(可以通过这个值的判断来控制最多取多少页)
        log.info("==>pageCount:" + pageCount);
        if (pageCount > maxHandlePageCount){
            pageCount = maxHandlePageCount;
            log.info("==>set pageCount:" + pageCount);
        }

        for (pageNum = 2; pageNum <= pageCount; pageNum++) {
            log.info("==>pageNum:" + pageNum + ", numPerPage:" + numPerPage);
            pageBean = messageService.listPage(new PageDto(new PageParam(pageNum, numPerPage),paramMap));
            recordList = pageBean.getRecordList();
            if (recordList == null || recordList.isEmpty()) {
                break;
            }
            log.info("==>now page size:" + recordList.size());

            for (RpTransactionMessage message : recordList) {
                messageMap.put(message.getMessageId(), message);
            }
        }

        recordList = null;
        pageBean = null;

        return messageMap;
    }


    private void handleSendingTimeOutMessage(Map<String, RpTransactionMessage> messageMap) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        log.debug("开始处理[SENDING]状态的消息,总条数[" + messageMap.size() + "]");


        // 单条消息处理
        for (Map.Entry<String, RpTransactionMessage> entry : messageMap.entrySet()) {
            RpTransactionMessage message = entry.getValue();
            try {
                log.debug("开始处理[SENDING]消息ID为[" + message.getMessageId() + "]的消息");
                // 判断发送次数
                int maxTimes = Integer.valueOf(5);
                log.debug("[SENDING]消息ID为[" + message.getMessageId() + "]的消息,已经重新发送的次数[" + message.getMessageSendTimes() + "]");

                // 如果超过最大发送次数直接退出
                if (maxTimes < message.getMessageSendTimes()) {
                    // 标记为死亡
                    messageService.setMessageToAreadlyDead(message.getMessageId());
                    continue;
                }
                // 判断是否达到发送消息的时间间隔条件
                int reSendTimes = message.getMessageSendTimes();
                int times = 2;
                long currentTimeInMillis = Calendar.getInstance().getTimeInMillis();
                long needTime = currentTimeInMillis - times * 60 * 1000;
                long hasTime = message.getEditTime().getTime();
                // 判断是否达到了可以再次发送的时间条件
                if (hasTime > needTime) {
                    log.debug("currentTime[" + sdf.format(new Date()) + "],[SENDING]消息上次发送时间[" + sdf.format(message.getEditTime()) + "],必须过了[" + times + "]分钟才可以再发送。");
                    continue;
                }

                // 重新发送消息
                messageService.reSendMessage(message);

                log.debug("结束处理[SENDING]消息ID为[" + message.getMessageId() + "]的消息");
            } catch (Exception e) {
                log.error("处理[SENDING]消息ID为[" + message.getMessageId() + "]的消息异常：", e);
            }
        }
    }


    /**
     * 获取配置的开始处理的时间
     *
     * @return
     */
    private String getCreateTimeBefore() {

        long currentTimeInMillis = Calendar.getInstance().getTimeInMillis();
        Date date = new Date(currentTimeInMillis - Integer.valueOf(duration) * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdf.format(date);
        return dateStr;
    }
}
