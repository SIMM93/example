
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.smartbr.wx.AesUtil;
import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
import com.wechat.pay.contrib.apache.httpclient.util.RsaCryptoUtil;
import lombok.SneakyThrows;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import static com.smartbr.wx.BasePay.sign;
import static com.smartbr.wx.WxPayV3Util.v3PayGet;
import static com.smartbr.wx.WxPayV3Util.getRequest;

/**
 * <p>>@作者 Sc
 * <p>>@所属包 smart-br:com.netrust.test
 * <p>>@创建时间 2021-07-20-10-17
 * <p>>@功能描述
 **/
public class wxpay {
    static String mchId = "1612088437"; // 商户号
    static String mchSerialNo = "35ED38DFF16498DC2B9DC117FBDA94A19FBE3090"; // 商户证书序列号
    static String apiV3Key = "WDFsunchaolufeifanshengjianbo201"; // api密钥
    static String path = "E:/pk/apiclient_key.pem";

    static String appid = "wxff79a780ba70d948";

    @SneakyThrows
    public static void main(String[] args) throws UnsupportedEncodingException {
        //onePay();//支付
        //    wxProfitSharing();//分账
        //  wxProfitSharingRefunds();   //分账退回
        // wxProfitSharingunfreeze();   //分账解冻
        //  refunds();//退单
        //   receiversAdd();
        //receiversDel();
        // findOrder();
        decryptOAEP();
    }


    @SneakyThrows
    public static void receiversAdd() {
        String reqdata = "  {\n" +
                "    \"appid\": \"wxff79a780ba70d948\",\n" +
                "    \"type\": \"PERSONAL_OPENID\",\n" +
                "    \"account\": \"oR9gT5IygoyLUZWPT5UVZDYH1EXo\",\n" +

                "    \"relation_type\": \"CUSTOM\",\n" +
                "    \"custom_relation\": \"测试LFF\"\n" +
                "  }";
        String profitSharingUrl = "https://api.mch.weixin.qq.com/v3/profitsharing/receivers/add";
        String body = v3PayGet(profitSharingUrl, reqdata, mchId, mchSerialNo, apiV3Key, path);
        System.out.println(body);
    }

    @SneakyThrows
    public static void receiversDel() {
        String reqdata = "{\n" +
                "  \"appid\": \"wxff79a780ba70d948\",\n" +
                "  \"type\": \"PERSONAL_OPENID\",\n" +
                "  \"account\": \"oR9gT5IygoyLUZWPT5UVZDYH1EXo\"\n" +
                "}";
        String profitSharingUrl = "https://api.mch.weixin.qq.com/v3/profitsharing/receivers/delete";
        String body = v3PayGet(profitSharingUrl, reqdata, mchId, mchSerialNo, apiV3Key, path);
        System.out.println(body);
    }

    @SneakyThrows
    public static void wxProfitSharing() {

        String reqdata = "{\n" +
                "  \n" +
                "    \"appid\": \"wxff79a780ba70d948\",\n" +
                "    \"transaction_id\": \"4200001149202107289947302305\",\n" +
                "    \"out_order_no\": \"nt_ceshi_Fz_006\",\n" +
                "    \"receivers\": [\n" +
                "      {\n" +
                "        \"type\": \"PERSONAL_OPENID\",\n" +
                "        \"account\": \"oR9gT5IygoyLUZWPT5UVZDYH1EXo\",\n" +

                "        \"amount\": 1,\n" +
                "        \"description\": \"分给lff\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"unfreeze_unsplit\": false\n" +
                "  }";


        String profitSharingUrl = "https://api.mch.weixin.qq.com/v3/profitsharing/orders";
        String body = v3PayGet(profitSharingUrl, reqdata, mchId, mchSerialNo, apiV3Key, path);
        System.out.println(body);
    }


    @SneakyThrows
    public static void wxProfitSharingRefunds() {
        String reqdata = "{\n" +
                "  \"order_id\": \"30001308232021072815499093735\",\n" +
                "  \"out_return_no\": \"sdqqqw1112\",\n" +
                "  \"return_mchid\": \"1612088437\",\n" +
                "  \"amount\": 200,\n" +
                "  \"description\": \"tk\"\n" +
                "}";
        String profitSharingUrl = "https://api.mch.weixin.qq.com/v3/profitsharing/return-orders";
        String body = v3PayGet(profitSharingUrl, reqdata, mchId, mchSerialNo, apiV3Key, path);
        System.out.println(body);
    }

    @SneakyThrows
    public static void wxProfitSharingunfreeze() {
        String reqdata = "{\n" +
                "  \"transaction_id\": \"4200001149202107289947302305\",\n" +
                "  \"out_order_no\": \"nt_ceshi_004\",\n" +
                "  \"description\": \"解冻全部剩余资金\"\n" +
                "}";
        String profitSharingUrl = "https://api.mch.weixin.qq.com/v3/profitsharing/orders/unfreeze";
        String body = v3PayGet(profitSharingUrl, reqdata, mchId, mchSerialNo, apiV3Key, path);
        System.out.println(body);
    }

