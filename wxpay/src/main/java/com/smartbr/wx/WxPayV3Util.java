package com.smartbr.wx;

import okhttp3.HttpUrl;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 微信pay v3
 *
 * @author sc
 */
public class WxPayV3Util extends BasePay {
    private static final Logger logger = LoggerFactory.getLogger(WxPayV3Util.class);
    static String charset = "UTF-8";

    /**
     * 微信支付下单
     *
     * @param url                请求地址（只需传入域名之后的路由地址）
     * @param payInfo            请求体 json字符串 此参数与微信官方文档一致
     * @param mchId              商户ID
     * @param mchSerialNo        证书序列号
     * @param privateKeyFilePath 私钥的路径
     * @return 订单支付的参数
     * @throws Exception
     */
    public static String v3PayGet(String url, String payInfo, String mchId, String mchSerialNo, String type, String privateKeyFilePath) throws Exception {

        //微信平台证书编号
        String WechatpaySerial = "21485620C96E979179C512D98AACCFE071BBDCB3";
        //创建post方式请求对象
        HttpPost httpPost = new HttpPost(url);
        //装填参数
        StringEntity s = new StringEntity(payInfo, charset);
        s.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        //设置参数到请求对象中
        httpPost.setEntity(s);

        String post = getToken("POST", HttpUrl.parse(url), mchId, mchSerialNo, privateKeyFilePath, payInfo);
        //设置header信息
        //指定报文头【Content-type】、【User-Agent】
        httpPost.setHeader("Content-type", "application/json");
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Authorization", "WECHATPAY2-SHA256-RSA2048 " + post);
        httpPost.addHeader("Wechatpay-Serial", WechatpaySerial);
        //创建httpclient对象
        CloseableHttpClient client = HttpClients.createDefault();
        //执行请求操作，并拿到结果（同步阻塞）
        CloseableHttpResponse response = client.execute(httpPost);
        //获取结果实体
        HttpEntity entity = response.getEntity();
        String body = "";
        if (entity != null) {
            //按指定编码转换结果实体为String类型
            body = EntityUtils.toString(entity, charset);
        }
        EntityUtils.consume(entity);
        //释放链接
        response.close();
       /* switch (type) {
            case "v3/pay/transactions/app"://返回APP支付所需的参数
                return JSONObject.fromObject(body).getString("prepay_id");
            case "v3/pay/transactions/jsapi"://返回JSAPI支付所需的参数
                return JSONObject.fromObject(body).getString("prepay_id");
            case "v3/pay/transactions/native"://返回native的请求地址
                return JSONObject.fromObject(body).getString("code_url");
            case "v3/pay/transactions/h5"://返回h5支付的链接
                return JSONObject.fromObject(body).getString("h5_url");
        }*/

        logger.debug("{}:\r {}", type, body);
        return body;

    }

    public static String getRequest(String url, String mchid, String serial_no, String privateKeyFilePath) throws Exception {

        //创建httpclient对象
        CloseableHttpClient client = HttpClients.createDefault();
        //创建post方式请求对象
        HttpGet httpGet = new HttpGet(url);
        // 处理请求头报文
        String post = getToken("GET", HttpUrl.parse(url), mchid, serial_no, privateKeyFilePath, "");
        //设置header信息
        //指定报文头【Content-type】、【User-Agent】
        httpGet.setHeader("Content-type", "application/json");
        httpGet.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        httpGet.setHeader("Accept", "application/json");
        httpGet.setHeader("Authorization", "WECHATPAY2-SHA256-RSA2048 " + post);
        //执行请求操作，并拿到结果（同步阻塞）
        CloseableHttpResponse response = client.execute(httpGet);
        //获取结果实体
        HttpEntity entity = response.getEntity();
        String body = null;
        if (entity != null) {
            //按指定编码转换结果实体为String类型
            body = EntityUtils.toString(entity, charset);
        }
        EntityUtils.consume(entity);
        //释放链接
        response.close();
        return body;
    }

}
