package com.murphy.mall.pay.config;

import java.io.FileWriter;
import java.io.IOException;

/**
 * 支付宝配置类
 * @author murphy
 */
public class AlipayUtils {

    /**
     * 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
     */
    public static String app_id = "2021000118616708";

    /**
     * 商户私钥，您的PKCS8格式RSA2私钥
     */
    public static String merchant_private_key="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCM5bY5ovRtecg9vxRr33OVrKRJ5jHepLZ2scW2Ia1RMc/5QxrAJW9v7DHvYCqMEe8v4Z1dFvp5jp+0yHIo0pUqvXEzvqkVZ76p0LajtAp0lVdkAx41pabrQdppgfotCAllymPorrFjKtt/sdFANenGM0doicZWE4ElQpAXIC4wqzmFjJ8l8lCJjrHnZBDu+60BpIkayXgg8X57uOzp6k7GYsS78jRP05UvL+Q6qVirPhAWc9BkFiOSS11k+x+cKmoD0gR2mGjBh1C5LZa9lx4oK00SRyarm8GaRBRUdmg1J9var0m8OWGh0ZCT1wTI7utnVR8gh94GtI7Nfc6ArIT1AgMBAAECggEAfJGjeHXAEQI4rY7AqVbXxB7s6YEh2ixaPbqSiKJhr4WQ0FopznPKgHOc+nxVYZ2BkR2xmVn1fX9BZ/5IIBt+UllHOm544znj8hNbRoSpMcXPi9bN4LRiTWDdj6ag3ytZ06sKhe/dWUXM5qr5zAFefCNeIOied/+qkJy29Bs5e0CwcSkNIE0OJxO4/uT0qI/NQgYJPBKzm5fXHf8p6cB1xlN+JWaBtwsXXvoUA/TfZzT2RgBBoa1QNKPcd5JoA3w7DZ18scTdphsXNDRrJ6zy4DeFvTWxnJ0F7HOd2F2tOSEh2hsC/eQwVa2kPcZ0jih9Lch15+Od0tqWsEU4PUaNwQKBgQDoHIq+ELXO4Ls5EACUWrMFDPg8Ag5COoVP+fC/HOBUUHSuCTOFLquLT4j1nB3OakClpj2PmQie/KwtvuUNRYgGtJG8DJFshAAiRK5yfNJIsskJUKzceYbW3+FQeowzEwe/jHYrii6gLPgY/c160jWMvlgB2RBD4AzfE0R466ECfQKBgQCbZfF+x9Za/Z709Hy3cxnb8zWWTAtwFi1TNtSGFyX97g47xZuUten8xNhFMsx0itxca2TL0SDKaPrb71zhNViwlbOWhcjQl8w0sBdVisqH56ndzGK5B47bbz4uUkThQSGAnHT9G6CsZlucPfzyEB2IlnXZCK/AA1M/Yt4UDJJd2QKBgAxNxwUAOcL3orUJph43GJpB776ulTbphzuQEjTvQOz3Ptgi4LpEmQ9PbgEm/2V7uGWPhF/iStSyrCXYoqerceR5GeiP+f14mdE3NtAW0cbMw19dCWy+fGlTgMwVUu06KwT6WyE/t+0BtT0N5yCFmBUX2e5vJS+T5trEWLLYOra5AoGAE0jaaFZaZp5UzKdOluGE0wPioY08WiOWal5Cj4Vt5I5XW9mRDippKEIxaJz+PHRDFoEJWDXSsOvIPeT9jME3CWr6h3KA0koUQHGd47K+oZEBRpInqjii5N5wRAVXoPCZPMGtzv9PuDVXpxMWQd/4Gkw5wb5POS/wc8xMLQbGXzkCgYEAtSu1xBIys3W3mPVchB0+yzRRZjxH5vE9eBkr610oT83ddnNNtWMQuXPDtDbHqi4d8f5xxTzFq2wHZTWzt9zWf/Z7a/GYXVhYAkfC2fZYC8LcXArDp6lgdCwNW+brXCyFJoq9Qh1fRv4qZx3SHtDrXSeJlcscfkIK0cWoxOEA4Ao=";

    /**
     * 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
     */
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkRGJtuWw76bEM/suDDhXoGpWGfHJDxH2PGKRifTbUImHCqDDFXodclYF8SeibhtFqoxzIHXaBKE3U5koQkQYkr5Rg08V3aQSQh9dGTR+etUaZHXg4C3JR7PeSyctqQcYvCmeSbJv7NGzGHajTKqJUdWrUldUs0A5fxtFs2/UtRSvZbD9AccorhstAsOEVL4RtfSSqJTZYDBMpVbFhzZWx+GS0yenZVFNFwGg8W5i6xQRbgLPHqy0ttfdnFkGEWziDrSLJUhOlM5Z6nr5NYb8djYWxa2RAN6ePl7yfH/WWwvP+SEGMsXMjjHjtWwAr8aUqMnGTUhQzTzU8yQhhOUttwIDAQAB";

    /**
     * 服务器异步通知页面路径
     * 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
     */
    public static String notify_url = "http://62ft67.natappfree.cc/getnotify";

    /**
     * 页面跳转同步通知页面路径
     * 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
     */
    public static String return_url = "http://62ft67.natappfree.cc/getreturn";

    /**
     * 签名方式
     */
    public static String sign_type = "RSA2";

    /**
     * 字符编码格式
     */
    public static String charset = "utf-8";

    /**
     * 支付宝网关,注意这些使用的是沙箱的支付宝网关，与正常网关的区别是多了dev
     */
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    /**
     * 支付宝网关
     */
    public static String log_path = "/Users/murphy/Documents/AlipayLog/";


    //↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
