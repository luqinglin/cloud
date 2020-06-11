package me.sta.sms.controller;

import me.sta.sms.model.DataTable;
import me.sta.sms.model.PageTable;
import me.sta.sms.pojo.LogModel;
import me.sta.sms.service.LogModelService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @CreateBy admin
 * @CreateTime 2019/7/18
 * @Description
 */
@Controller
@RequestMapping("/logs")
public class LogController {

    @Resource
    LogModelService logModelService;


    @GetMapping("/tableData")
    @ResponseBody
    public Object tableData(@RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber,
                            @RequestParam(value = "pageSize", defaultValue = "30") Integer pageSize,
                            @RequestParam(value = "lTempId", required = false) Integer lTempId,
                            @RequestParam(value = "lPhoneNum", required = false) String lPhoneNum,
                            @RequestParam(value = "sdktag", required = false) String sdktag,
                            @RequestParam(value = "status", required = false, defaultValue = "-1") Integer status) {
        LogModel logModel = new LogModel();
        if (lTempId != null && lTempId > 0) {
            logModel.setlTempId(lTempId);
        }

        if (StringUtils.isNotEmpty(lPhoneNum)) {
            logModel.setlPhoneNum(lPhoneNum);
        }

        if (StringUtils.isNotEmpty(sdktag)) {
            logModel.setSdktag(sdktag);
        }

        if (null != status && status >= 0) {
            logModel.setStatus(status);
        }

        PageTable<LogModel> byPage = logModelService.findByPage(logModel, pageNumber, pageSize);
        return new DataTable<LogModel>(byPage);
    }
}
