package me.sta.sms.model;

import java.util.LinkedList;

/**
 * @CreateBy admin
 * @CreateTime 2019/7/18
 * @Description
 */
public class SmsRequestModel {
    /**
     * 请求的ip地址
     */
    private String requestIP;
    /**
     * 模板序号
     */
    private String tplNo;
    /**
     * 手机号
     */
    private String phoneNum;
    /**
     * 时间戳
     */
    private long timestamp;
    /**
     * 参数值  需按照顺序添加
     */
    private LinkedList<String> params = new LinkedList<>();


    public String getRequestIP() {
        return requestIP;
    }

    public String getTplNo() {
        return tplNo;
    }

    public void setTplNo(String tplNo) {
        this.tplNo = tplNo;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public LinkedList<String> getParams() {
        return params;
    }

    public void setParams(LinkedList<String> params) {
        this.params = params;
    }

    public SmsRequestModel addParam(String param) {
        this.params.add(param);
        return this;
    }

    public void setRequestIP(String addr) {
        this.requestIP = addr;
    }
}
