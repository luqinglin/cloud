package me.sta.sms.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class TemplateModel {
    private Integer id;

    private Integer tplComId;

    private String sdktag;

    private String tplNo;

    private String tplName;

    private String tplSmsHeader;

    private String tplContent;

    private String tplRemark;

    private Integer status;

    private Date createtime;

    private Date updatetime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTplComId() {
        return tplComId;
    }

    public void setTplComId(Integer tplComId) {
        this.tplComId = tplComId;
    }

    public String getSdktag() {
        return sdktag;
    }

    public void setSdktag(String sdktag) {
        this.sdktag = sdktag == null ? null : sdktag.trim();
    }

    public String getTplNo() {
        return tplNo;
    }

    public void setTplNo(String tplNo) {
        this.tplNo = tplNo == null ? null : tplNo.trim();
    }

    public String getTplName() {
        return tplName;
    }

    public void setTplName(String tplName) {
        this.tplName = tplName == null ? null : tplName.trim();
    }

    public String getTplSmsHeader() {
        return tplSmsHeader;
    }

    public void setTplSmsHeader(String tplSmsHeader) {
        this.tplSmsHeader = tplSmsHeader == null ? null : tplSmsHeader.trim();
    }

    public String getTplContent() {
        return tplContent;
    }

    public void setTplContent(String tplContent) {
        this.tplContent = tplContent == null ? null : tplContent.trim();
    }

    public String getTplRemark() {
        return tplRemark;
    }

    public void setTplRemark(String tplRemark) {
        this.tplRemark = tplRemark == null ? null : tplRemark.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}
