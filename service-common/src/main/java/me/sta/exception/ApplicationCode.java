package me.sta.exception;

/**
 * ApplicationCode
 *
 * @Description
 * @Author liujun
 * @Date 2017-08-15 14:28
 */
public enum ApplicationCode {


    INTERNAL_SERVER_ERROR(500, "Internal Server Error", "服务器内部错误"),

    INVALID_PARAM(20001, "Invalid param", "非法参数"),

    PARAMETERS_IS_MISSING(20002,"Parameters is missing","参数缺失"),

    USER_NOT_LOGIN(20003, "User Not Login", "用户未登录或认证失效"),
    INVALID_URL(20004, "Invalid URL", "非法URL"),

    INVALID_REQUEST(20005, "Invalid REQUEST", "非法访问"),

    USER_HAS_ACCOUNT_FROZEN(20006, "user has account frozen", "账户已冻结,请联系工作人员"),
    CMS_PLATFORM_SERVER_IS_ERROR(20004,"Parameters is missing","短信服务平台异常,请联系工作人员"),

    OPERATING_FREQUENCY_ERROR(20005,"","操作频繁,请稍后重试"),

    USER_NAME_PASSWORD_ERROR(30001, "user Name Password error", "用户名密码错误"),
    USER_NAME_ERROR(30002, "user Name Password error", "请输入正确登录名"),
    USER_PASSWORD_ERROR(30003, "user Name Password error", "请输入正确密码"),
    USER_NOT_EXIST(30004, "user not exist", "用户不存在"),

    ERROR_REQUIRED_ENTRY(40001, "", "缺少必要的条目"),

    ERROR_PARAM_EXCEPTION(40002, "", "请求参数异常"),

    ERROR_DATE_FORMAT_ILLEGAL(40020, "", "日期格式不正确"),

    DATA_SOURCE_SET_ERROR(40021, "", "未指定数据源"),

    DATA_SOURCE_SWITCH_ERROR(40022, "", "开关尚未打开"),

    OLD_PASSWORD_ERROR(50000, "", "原密码错误"),

    LABEL_NAME_NULL(60000, "", "标签显示名为空"),
    LABEL_FQ_NAME_NULL(60001, "", "标签分群名为空"),
    LABEL_TYPE_NULL(60002, "", "标签分组为空"),
    LABEL_DESC_NULL(60003, "", "标签描述为空"),
    LABEL_RULE_NULL(60004, "", "标签规则为空"),
    LABEL_NOT_EXIST(60005, "", "标签不存在"),
    LABEL_STATUS_ERROR(60006, "", "标签状态错误"),



//########功能管理部分##############
    FUNCTION_NAME_NULL(90000, "", "功能名称为空"),
    FUNCTION_TYPE_NULL(90001, "", "功能类型为空"),
    FUNCTION_TYPE_UNEXIST(90002, "", "功能类型不存在"),
    FUNCTION_ID_UNEXIST(90003, "", "功能不存在"),
    FUNCTION_ADMIN(90004, "", "已经是管理员权限"),
//########角色管理部分##############
    ROLE_NAME_NULL(80000, "", "角色名称为空"),
    ROLE_ID_UNEXIST(80001, "", "角色不存在"),

    ANALYSIS_FAILE(70000, "", "excel解析失败"),
    CAN_NOT_WRITE_CONTENT_TO_RESP(70001, "", "无法向response中写入数据"),

    ADMIN_ID_UNEXIST(100000, "", "员工不存在"),
    ;


    ApplicationCode(int code, String message, String desc) {
        this.code = code;
        this.message = message;
        this.desc = desc;
    }

    private int code;
    private String message;
    private String desc;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
