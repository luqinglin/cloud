package me.sta.sms.core;

import cn.hutool.core.date.DateUtil;
import me.sta.sms.model.SmsResultModel;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Date;

/**
 * @CreateBy gaoguangtian
 * @CreateTime 2018/12/19
 * @Description
 */
public class SmsUtils {


    public static final String defaultAppId = "EUCP-EMY-SMS1-1AH8G";
    public static final String defaultSecretKey = "D8EDCA438C502217";

    /**
     * 发送短信
     *
     * @param phone
     * @param code
     * @return true 发送成功  false 发送失败
     */
    public static SmsResultModel sendContent(String phone, String content) {

        if (true){
            return  new SmsResultModel(200,"发送成功");
        }

        String timestamp = DateUtil.format(new Date(), "yyyyMMddhhmmss");
        String sign = getSignMD5(defaultAppId, defaultSecretKey, timestamp);
        String url = "http://bjmtn.b2m.cn:80/simpleinter/sendSMS?appId=" + defaultAppId + "&timestamp=" + timestamp
                + "&sign=" + sign + "&mobiles=" + phone.trim() + "&content=【藏酒之计】" + URLEncoder.encode("：" + content + ":test");
        try {
            String s = sendGetRequest(url);
            //"code":"SUCCESS","data":[{"smsId":"154415710516500100","mobile":"17610175292","customSmsId":null}]}
            //            {"code":"ERROR_MOBILE_ERROR","data":null}
            if (s != null && s.contains("SUCCESS")) {
                return new SmsResultModel(200,"发送成功");
            } else {

                return  new SmsResultModel(444,"发送失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  new SmsResultModel(444,"发送失败");
    }



    private static String getSignMD5(String appId, String secretKey, String timestamp) {
        return MD5Encode(appId + secretKey + timestamp, "utf-8");
    }

    private static String sendGetRequest(String urlPath) throws Exception {
        URL url = new URL(urlPath);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");// 提交模式
        conn.getResponseCode();
        InputStream inStream = conn.getInputStream();
        return new String(readInputStream(inStream));
    }

    private static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        byte[] data = outStream.toByteArray();// 网页的二进制数据
        outStream.close();
        inStream.close();
        return data;
    }

    public static String MD5Encode(String origin, String charsetname) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetname == null || "".equals(charsetname))
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes()));
            else
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes(charsetname)));
        } catch (Exception exception) {
        }
        return resultString;
    }

    private static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++)
            resultSb.append(byteToHexString(b[i]));

        return resultSb.toString().toUpperCase();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n += 256;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }


    private static final String hexDigits[] = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    
    /**
     * 签章发送短信
     *
     * @param phone
     * @param code
     * @return true 发送成功  false 发送失败
     */
    public static boolean sendSmsCodeQZ(String phone, String code) {
        String timestamp = DateUtil.format(new Date(), "yyyyMMddhhmmss");
        String sign = getSignMD5(defaultAppId, defaultSecretKey, timestamp);
        String url = "http://bjmtn.b2m.cn:80/simpleinter/sendSMS?appId=" + defaultAppId + "&timestamp=" + timestamp
                + "&sign=" + sign + "&mobiles=" + phone.trim() + "&content=【汇聚】" + URLEncoder.encode("验证码：" + code + " ,您正在使用亚太账户注册，需要进行验证，请不要向任何人泄漏。如非本人操作，无需理会。");
        try {
            String s = sendGetRequest(url);
            //"code":"SUCCESS","data":[{"smsId":"154415710516500100","mobile":"17610175292","customSmsId":null}]}
            //            {"code":"ERROR_MOBILE_ERROR","data":null}
            if (s != null && s.contains("SUCCESS")) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 客户生日短信
     *
     * @param phone
     * @return true 发送成功  false 发送失败
     */
    public static boolean sendSms(String phone, String content) {
        String timestamp = DateUtil.format(new Date(), "yyyyMMddhhmmss");
        String sign = getSignMD5(defaultAppId, defaultSecretKey, timestamp);
        String url = "http://bjmtn.b2m.cn:80/simpleinter/sendSMS?appId=" + defaultAppId + "&timestamp=" + timestamp
                + "&sign=" + sign + "&mobiles=" + phone + "&content=" + content;
        try {
            String s = sendGetRequest(url);
            if (s != null && s.contains("SUCCESS")) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
