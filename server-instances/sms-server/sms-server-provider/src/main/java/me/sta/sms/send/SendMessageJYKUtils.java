package me.sta.sms.send;

/**
 * 康心联盟 发送短信
 */
public class SendMessageJYKUtils {
//    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(SendMessageJYKUtils.class);
//
//    public static final String defaultAppId = "EUCP-EMY-SMS1-1AH8G";
//    public static final String defaultSecretKey = "D8EDCA438C502217";
//
//    public static SystemException sendSmscode(String mobile, String code) {
//        String timestamp = DateUtil.getFormattedDateUtil(new Date(), "yyyyMMddhhmmss");
//        String sign = getSignMD5(defaultAppId, defaultSecretKey, timestamp);
//        String url = "http://bjmtn.b2m.cn:80/simpleinter/sendSMS?appId=" + defaultAppId + "&timestamp=" + timestamp
//                + "&sign=" + sign + "&mobiles=" + mobile + "&content=【康心联盟】" + URLEncoder.encode("您的短信验证码是：" + code + " ,在10分钟内有效。");
////         String url = "http://bjksmtn.b2m.cn/simpleinter/sendSMS?appId=" + defaultAppId + "&timestamp=" + timestamp
////                + "&sign=" + sign + "&mobiles=" + mobile + "&content=【康心联盟】" + URLEncoder.encode("您的短信验证码是：" + code + " ,在10分钟内有效。");
//        try {
//            String s = HttpUtil.sendGetRequest(url);
////            {"code":"SUCCESS","data":[{"smsId":"154415710516500100","mobile":"17610175292","customSmsId":null}]}
////            {"code":"ERROR_MOBILE_ERROR","data":null}
//            LOGGER.info("发送短信成功-> "+ s);
//            return null;
//        } catch (Exception e) {
//            LOGGER.error("发送短信失败 -->",e);
//            e.printStackTrace();
//        }
//        return new SystemException(ApplicationCode.USER_SEND_SMS_ERROR);
//    }
//
//    private static String getSignMD5(String appId, String secretKey, String timestamp) {
//        return MD5Util.MD5Encode(appId + secretKey + timestamp, "utf-8");
//    }
//
//    /**
//     * 转发信息
//     * @param phone
//     * @param msg
//     */
//    public static void sendRedirectSms(String phone,String msg){
//        String timestamp = DateUtil.getFormattedDateUtil(new Date(), "yyyyMMddhhmmss");
//        String sign = getSignMD5(defaultAppId, defaultSecretKey, timestamp); String url = "http://bjmtn.b2m.cn:80/simpleinter/sendSMS?appId=" + defaultAppId + "&timestamp=" + timestamp
//                + "&sign=" + sign + "&mobiles=" + phone + "&content=【康心联盟】" + URLEncoder.encode(msg);
//        try {
//            String s = HttpUtil.sendGetRequest(url);
////            {"code":"SUCCESS","data":[{"smsId":"154415710516500100","mobile":"17610175292","customSmsId":null}]}
////            {"code":"ERROR_MOBILE_ERROR","data":null}
//            LOGGER.info("发送短信成功-> "+ s);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) {
//        String s1 = "您的短信验证码是：%s ,在%d分钟内有效。";
//        System.out.println(String.format(s1, "1234", 10));
//        Exception e = new NullPointerException("test");
//        LOGGER.error("test",e);
////        sendSmscode("1761017522", "1234");
//    }
}
