package me.sta.sms.dao;

import me.sta.sms.pojo.ResModel;

import java.util.List;

public interface ResModelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ResModel record);

    int insertSelective(ResModel record);

    ResModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ResModel record);

    int updateByPrimaryKey(ResModel record);

    List<ResModel> findLeftMenu();
}
