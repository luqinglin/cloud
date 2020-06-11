package me.sta.sms.dao;

import me.sta.sms.pojo.TemplateModel;

import java.util.List;

public interface TemplateModelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TemplateModel record);

    int insertSelective(TemplateModel record);

    TemplateModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TemplateModel record);

    int updateByPrimaryKey(TemplateModel record);


    TemplateModel findByTplNo(String tpl);

    TemplateModel findTplExist(TemplateModel templateModel);

    List<TemplateModel> findByPage(TemplateModel model);

    List<TemplateModel> findByCompanyId(Integer id);

    TemplateModel findByTplNoEffect(String tplNo);
}
