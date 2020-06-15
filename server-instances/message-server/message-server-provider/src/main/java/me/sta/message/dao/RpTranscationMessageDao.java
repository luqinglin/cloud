package me.sta.message.dao;

import me.sta.message.dto.PageDataResult;
import me.sta.message.entity.RpTransactionMessage;
import org.springframework.stereotype.Repository;
import tk.mapper.MyMapper;

import java.util.List;
import java.util.Map;

@Repository
public interface RpTranscationMessageDao extends MyMapper<RpTransactionMessage> {


    RpTransactionMessage getBy(Map<String, Object> paramMap);

    List<RpTransactionMessage> listPage(Map<String, Object> paramMap);

    void deleteMessageByMessageId(String messageId);

}