package me.sta.sms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import me.sta.sms.dao.LogModelMapper;
import me.sta.sms.model.PageTable;
import me.sta.sms.pojo.LogModel;
import me.sta.sms.service.LogModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @CreateBy admin
 * @CreateTime 2019/7/25
 * @Description
 */
@Service
public class LogModelServiceImpl implements LogModelService {
    @Autowired
    LogModelMapper logModelMapper;
    @Override
    public PageTable<LogModel> findByPage(LogModel logModel, Integer pageNumber, Integer pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        List<LogModel> companyModels = logModelMapper.findByPage(logModel);
        return new PageTable<>(companyModels, PageInfo.of(companyModels).getTotal());
    }
}