    @SneakyThrows
    public static void combinePay() {
        String reqdata = "{\n" +
                "\t\"combine_out_trade_no\": \"hd_001\",\n" +
                "\t\"combine_mchid\": \"1612088437\",\n" +
                "\t\"combine_appid\": \"wxff79a780ba70d948\",\n" +
                "\n" +
                "\t\"sub_orders\": [{\n" +
                "\t\t\"mchid\": \"1612088437\",\n" +
                "\t\t\"attach\": \"景区\",\n" +
                "\t\t\"amount\": {\n" +
                "\t\t\t\"total_amount\": 1,\n" +
                "\t\t\t\"currency\": \"CNY\"\n" +
                "\t\t},\n" +
                "\t\t\"out_trade_no\": \"jq_ceshi_001\",\n" +
                "\t\t\"description\": \"景区\"\n" +
                "\t},\n" +
                "    {\n" +
                "\t\t\"mchid\": \"1612088437\",\n" +
                "\t\t\"attach\": \"出行\",\n" +
                "\t\t\"amount\": {\n" +
                "\t\t\t\"total_amount\": 1,\n" +
                "\t\t\t\"currency\": \"CNY\"\n" +
                "\t\t},\n" +
                "\t\t\"out_trade_no\": \"chuxing_ceshi_001\",\n" +
                "\t\t\"description\": \"出行测试\"\n" +
                "\t}\n" +
                "],\n" +
                "\t\"combine_payer_info\": {\n" +
                "\t\t\"openid\": \"oUpF8uMuAJO_M2pxb1Q9zNjWeS6o\"\n" +
                "\t},\n" +
                "\n" +
                "\t\"notify_url\": \"http://120.26.222.134:8024/document/insert/oneJson\"\n" +
                "}";
        String url = "https://api.mch.weixin.qq.com/v3/combine-transactions/jsapi";
        String body = v3PayGet(url, reqdata, mchId, mchSerialNo, apiV3Key, path);
        System.out.println(body);
        /*HashMap map = JSON.parseObject(body, HashMap.class);
        String prepay_id = "prepay_id=" + map.get("prepay_id");
        System.out.println("prepay_id: " + prepay_id);

        String timeStamp = String.valueOf(getTimeStamp());
        String nonceStr = getNonceStr();

        String k = createSign(timeStamp, nonceStr, prepay_id, appid, path);
        System.out.println("timeStamp: " + timeStamp);
        System.out.println("nonceStr: " + nonceStr);
        System.out.println("prepay_id: " + prepay_id);
        System.out.println("appid: " + appid);
        System.out.println("signType: " + "RSA");
        System.out.println("paySign: " + k);*/

    }

    @SneakyThrows
    public static void findOrder() {
        String reqdata = "";
        String out_trade_no = "nt_ceshi_002";
        String url = "https://api.mch.weixin.qq.com/v3/pay/transactions/out-trade-no/"
                + out_trade_no
                + "?mchid=" + mchId;
        String body = getRequest(url, mchId, mchSerialNo, path);
        System.out.println(body);

    }

    @SneakyThrows
    public static void refunds() {
        String reqdata = "{\n" +
                "  \"transaction_id\": \"4200001149202107289947302305\",\n" +
                "  \"out_refund_no\": \"4200001149202107289947302305\",\n" +
                "  \"reason\": \"商品已售完\",\n" +
                "  \"notify_url\": \"http://120.26.222.134:8024/document/insert/oneJson\",\n" +
                "  \"amount\": {\n" +
                "    \"refund\": 790,\n" +
                "    \"total\": 1000,\n" +
                "    \"currency\": \"CNY\"\n" +
                "  }\n" +
                "\n" +
                "}";
        String url = "https://api.mch.weixin.qq.com/v3/refund/domestic/refunds";
        String body = v3PayGet(url, reqdata, mchId, mchSerialNo, apiV3Key, path);
        System.out.println(body);

    }

