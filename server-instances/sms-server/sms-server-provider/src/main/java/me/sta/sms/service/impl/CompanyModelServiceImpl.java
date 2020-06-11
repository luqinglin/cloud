package me.sta.sms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import me.sta.sms.dao.CompanyModelMapper;
import me.sta.sms.dao.TemplateModelMapper;
import me.sta.sms.listener.BootFinishListener;
import me.sta.sms.model.PageTable;
import me.sta.sms.pojo.CompanyModel;
import me.sta.sms.pojo.TemplateModel;
import me.sta.sms.model.CompanyTag;
import me.sta.sms.model.SmsCompany;
import me.sta.sms.service.CompanyModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 注意sdkTag不可编辑
 *
 * @CreateBy admin
 * @CreateTime 2019/7/16
 * @Description
 */
@Service
public class CompanyModelServiceImpl implements CompanyModelService {

    @Autowired
    CompanyModelMapper companyModelMapper;
    @Autowired
    TemplateModelMapper templateModelMapper;

    @Autowired
    BootFinishListener bootFinishListener;


    @Override
    public List<CompanyTag> supportCompanyList() {
        return bootFinishListener.companyTags();
    }

    @Override
    public PageTable<CompanyModel> findByPage(CompanyModel companyDomain, Integer pageNumber, Integer pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        List<CompanyModel> companyModels = companyModelMapper.findByPage(companyDomain);
        return new PageTable<>(companyModels, PageInfo.of(companyModels).getTotal());
    }

    @Override
    public SmsCompany getSmsCompanyByTag(String tag) {
        return bootFinishListener.getSmsCompanyByTag(tag);
    }

    @Override
    public void addCompany(CompanyModel companyModel) {
        CompanyModel exits = companyModelMapper.findCompanyForExist(companyModel);
        if (exits != null) {
            throw new RuntimeException("已存在相同配置");
        }
        Date d = new Date();
        companyModel.setCreatetime(d);
        companyModel.setUpdatetime(d);
        companyModelMapper.insertSelective(companyModel);
    }

    @Override
    public void updateCompanyStatus(Integer id, int status) {
        CompanyModel model = companyModelMapper.selectByPrimaryKey(id);
        if (model == null) {
            throw new RuntimeException("未找到对应的数据");
        }
        model.setStatus(status);
        model.setUpdatetime(new Date());
        int i = companyModelMapper.updateByPrimaryKeySelective(model);
        if (i > 0) {
            List<TemplateModel> templateModels = templateModelMapper.findByCompanyId(model.getId());
            //该信息被删除了  删除下面所有的模板
            if (status == 2) {
                for (TemplateModel tModel : templateModels) {
                    tModel.setStatus(2);
                    tModel.setUpdatetime(new Date());
                    templateModelMapper.updateByPrimaryKeySelective(tModel);
                }
            } else if (status == 0) {
                for (TemplateModel tModel : templateModels) {
                    tModel.setStatus(0);
                    tModel.setUpdatetime(new Date());
                    templateModelMapper.updateByPrimaryKeySelective(tModel);
                }
            }
        }

    }

    @Override
    public CompanyModel findCompanyById(Integer id) {
        return companyModelMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean updateCompany(CompanyModel companyModel) {
        try {
            companyModel.setUpdatetime(new Date());
            int i = companyModelMapper.updateByPrimaryKeySelective(companyModel);
            if (i > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
