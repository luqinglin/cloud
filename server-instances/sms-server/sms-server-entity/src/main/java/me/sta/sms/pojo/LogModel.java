package me.sta.sms.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class LogModel {
    private Integer id;

    private Integer lTempId;

    private String sdktag;

    private String lPhoneNum;

    private String lContent;

    private String lRequestIp;

    private Integer status;

    private String lFailureReason;

    private Date lSendTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getlTempId() {
        return lTempId;
    }

    public void setlTempId(Integer lTempId) {
        this.lTempId = lTempId;
    }

    public String getSdktag() {
        return sdktag;
    }

    public void setSdktag(String sdktag) {
        this.sdktag = sdktag == null ? null : sdktag.trim();
    }

    public String getlPhoneNum() {
        return lPhoneNum;
    }

    public void setlPhoneNum(String lPhoneNum) {
        this.lPhoneNum = lPhoneNum == null ? null : lPhoneNum.trim();
    }

    public String getlContent() {
        return lContent;
    }

    public void setlContent(String lContent) {
        this.lContent = lContent == null ? null : lContent.trim();
    }

    public String getlRequestIp() {
        return lRequestIp;
    }

    public void setlRequestIp(String lRequestIp) {
        this.lRequestIp = lRequestIp == null ? null : lRequestIp.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getlFailureReason() {
        return lFailureReason;
    }

    public void setlFailureReason(String lFailureReason) {
        this.lFailureReason = lFailureReason == null ? null : lFailureReason.trim();
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getlSendTime() {
        return lSendTime;
    }

    public void setlSendTime(Date lSendTime) {
        this.lSendTime = lSendTime;
    }
}
