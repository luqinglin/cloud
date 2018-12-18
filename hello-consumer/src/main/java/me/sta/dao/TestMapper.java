package me.sta.dao;

import me.sta.entity.Test;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by luqingling on 2018/12/13.
 */
@Mapper
public interface TestMapper {

//    @Insert("insert into test (name) values (#{name})")
//    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int insert(Test test);

    int insertSelective(Test test);

    void deleteByPrimaryKey(Integer id);
}
