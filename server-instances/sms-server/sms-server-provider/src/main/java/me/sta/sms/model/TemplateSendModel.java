package me.sta.sms.model;

import java.util.LinkedList;

/**
 * @CreateBy admin
 * @CreateTime 2019/7/16
 * @Description
 */
public class TemplateSendModel {
    private String appid;

    private String appsecret;

    private Integer smsSendType;

    private String requestUrl;

    /**
     * 模板id
     */
    private Integer id;
    /**
     * 短信签名
     */
    private String smsHeader;
    /**
     * 模板内容
     */
    private String template;
    /**
     * 替换后的内容
     */
    private String content;
    /**
     * 替换模板参数
     */
    LinkedList<String> params;


    public String getAppid() {
        return appid;
    }

    private void setAppid(String appid) {
        this.appid = appid;
    }

    public String getAppsecret() {
        return appsecret;
    }

    private void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }

    private void setSmsSendType(Integer smsSendType) {
        this.smsSendType = smsSendType;
    }


    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    /**
     * 参数个数
     */
    private int paramCount;
    private static final String PLACE_HOLDER_STRING = "%param";


    public TemplateSendModel(Integer id, String appid, String appsecret, String url, Integer smsSendType
            , String smsHeader, String template, LinkedList<String> params) {
        setId(id);
        setSmsHeader(smsHeader);
        setTemplate(template);
        setAppid(appid);
        setAppsecret(appsecret);
        setRequestUrl(url);
        setSmsSendType(smsSendType);
        setParams(params);
    }

    private void setId(Integer id) {
        this.id = id;
    }

    public Integer getTplId() {
        return id;
    }

    private void setSmsHeader(String smsHeader) {
        this.smsHeader = smsHeader;
    }


    private void setTemplate(String template) {
        this.template = template;
        calcParamCount();
    }


    private void setParams(LinkedList<String> params) {
        this.params = params;
    }

    private void calcParamCount() {
        if (template != null && template.trim().length() > 0) {
            int count = 0;
            int index = 0;
            String str = template;
            while (((index = str.indexOf(PLACE_HOLDER_STRING)) != -1)) {
                //想个办法截取字符串中查找字符,并将查找当前匹配字符之后的字符串重新
                //赋值给字符串
                str = str.substring(index + 1);
                count++;
            }
            paramCount = count;
        } else {
            paramCount = 0;
        }
    }

    public SmsResultModel replaceParam() {
        SmsResultModel result = new SmsResultModel();
        //判断是否使用模板
        if (id > 0) {
            result.setCode(SmsResultModel.FAILURE);
            if (template == null || template.trim().length() == 0) {
                result.setMsg("请求模板为空");
                return result;
            }
            if (paramCount != params.size()) {
//                result.setMsg("模板参数个数为" + paramCount + "个,传递的参数个数为" + params.size() + "个");
                result.setMsg("模板参数个数与传递的参数个数不匹配");
//                result.setData(params);
                return result;
            }
            realReplaceParam(params);
        } else {
            //不使用模板
            content = params.get(0);
        }
        return null;
    }

    /**
     * 真正的替换参数
     *
     * @param params
     */
    private void realReplaceParam(LinkedList<String> params) {
        content = "";
        String tempTpl = template;
        int start = 0;
        int len = tempTpl.indexOf(PLACE_HOLDER_STRING);
        String piece = "";
        for (int i = 0; len != -1; i++) {
            piece = tempTpl.substring(start, len);
            content = content + piece + params.get(i);
            piece = "";
            start = len + PLACE_HOLDER_STRING.length();
            len = tempTpl.indexOf(PLACE_HOLDER_STRING, start);
        }
        //处理尾部
        len = template.lastIndexOf(PLACE_HOLDER_STRING) + PLACE_HOLDER_STRING.length();
        if (len != template.length()) {
            content = content + template.substring(len);
        }
    }

    /**
     * 获取替换后的内容
     *
     * @return
     */
    public String getContent() {
        if (isNeedSmsHeader()) {
            content = smsHeader + content;
        }
        return content;
    }

    private boolean isNeedSmsHeader() {
        return smsSendType == 0;
    }

}
