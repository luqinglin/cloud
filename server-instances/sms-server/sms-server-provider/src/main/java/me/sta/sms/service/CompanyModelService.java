package me.sta.sms.service;

import me.sta.sms.model.PageTable;
import me.sta.sms.pojo.CompanyModel;
import me.sta.sms.model.CompanyTag;
import me.sta.sms.model.SmsCompany;

import java.util.List;

/**
 * @CreateBy admin
 * @CreateTime 2019/7/16
 * @Description
 */
public interface CompanyModelService {

    /**
     * 获取支持公司的tag列表
     */
    List<CompanyTag> supportCompanyList();

    PageTable<CompanyModel> findByPage(CompanyModel companyDomain, Integer pageNumber, Integer pageSize);

    /**
     * 根据tag获取对应的平台信息
     *
     * @param tag
     * @return
     */
    SmsCompany getSmsCompanyByTag(String tag);

    /**
     * 添加一条数据
     *
     * @param companyModel
     */
    void addCompany(CompanyModel companyModel);

    /**
     * 更新状态
     *
     * @param id
     * @param status
     */
    void updateCompanyStatus(Integer id, int status);

    /**
     * 通过id查找公司信息
     *
     * @param id
     * @return
     */
    CompanyModel findCompanyById(Integer id);

    /**
     * 更新公司信息
     *
     * @param companyModel
     */
    boolean updateCompany(CompanyModel companyModel);
}
