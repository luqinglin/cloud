package me.sta.sms.service;

import me.sta.sms.model.PageTable;
import me.sta.sms.pojo.LogModel;

/**
 * @CreateBy admin
 * @CreateTime 2019/7/25
 * @Description
 */
public interface LogModelService {
    /**
     * 分页查询
     * @param logModel
     * @param pageNumber
     * @param pageSize
     * @return
     */
    PageTable<LogModel> findByPage(LogModel logModel, Integer pageNumber, Integer pageSize);

}
