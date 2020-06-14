package me.sta.user.dto;

import java.util.Date;

public class UserInfoDto {



    private Integer id;

    private String name;


    private String realName;

    private String pwd;

    private String status;

    private String hasUpdpwd;

    private String orgName;

    private String researchArea;

    private String icon;

    private String email;

    private Date createdate;

    private String phone;

    private String salt2;

    private String onlineStatus;

    private String lastUpdAcct;

    private Date lastUpdTime;

    private Integer lastRoleId;

    private Date lastLoginTime;

    private String note;

    private String lastip;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getHasUpdpwd() {
        return hasUpdpwd;
    }

    public void setHasUpdpwd(String hasUpdpwd) {
        this.hasUpdpwd = hasUpdpwd;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getResearchArea() {
        return researchArea;
    }

    public void setResearchArea(String researchArea) {
        this.researchArea = researchArea;
    }

    public Integer getLastRoleId() {
        return lastRoleId;
    }

    public void setLastRoleId(Integer lastRoleId) {
        this.lastRoleId = lastRoleId;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastip() {
        return lastip;
    }

    public void setLastip(String lastip) {
        this.lastip = lastip;
    }

    public String getLastUpdAcct() {
        return lastUpdAcct;
    }

    public void setLastUpdAcct(String lastUpdAcct) {
        this.lastUpdAcct = lastUpdAcct;
    }

    public Date getLastUpdTime() {
        return lastUpdTime;
    }

    public void setLastUpdTime(Date lastUpdTime) {
        this.lastUpdTime = lastUpdTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSalt2() {
        return salt2;
    }

    public void setSalt2(String salt2) {
        this.salt2 = salt2;
    }

    public String getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(String onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

}
