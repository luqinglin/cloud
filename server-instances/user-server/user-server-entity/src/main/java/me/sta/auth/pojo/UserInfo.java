package me.sta.auth.pojo;

import javax.persistence.*;
import java.util.Date;
@Table(name = "user_info")
public class UserInfo{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "real_name")
    private String realName;

    @Column(name = "pwd")
    private String pwd;

    @Column(name = "status")
    private String status;

    @Column(name = "has_updpwd")
    private String hasUpdpwd;

    @Column(name = "org_name")
    private String orgName;

    @Column(name = "research_area")
    private String researchArea;

    @Column(name = "icon")
    private String icon;

    @Column(name = "email")
    private String email;

    @Column(name = "createdate")
    private Date createdate;

    @Column(name = "phone")
    private String phone;

    @Column(name = "salt2")
    private String salt2;

    @Column(name = "onlinestatus")
    private String onlineStatus;

    @Column(name = "lastupdacct")
    private String lastUpdAcct;

    @Column(name = "lastupdtime")
    private Date lastUpdTime;

    @Column(name = "last_role_id")
    private Integer lastRoleId;

    @Column(name = "last_login_time")
    private Date lastLoginTime;

    @Column(name = "note")
    private String note;

    @Column(name = "lastip")
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
