package me.sta.sms.send;

/**
 * @CreateBy admin
 * @CreateTime 2019/7/16
 * @Description
 */
public enum CompanyTag {

    YI_MEI_RUAN_TONG("亿美软通","北京亿美软通科技有限公司");


    private String tag;
    private String comName;

    CompanyTag(String tag, String comName) {
        this.tag = tag;
        this.comName = comName;
    }

    public String getTag() {
        return tag;
    }

    public String getComName() {
        return comName;
    }
}
