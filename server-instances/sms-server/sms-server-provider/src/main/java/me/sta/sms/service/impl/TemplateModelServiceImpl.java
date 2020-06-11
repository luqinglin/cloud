package me.sta.sms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import me.sta.sms.dao.TemplateModelMapper;
import me.sta.sms.model.PageTable;
import me.sta.sms.pojo.TemplateModel;
import me.sta.sms.service.TemplateModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @CreateBy admin
 * @CreateTime 2019/7/17
 * @Description
 */
@Service
public class TemplateModelServiceImpl implements TemplateModelService {

    @Autowired
    TemplateModelMapper templateModelMapper;

    @Override
    public boolean addTemplateModel(TemplateModel templateModel) {

        TemplateModel model = templateModelMapper.findTplExist(templateModel);
        if (model != null) {
            throw new RuntimeException("该配置内容已存在");
        }


        String tpl = generalTemplateNo();
        while (templateModelMapper.findByTplNo(tpl) != null) {
            tpl = generalTemplateNo();
        }
        templateModel.setTplNo(tpl);
        return templateModelMapper.insertSelective(templateModel) > 0;
    }

    @Override
    public PageTable<TemplateModel> findByPage(TemplateModel model, Integer pageNumber, Integer pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        List<TemplateModel> companyModels = templateModelMapper.findByPage(model);
        return new PageTable<>(companyModels, PageInfo.of(companyModels).getTotal());
    }

    @Override
    public void updateCompanyStatus(Integer id, int status) {
        TemplateModel model = templateModelMapper.selectByPrimaryKey(id);
        if (model == null) {
            throw new RuntimeException("未找到对应的数据");
        }
        model.setStatus(status);
        templateModelMapper.updateByPrimaryKeySelective(model);
    }

    @Override
    public TemplateModel findById(Integer id) {
        return templateModelMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean updateTemplate(TemplateModel tplModel) {
        try {
            tplModel.setUpdatetime(new Date());
            int i = templateModelMapper.updateByPrimaryKeySelective(tplModel);
            if (i > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    private static String generalTemplateNo() {
        String prefix = "tpl_";
        String code = UUID.randomUUID().toString();
        code = code.substring(code.lastIndexOf("-") + 1);
        return prefix + code;
    }

}
