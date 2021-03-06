package me.sta.sms.dao;

import me.sta.sms.pojo.LogModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogModelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LogModel record);

    int insertSelective(LogModel record);

    LogModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LogModel record);

    int updateByPrimaryKey(LogModel record);

    List<LogModel> findByPage(LogModel logModel);
}
