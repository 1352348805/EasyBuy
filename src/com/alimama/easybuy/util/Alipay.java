package com.alimama.easybuy.util;

/**
 * @author asuk
 * @date 2020/7/20 0:03
 */
public class Alipay {

    public static String app_id = ConfigManager.getAlipayProperty("app_id");
    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = ConfigManager.getAlipayProperty("merchant_private_key");
    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = ConfigManager.getAlipayProperty("alipay_public_key");
    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = ConfigManager.getAlipayProperty("notify_url");
    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    //支付成功以后跳到哪里 http://tfkv0cljsb.51http.tech/
    public static String return_url = ConfigManager.getAlipayProperty("return_url");
    // 签名方式
    public static String sign_type = ConfigManager.getAlipayProperty("sign_type");
    // 字符编码格式
    public static String charset = ConfigManager.getAlipayProperty("charset");

    // 支付宝网关  https://openapi.alipaydev.com/gateway.do 这是正式地址
    public static String gatewayUrl = ConfigManager.getAlipayProperty("gatewayUrl");
}
