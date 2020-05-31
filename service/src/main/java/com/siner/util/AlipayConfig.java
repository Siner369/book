package com.siner.util;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016102200737982";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQClUJ0KMUXfFppdu2gIgCfE0wD/NERFN9xOIOrXjnmgQJzgGoDunDAOMWWZ+sxCCZ/OAgvFzFCRDjzg5KJ9+uv1aQV94g6gl54RlA62nk8mZgKYnxfWsePneicC7avOcY/9yMr+WOw5+RKB+T4iiXCK1BywjLG3gLyPUzyby8I3OpWyzuEZH09XN9dR82Wkqhc1knP0LAOcA2U4wyksyzBrAbUPUsCMD46ITM2H0b8fXKRu2JkEU577cbphhvIC/uKdML2DwBhWyq/hIRR+jrBBYpFjT4WGO958cqOUxNOtgiAHGUFQV5C1LLOgFuWYJ/DRocpx/DslqDIHR/fzPTtpAgMBAAECggEAeeDF9qa/CatDYYlyOJorN7wMxxoEnzeAz4ULPqKG3vPj9ID1v3UdJMmcS3qQrrsjuyDdBxYL6/WgQnmtps2gusyTxOwtto4xdmo+srpiRj/8+AKK87kw+g6fxlPtT4WAEC3CIuTyPAd6cqKMAbBsbZ3WDP+dYlmiKtTGIWKGrGntTgL/VTNEVKg9ISO1Z+ci6E12hlf8MsFhdgMI13Qd4oInAzMtU9I7PuuowXfNgJ2c9h6YSwbi/csiqcpVFmr0KAh+D68WwtUUGL3pm35BUIU16nHEhefxfKXIPhTBRgoYZZtUmC2HbqtBhiqlb3ZkQMTYuqKadXa17vGUR0jqgQKBgQDtefZxwbs7Bi+TGZdRcOTgNtmyRW6sdpAEFZTcWk2V+cAtFoA6cpEX6+BGCv0gbJ9ti20Wm7faQAygE+VhOWigdYd+pKCisF+x0JllsKdNjQ/fZ2NJZAJdp/4vb+y7uoR4MTW1OgPnFXN5Q+ybIXCbc0T2wl9siFB/jp8qW8K5eQKBgQCyNbJWiJKAKM3j9pIuxSojfqoDH5jOM4/6jzd7nrG7XO0RSCy0QOJboDJlxgpCr1pVbJNABbwzFRA7QmNXrY2ee+FP6PzTxomPF3yXb7y1XBZCkoZIzGenr0/lTSSwlxgtQoscjJMV9/rMmkV+osyc9X3zEiDN31voQFcxCXoFcQKBgGz9MqwW29VFC4SZlCcehwyYX+U/lIG2kHYvaXiQQVd5nkeTMhqhBhJSgUXkOTQuZiUifliH8yNJjgHR3851SJ2MEzl+WPMRu3miYSSYpNAk04oWqyy9p5BQT4p59c5a1f1LqHiaPbxKzgpzsB2qhUHy703I+36WOUuxqz8HiiERAoGBAKk3n63XZPTYyhhKi6hpg4fMwHbSy/AQugh7cX2qxK1aoaklVX2bltTWGsTU9e04QJiYzoGecIQ43f7HHTmmDLGOg/WzEE+vV/NNo/rQGOpFFMYGCAicP1jUeRMM22frMQPZCesD+K6Fl9osKw0CXTQ1T3E9p3SVQNoaLjDcBMehAoGBAM0kQU7ZTJi3jeqokAVuEHNoLMo1k/je7DbI/kK6iIe9r7nMjMoG9k9Lt2ZsJmNNObUShut4ciwOgHIX8pak+nlIhz31wAMlFzlxJoOHi9S1ylRrD+QndFk5SugI305w8iijUibEnRWPhrLSk67r3hax0b3XbvqWiwiDRX/EqSDY";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApVCdCjFF3xaaXbtoCIAnxNMA/zRERTfcTiDq1455oECc4BqA7pwwDjFlmfrMQgmfzgILxcxQkQ484OSiffrr9WkFfeIOoJeeEZQOtp5PJmYCmJ8X1rHj53onAu2rznGP/cjK/ljsOfkSgfk+IolwitQcsIyxt4C8j1M8m8vCNzqVss7hGR9PVzfXUfNlpKoXNZJz9CwDnANlOMMpLMswawG1D1LAjA+OiEzNh9G/H1ykbtiZBFOe+3G6YYbyAv7inTC9g8AYVsqv4SEUfo6wQWKRY0+FhjvefHKjlMTTrYIgBxlBUFeQtSyzoBblmCfw0aHKcfw7JagyB0f38z07aQIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:8080/bookstore/payloading";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:8080/bookstore/payloading";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipay.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


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
