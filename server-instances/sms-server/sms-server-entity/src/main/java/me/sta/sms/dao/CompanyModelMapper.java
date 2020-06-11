package me.sta.sms.dao;

import me.sta.sms.pojo.CompanyModel;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CompanyModelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CompanyModel record);

    int insertSelective(CompanyModel record);

    CompanyModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CompanyModel record);

    int updateByPrimaryKey(CompanyModel record);

    /**
     * 分页查询
     * @param companyDomain
     * @return
     */
    List<CompanyModel> findByPage(CompanyModel companyDomain);

    /**
     * 查询是否已经存在数据
     * appid  appsecret  sdkTag相等即为重复
     *
     * @param companyModel
     * @return
     */
    CompanyModel findCompanyForExist(CompanyModel companyModel);
}
