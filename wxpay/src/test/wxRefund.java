import com.smartbr.wx.WxPayInfoFactory;
import com.wechat.pay.contrib.apache.httpclient.WechatPayHttpClientBuilder;
import com.wechat.pay.contrib.apache.httpclient.auth.AutoUpdateCertificatesVerifier;
import com.wechat.pay.contrib.apache.httpclient.auth.PrivateKeySigner;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Credentials;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Validator;
import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.security.PrivateKey;

/**
 * <p>>@作者 Sc
 * <p>>@所属包 smart-br:com.netrust.test
 * <p>>@创建时间 2021-07-20-13-29
 * <p>>@功能描述
 **/
public class wxRefund {
    public static void main(String[] args) throws UnsupportedEncodingException {
        getRefundsInfo();
    }

    public static void getRefundsInfo() {
        String k = WxPayInfoFactory.getRefundsInfo();
        System.out.println(k);
    }

    public static void refunds() throws UnsupportedEncodingException {
        String privateKey = "-----BEGIN PRIVATE KEY-----\n" + "-----END PRIVATE KEY-----\n";
        PrivateKey merchantPrivateKey = PemUtil.loadPrivateKey(new ByteArrayInputStream(privateKey.getBytes("utf-8")));
        String mchId = ""; // 商户号
        String mchSerialNo = ""; // 商户证书序列号
        String apiV3Key = ""; // api密钥
        AutoUpdateCertificatesVerifier verifier = new AutoUpdateCertificatesVerifier(
                new WechatPay2Credentials(mchId, new PrivateKeySigner(mchSerialNo, merchantPrivateKey)),
                apiV3Key.getBytes("utf-8"));
        HttpPost httpPost = new HttpPost("https://api.mch.weixin.qq.com/v3/pay/transactions/jsapi");
        httpPost.addHeader("Accept", "application/json");
        httpPost.addHeader("Content-type", "application/json; charset=utf-8");

        try {
            CloseableHttpClient httpClient = WechatPayHttpClientBuilder.create()
                    .withMerchant(mchId, mchSerialNo, merchantPrivateKey)
                    .withValidator(new WechatPay2Validator(verifier)).build();
            // String reqdata = new wxPayInfoFactory().getPayInfo();
// 请求body参数
            String reqdata = "{"
                    + "\"combine_out_trade_no\":\"1217752501201407033233368018\","
                    + "\"combine_mchid\":\"1230000109\","
                    + "\"combine_appid\":\"wxd678efh567hg6787\","
                    + "\"scene_info\": {"
                    + "\"device_id\":\"POS1:1\","
                    + "\"payer_client_ip\":\"14.17.22.32\""
                    + "},"
                    + "\"sub_orders\": ["
                    + "{"
                    + "\"mchid\":\"1230000109\","
                    + "\"attach\":\"深圳分店\","
                    + "\"amount\": {"
                    + "\"total_amount\":10,"
                    + "\"currency\":\"CNY\""
                    + "},"
                    + "\"out_trade_no\":\"20150806125346\","
                    + "\"sub_mchid\":\"1900000109\","
                    + "\"description\":\"腾讯充值中心-QQ会员充值\""
                    + "}"
                    + "],"
                    + "\"combine_payer_info\": {"
                    + "\"openid\":\"oUpF8uMuAJO_M2pxb1Q9zNjWeS6o\""
                    + "},"
                    + "\"time_start\":\"2018-06-08T10:34:56+08:00\","
                    + "\"time_expire\":\"2018-06-08T10:34:56+08:00\","
                    + "\"notify_url\":\"https://yourapp.com/notify\""
                    + "}";
            StringEntity entity = new StringEntity(reqdata);
            entity.setContentType("application/json");
            httpPost.setHeader("Accept", "application/json");
            //完成签名并执行请求
            CloseableHttpResponse response = httpClient.execute(httpPost);
            String bodyAsString = EntityUtils.toString(response.getEntity());
            System.out.println(bodyAsString);
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
