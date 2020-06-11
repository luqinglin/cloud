package me.sta.sms.service;

import me.sta.sms.model.PageTable;
import me.sta.sms.pojo.TemplateModel;

/**
 * @CreateBy admin
 * @CreateTime 2019/7/17
 * @Description
 */
public interface TemplateModelService {
    /**
     * 添加模板
     *
     * @param templateModel
     * @return
     */
    boolean addTemplateModel(TemplateModel templateModel);

    /**
     * 分页查找
     * @param model
     * @param pageNumber
     * @param pageSize
     * @return
     */
    PageTable<TemplateModel> findByPage(TemplateModel model, Integer pageNumber, Integer pageSize);

    /**
     * 更新状态
     * @param id
     * @param status
     */
    void updateCompanyStatus(Integer id, int status);

    TemplateModel findById(Integer id);

    boolean updateTemplate(TemplateModel tplModel);
}
