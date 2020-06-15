package me.sta.message.entity;

import javax.persistence.Column;

/**
 * 持久化消息实体
 * <p>
 * 龙果学院：www.roncoo.com
 *
 * @author：shenjialong
 */
public class RpTransactionMessage extends BaseEntity {

    private static final long serialVersionUID = 1757377457814546156L;
    @Column(name = "message_id")
    private String messageId;
    @Column(name = "message_body")
    private String messageBody;
    @Column(name = "message_data_type")
    private String messageDataType;
    @Column(name = "consumer_queue")
    private String consumerQueue;
    @Column(name = "message_send_times")
    private Integer messageSendTimes;
    @Column(name = "areadly_dead")
    private String areadlyDead;
    @Column(name = "field1")
    private String field1;
    @Column(name = "field2")
    private String field2;
    @Column(name = "field3")
    private String field3;

    public RpTransactionMessage() {
        super();
    }


    public RpTransactionMessage(String messageId, String messageBody, String consumerQueue) {
        super();
        this.messageId = messageId;
        this.messageBody = messageBody;
        this.consumerQueue = consumerQueue;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public String getMessageDataType() {
        return messageDataType;
    }

    public void setMessageDataType(String messageDataType) {
        this.messageDataType = messageDataType;
    }

    public String getConsumerQueue() {
        return consumerQueue;
    }

    public void setConsumerQueue(String consumerQueue) {
        this.consumerQueue = consumerQueue;
    }

    public Integer getMessageSendTimes() {
        return messageSendTimes;
    }

    public void setMessageSendTimes(Integer messageSendTimes) {
        this.messageSendTimes = messageSendTimes;
    }

    public String getAreadlyDead() {
        return areadlyDead;
    }

    public void setAreadlyDead(String areadlyDead) {
        this.areadlyDead = areadlyDead;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    public String getField3() {
        return field3;
    }

    public void setField3(String field3) {
        this.field3 = field3;
    }

    public void addSendTimes() {
        messageSendTimes++;
    }

}