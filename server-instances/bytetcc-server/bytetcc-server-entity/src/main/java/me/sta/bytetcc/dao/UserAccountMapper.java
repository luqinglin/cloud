package me.sta.bytetcc.dao;


import me.sta.bytetcc.entity.UserAccount;

public interface UserAccountMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(UserAccount record);

    int insertSelective(UserAccount record);

    UserAccount selectByPrimaryKey(Integer id);

    UserAccount selectByPrimaryKeyLock(Integer id);

    int updateByPrimaryKeySelective(UserAccount record);

    int updateByPrimaryKey(UserAccount record);
}