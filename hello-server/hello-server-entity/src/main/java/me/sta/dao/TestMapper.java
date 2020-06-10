package me.sta.dao;

import me.sta.entity.Test;

/**
 * Created by luqingling on 2018/12/13.
 */
public interface TestMapper {

    //    @Insert("insert into test (name) values (#{name})")
//    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int insert(Test test);

    int insertSelective(Test test);

    void deleteByPrimaryKey(Integer id);

    Test findByUserIdAndId(int userId, Long id);
}