    @SneakyThrows
    public static void onePay() {
        String reqdata = "{\n" +
                "    \"mchid\": \"1612088437\",\n" +
                "    \"out_trade_no\": \"nt_ceshi_Fz_004\",\n" +
                "    \"appid\": \"wxff79a780ba70d948\",\n" +
                "    \"description\": \"nt_ceshi_Fz_004\",\n" +
                "    \"notify_url\": \"https://weixin.qq.com/\",\n" +
                "    \"amount\": {\n" +
                "        \"total\": 1000,\n" +
                "        \"currency\": \"CNY\"\n" +
                "    },\n" +
                "    \"payer\": {\n" +
                "        \"openid\": \"oR9gT5IygoyLUZWPT5UVZDYH1EXo\"\n" +
                "    },\n" +
                "    \"settle_info\": {\n" +
                "        \"profit_sharing\": true\n" +
                "    }\n" +
                "}";
        String url = "https://api.mch.weixin.qq.com/v3/pay/transactions/jsapi";

        String body = v3PayGet(url, reqdata, mchId, mchSerialNo, apiV3Key, path);
        HashMap map = JSON.parseObject(body, HashMap.class);
        String prepay_id = "prepay_id=" + map.get("prepay_id");
        System.out.println("prepay_id: " + prepay_id);

        String timeStamp = String.valueOf(getTimeStamp());
        String nonceStr = getNonceStr();

        String k = createSign(timeStamp, nonceStr, prepay_id, appid, path);
        System.out.println("timeStamp: " + timeStamp);
        System.out.println("nonceStr: " + nonceStr);
        System.out.println("prepay_id: " + prepay_id);
        System.out.println("appid: " + appid);
        System.out.println("signType: " + "RSA");
        System.out.println("paySign: " + k);
    }


    @SneakyThrows
    public static String createSign(String timeStamp,
                                    String nonceStr,
                                    String prepay_id,
                                    String appid,
                                    String path
    ) {

        String privateKey = appid + "\n"
                + timeStamp + "\n"
                + nonceStr + "\n"
                + prepay_id + "\n";

        String signature = sign(privateKey.getBytes(), path);

        return signature;
    }

    public static long getTimeStamp() {
        Date d = new Date();
        long timeStamp = d.getTime() / 1000;     //getTime()得到的是微秒， 需要换算成秒
        return timeStamp;
    }

    public static String getNonceStr() {
        String s = UUID.randomUUID().toString().replace("-", "");
        return s;
    }

    @SneakyThrows
    public static String xxx(String msg) {

        String keyPath = "E:/pk/new/wechatpay3.pem";
        String content = new String(Files.readAllBytes(Paths.get(keyPath)), "UTF-8");
        X509Certificate wechatpayCertificate = PemUtil.loadCertificate(new ByteArrayInputStream(content.getBytes("utf-8")));

        return RsaCryptoUtil.encryptOAEP(msg, wechatpayCertificate);

    }


    @SneakyThrows
    public static String decryptOAEP() {
        String msg = "{\n" +
                "\tsummary: \"退款成功\",\n" +
                "\tevent_type: \"REFUND.SUCCESS\",\n" +
                "\tcreate_time: \"2021-07-29T16:16:46+08:00\",\n" +
                "\tresource: {\n" +
                "\t\tassociated_data: \"refund\",\n" +
                "\t\tciphertext: \"o3+N7ZXOU/JsCGbJhiyOliixOu9ObaW6zUDqUR8WVl+D+AV3qSUbZVBDorGxKi8GjQ0tNOjhkWsOo9IwgmwaV37YwPFA8QZEqPs4aQsGV9NhITGtnFPTqIDv9v2TbROXnkko/GoNgbnBwRI707PVWow/kKKDlSuRUX2R0kns75x/egvn6Lg2cJZ2oyqi6SzAsfDPmuaaa31lfHXMd86flroQ+9rbrtzP5vctelKicWw0VXu+8zAuvYItA0TSqVhKsZ58fK/Z0q2ji+0CqISClswWsGY7vl7O7XXc0l7i1nR/K0OTyS0/gP61yw1lGb/DMtweykd23hwzeUbCI271D8ZfY8/NVcbGbubgwF/AOaiXobSAzKrytymxY7QIFdt3R5CboUxcejxK3UP9RHI83uFeV+IRDdwgkcjFxZvgjy0Ta22xgbPoLu2pU4HGmUnq/HEg/fBm8Hknw8rY4FQJJDiv6JJCEzKsyDjWNIH4luFX9VBrTQ==\",\n" +
                "\t\toriginal_type: \"refund\",\n" +
                "\t\tnonce: \"xy7Pz7svkBAX\",\n" +
                "\t\talgorithm: \"AEAD_AES_256_GCM\"\n" +
                "\t},\n" +
                "\tresource_type: \"encrypt-resource\",\n" +
                "\tid: \"d84929b0-215d-5ca1-8f11-c1417509c408\"\n" +
                "}";
        String re = verifyNotify(msg, apiV3Key);
        System.out.println(re);
        return re;
    }

    static String verifyNotify(String body, String key) throws Exception {
        // 获取平台证书序列号
        JSONObject resultObject = JSONObject.parseObject(body);
        JSONObject resource = resultObject.getJSONObject("resource");
        String cipherText = resource.getString("ciphertext");
        String nonceStr = resource.getString("nonce");
        String associatedData = resource.getString("associated_data");
        AesUtil aesUtil = new AesUtil(key.getBytes(StandardCharsets.UTF_8));
        // 密文解密
        return aesUtil.decryptToString(
                associatedData.getBytes(StandardCharsets.UTF_8),
                nonceStr.getBytes(StandardCharsets.UTF_8),
                cipherText
        );
    }
}
