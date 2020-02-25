package me.sta.sms.controller;

import me.sta.sms.model.DataTable;
import me.sta.sms.model.PageTable;
import me.sta.sms.model.RestResult;
import me.sta.sms.pojo.CompanyModel;
import me.sta.sms.send.CompanyTag;
import me.sta.sms.send.SmsCompany;
import me.sta.sms.service.CompanyModelService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @CreateBy admin
 * @CreateTime 2019/7/10
 * @Description
 */
@Controller
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    CompanyModelService companyModelService;


    @GetMapping("/tableData")
    @ResponseBody
    public Object tableData(@RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber,
                            @RequestParam(value = "pageSize", defaultValue = "30") Integer pageSize,
                            @RequestParam(value = "comName", required = false) String comName,
                            @RequestParam(value = "sdktag", required = false) String sdktag,
                            @RequestParam(value = "smsSendType", required = false, defaultValue = "-1") Integer smsSendType,
                            @RequestParam(value = "status", required = false, defaultValue = "-1") Integer status) {
        CompanyModel companyDomain = new CompanyModel();
        if (StringUtils.isNotEmpty(comName)) {
            companyDomain.setComName(comName);
        }
        if (StringUtils.isNotEmpty(sdktag)) {
            companyDomain.setSdktag(sdktag);
        }

        if (smsSendType != null && smsSendType != -1) {
            companyDomain.setSmsSendType(smsSendType);
        }
        if (status != null && status != -1) {
            companyDomain.setStatus(status);
        }

        PageTable<CompanyModel> byPage = companyModelService.findByPage(companyDomain, pageNumber, pageSize);
        return new DataTable<CompanyModel>(byPage);
    }

    @GetMapping("/add")
    public String addCompany(Model model) {
        List<CompanyTag> companyTags = companyModelService.supportCompanyList();
        List<String> companys = new ArrayList<>();
        for (CompanyTag tag : companyTags) {
            companys.add(tag.getComName());
        }
        model.addAttribute("sdkTags", companyTags);
        return "/company/add";
    }


    @PostMapping("/getCompanyName")
    @ResponseBody
    public Object getCompanyName(String key) {
        List<CompanyTag> companyTags = companyModelService.supportCompanyList();
        List<String> companys = new ArrayList<>();
        for (CompanyTag tag : companyTags) {
            if (tag.getTag().equalsIgnoreCase(key)) {
                return RestResult.buildSuccess(tag.getComName());
            }
        }
        return RestResult.buildSuccess("未找到对应的公司名称");
    }


    @PostMapping("/postAdd")
    @ResponseBody
    public Object postAdd(CompanyModel companyModel) {

        if (StringUtils.isEmpty(companyModel.getAppid())) {
            return RestResult.buildError("AppID不能为空");
        }

        if (StringUtils.isEmpty(companyModel.getAppsecret())) {
            return RestResult.buildError("Appsecret不能为空");
        }

        if (StringUtils.isEmpty(companyModel.getSdktag())) {
            return RestResult.buildError("使用接口不能为空");
        }

        SmsCompany smsCompany = companyModelService.getSmsCompanyByTag(companyModel.getSdktag());
        companyModel.setComName(smsCompany.getCompanyTag().getComName());
        // 0-自己设置短信签名 1-第三方设置
        companyModel.setSmsSendType(smsCompany.isNeedSmsHeader() ? 0 : 1);
        //0-禁用  1-启用  2-删除
        companyModel.setStatus(1);
        try {
            companyModelService.addCompany(companyModel);
        } catch (Exception se) {
            return RestResult.buildError(se.getMessage());
        }
        return RestResult.buildSuccess();
    }

    @PostMapping("/use")
    @ResponseBody
    public Object use(Integer id) {
        try {
            //启用
            companyModelService.updateCompanyStatus(id, 1);
        } catch (Exception se) {
            return RestResult.buildError(se.getMessage());
        }
        return RestResult.buildSuccess();
    }

    @PostMapping("/unuse")
    @ResponseBody
    public Object unuse(Integer id) {
        try {
            //禁用
            companyModelService.updateCompanyStatus(id, 0);
        } catch (Exception se) {
            return RestResult.buildError(se.getMessage());
        }
        return RestResult.buildSuccess();
    }


    @PostMapping("/delete")
    @ResponseBody
    public Object delete(Integer id) {
        try {
            //删除
            companyModelService.updateCompanyStatus(id, 2);
        } catch (Exception se) {
            return RestResult.buildError(se.getMessage());
        }
        return RestResult.buildSuccess();
    }


    @GetMapping("/update")
    public String update(Integer id, Model model) {
        CompanyModel companyModel = companyModelService.findCompanyById(id);
        model.addAttribute("company", companyModel);
        return "/company/update";
    }

    @PostMapping("/postUpdate")
    @ResponseBody
    public Object postUpdate(CompanyModel companyModel) {
        boolean result;
        try {
            result = companyModelService.updateCompany(companyModel);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResult.buildError("更新失败");
        }
        if (result) {
            return RestResult.buildSuccess();
        } else {
            return RestResult.buildError("更新失败");
        }
    }
}
