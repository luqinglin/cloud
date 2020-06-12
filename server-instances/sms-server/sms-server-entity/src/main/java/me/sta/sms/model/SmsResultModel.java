package me.sta.sms.model;

/**
 * 调用发送短信返回的model
 * @CreateBy admin
 * @CreateTime 2019/7/16
 * @Description
 */
public class SmsResultModel {

    public static final Integer SUCCESS = 200;
    public static final Integer FAILURE = 500;

    private Integer code;
    private String msg;

    public SmsResultModel() {
    }

    public SmsResultModel(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
