package me.sta.configuration;

import me.sta.scheduled.MessageScheduled;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;

@Component
public class Starter implements InitializingBean {

    private static final Log log = LogFactory.getLog(Starter.class);


    @Autowired
    private Executor taskExector;
    @Autowired
    private MessageScheduled messageScheduled;

    @Override
    public void afterPropertiesSet() throws Exception {
        // 开一个子线程处理状态为“待确认”但已超时的消息.
        taskExector.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {

                    log.info("执行(处理[waiting_confirm]状态的消息)任务开始");
                    messageScheduled.handleWaitingConfirmTimeOutMessages();
                    log.info("执行(处理[waiting_confirm]状态的消息)任务结束");

                    try {
                        log.info("[waiting_confirm]睡眠60秒");
                        Thread.sleep(60000);
                    } catch (InterruptedException e) {
                    }
                }
            }
        });

        // 开一个子线程处理状态为“发送中”但超时没有被成功消费确认的消息
        taskExector.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    log.info("执行(处理[SENDING]的消息)任务开始");
                    messageScheduled.handleSendingTimeOutMessage();
                    log.info("执行(处理[SENDING]的消息)任务结束");
                    try {
                        log.info("[SENDING]睡眠60秒");
                        Thread.sleep(60000);
                    } catch (InterruptedException e) {
                    }
                }
            }
        });
    }
}
