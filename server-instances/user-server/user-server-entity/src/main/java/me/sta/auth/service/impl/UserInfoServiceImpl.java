package me.sta.auth.service.impl;

import me.sta.auth.dao.UserRepository;
import me.sta.auth.pojo.UserInfo;
import me.sta.auth.service.MessageService;
import me.sta.auth.service.UserInfoService;
import me.sta.auth.utils.BPwdEncoderUtil;
import me.sta.message.entity.RpTransactionMessage;
import me.sta.message.enums.NotifyDestinationNameEnum;
import me.sta.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    MessageService messageService;

    @Override
    @Transactional
    public UserInfo insertUser(String username, String  password){
        //发送短信注册成功
        String messageId = StringUtil.get32UUID();
        messageService.saveMessageWaitingConfirm(sealRpTransactionMessage(messageId,username));

        UserInfo userInfo = new UserInfo();
        userInfo.setName(username);
        userInfo.setPwd(BPwdEncoderUtil.BCryptPassword(password));
        userRepository.insertSelective(userInfo);

        messageService.confirmAndSendMessage(messageId);
        return userInfo;
    }

    @Override
    public UserInfo getUserInfoByName(String username) {
        return userRepository.findByName(username);
    }

    /**
     * 调用
     * @param
     * @return
     */
    private RpTransactionMessage sealRpTransactionMessage(String messageId,String username){


        String messageBody = "{\"phone\":\""+username+"\",\"content\":\"短信消息队列测试"+messageId+"\"}";
        RpTransactionMessage rpTransactionMessage = new RpTransactionMessage( messageId, messageBody, NotifyDestinationNameEnum.MESSAGE_NOTIFY.name());
        rpTransactionMessage.setField1(username); // 备用字段存储订单状态回查用的业务订单号

        return rpTransactionMessage;
    }
}