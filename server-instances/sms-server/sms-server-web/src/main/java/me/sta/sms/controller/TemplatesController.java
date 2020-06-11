package me.sta.sms.controller;

import me.sta.sms.model.DataTable;
import me.sta.sms.model.PageTable;
import me.sta.sms.model.RestResult;
import me.sta.sms.pojo.CompanyModel;
import me.sta.sms.pojo.TemplateModel;
import me.sta.sms.service.CompanyModelService;
import me.sta.sms.service.TemplateModelService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @CreateBy admin
 * @CreateTime 2019/7/17
 * @Description
 */
@Controller
@RequestMapping("/template")
public class TemplatesController {

    @Resource
    CompanyModelService companyModelService;

    @Autowired
    TemplateModelService templateModelService;

    @GetMapping("/tableData")
    @ResponseBody
    public Object tableData(@RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber,
                            @RequestParam(value = "pageSize", defaultValue = "30") Integer pageSize,
                            @RequestParam(value = "tplName", required = false) String tplName,
                            @RequestParam(value = "sdktag", required = false) String sdktag,
                            @RequestParam(value = "status", required = false, defaultValue = "-1") Integer status) {
        TemplateModel model = new TemplateModel();
        if (StringUtils.isNotEmpty(tplName)) {
            model.setTplName(tplName);
        }
        if (StringUtils.isNotEmpty(sdktag)) {
            model.setSdktag(sdktag);
        }
        if (status != null && status != -1) {
            model.setStatus(status);
        }
        PageTable<TemplateModel> byPage = templateModelService.findByPage(model, pageNumber, pageSize);
        return new DataTable<TemplateModel>(byPage);
    }

    @GetMapping("/add")
    public String addTemplate(Integer id, Model model) {
        CompanyModel company = companyModelService.findCompanyById(id);
        model.addAttribute("com", company);
        return "/template/add";
    }

    @PostMapping("/postAdd")
    @ResponseBody
    public Object postAdd(TemplateModel templateModel) {
        try {
            boolean result = templateModelService.addTemplateModel(templateModel);
            if (!result) {
                return RestResult.buildError("添加失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return RestResult.buildError("添加失败");
        }
        return RestResult.buildSuccess();
    }

    @PostMapping("/use")
    @ResponseBody
    public Object use(Integer id) {
        try {
            //启用
            templateModelService.updateCompanyStatus(id, 1);
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
            templateModelService.updateCompanyStatus(id, 0);
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
            templateModelService.updateCompanyStatus(id, 2);
        } catch (Exception se) {
            return RestResult.buildError(se.getMessage());
        }
        return RestResult.buildSuccess();
    }

    @GetMapping("/detail")
    public String detail(Integer id, Model model) {
        TemplateModel tplModel = templateModelService.findById(id);
        CompanyModel companyModel = companyModelService.findCompanyById(tplModel.getTplComId());
        model.addAttribute("tpl", tplModel);
        model.addAttribute("com", companyModel);
        return "/template/detail";
    }

    @GetMapping("/update")
    public String update(Integer id, Model model) {
        TemplateModel tplModel = templateModelService.findById(id);
        model.addAttribute("tpl", tplModel);
        CompanyModel companyModel = companyModelService.findCompanyById(tplModel.getTplComId());
        model.addAttribute("com", companyModel);
        return "/template/update";
    }


    @PostMapping("/postUpdate")
    @ResponseBody
    public Object postUpdate(TemplateModel tplModel) {
        boolean result;
        try {
            result = templateModelService.updateTemplate(tplModel);
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
