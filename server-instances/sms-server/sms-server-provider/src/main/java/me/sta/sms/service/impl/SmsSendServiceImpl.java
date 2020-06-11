package me.sta.sms.service.impl;

import me.sta.sms.dao.CompanyModelMapper;
import me.sta.sms.dao.LogModelMapper;
import me.sta.sms.dao.TemplateModelMapper;
import me.sta.sms.listener.BootFinishListener;
import me.sta.sms.model.SmsResultModel;
import me.sta.sms.model.TemplateSendModel;
import me.sta.sms.pojo.CompanyModel;
import me.sta.sms.pojo.LogModel;
import me.sta.sms.pojo.TemplateModel;
import me.sta.sms.model.SmsCompany;
import me.sta.sms.model.SmsRequestModel;
import me.sta.sms.service.SmsSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @CreateBy admin
 * @CreateTime 2019/7/19
 * @Description
 */
@Service
public class SmsSendServiceImpl implements SmsSendService {

    @Autowired
    BootFinishListener bootFinishListener;
    @Autowired
    TemplateModelMapper templateModelMapper;
    @Autowired
    CompanyModelMapper companyModelMapper;
    @Autowired
    LogModelMapper logModelMapper;

    @Override
    public SmsResultModel sendSmsByTemplate(SmsRequestModel requestModel) {
        SmsResultModel resultModel = new SmsResultModel();
        String tplNo = requestModel.getTplNo();
        TemplateModel templateModel = templateModelMapper.findByTplNoEffect(tplNo);
        if (templateModel == null) {
            resultModel.setCode(SmsResultModel.FAILURE);
            resultModel.setMsg("未找到对应的模板");
            return resultModel;
        }
        CompanyModel companyModel = companyModelMapper.selectByPrimaryKey(templateModel.getTplComId());

        String sdktag = templateModel.getSdktag();
        SmsCompany smsCompany = bootFinishListener.getSmsCompanyByTag(sdktag);
        //数据封装
        TemplateSendModel templateSendModel = new TemplateSendModel(templateModel.getId()
                , companyModel.getAppid(), companyModel.getAppsecret(), companyModel.getUrl(), companyModel.getSmsSendType(), templateModel.getTplSmsHeader()
                , templateModel.getTplContent(), requestModel.getParams());

        resultModel = smsCompany.sendSmsCode(templateSendModel, requestModel.getPhoneNum());
        //日志
        LogModel logModel = new LogModel();
        logModel.setlSendTime(new Date());
        logModel.setlContent(templateSendModel.getContent());
        logModel.setlPhoneNum(requestModel.getPhoneNum());
        logModel.setlRequestIp(requestModel.getRequestIP());
        logModel.setlTempId(templateSendModel.getTplId());
        logModel.setSdktag(sdktag);
        if (resultModel.getCode() == SmsResultModel.SUCCESS) {
            // 1-成功  0-失败
            logModel.setStatus(1);
        } else {
            // 1-成功  0-失败
            logModel.setStatus(0);
            logModel.setlFailureReason(resultModel.getMsg());
        }
        logModelMapper.insertSelective(logModel);
        return resultModel;
    }

    @Override
    public SmsResultModel sendSmsWithoutTemplate(String phoneNum, String content) {
        //日志
        LogModel logModel = new LogModel();
        logModel.setlSendTime(new Date());
//        logModel.setlContent(templateSendModel.getContent());
//        logModel.setlPhoneNum(requestModel.getPhoneNum());
//        logModel.setlRequestIp(requestModel.getRequestIP());
        //不使用模板
        logModel.setlTempId(-1);
//        logModel.setSdktag(sdktag);
//        if (resultModel.getCode() == SmsResultModel.SUCCESS) {
//            // 1-成功  0-失败
//            logModel.setStatus(1);
//        } else {
//            // 1-成功  0-失败
//            logModel.setStatus(0);
//            logModel.setlFailureReason(resultModel.getMsg());
//        }
        logModelMapper.insertSelective(logModel);
        return null;
    }
}
