package com.smartbr.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.smartbr.common.utils.StringUtils;
import com.smartbr.config.RedisConstants;
import com.smartbr.entity.BrReserveOrder;
import com.smartbr.entity.bo.OnePayItem;
import com.smartbr.entity.bo.OnePayRefundsItem;
import com.smartbr.factory.PayOrder;
import com.smartbr.mapper.WxOrderMapper;
import com.smartbr.mapper.WxOrderRefundMapper;
import com.smartbr.service.WxPayService;
import com.smartbr.util.Util;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.smartbr.wx.BasePay.sign;
import static com.smartbr.wx.WxPayV3Util.getRequest;
import static com.smartbr.wx.WxPayV3Util.v3PayGet;

@Service
public class WxPayServiceImpl implements WxPayService {

    private static final Logger logger = LoggerFactory.getLogger(WxPayServiceImpl.class);
    @Autowired
    public RedisTemplate redisTemplate;

    @Autowired
    private WxOrderMapper wxOrderMapper;
    @Autowired
    private WxOrderRefundMapper wxOrderRefundMapper;

    @Autowired
    private PayOrder payOrder;
    @Value("${wxPay.mchId}")
    private String mchId;
    @Value("${wxPay.mchSerialNo}")
    private String mchSerialNo;

    @Value("${wxPay.keyPath}")
    private String keyPath;
    @Value("${wxPay.keyPath_PTJM}")
    private String keyPath_PTJM;
    @Value("${wxPay.appid}")
    private String appid;
    @Value("${wxPay.singlePayUrl}")
    private String singlePayUrl;
    @Value("${wxPay.refundUrl}")
    private String refundUrl;
    @Value("${wxPay.combineUrl}")
    private String combineUrl;
    @Value("${wxPay.profitSharingUrl}")
    private String profitSharingUrl;
    @Value("${wxPay.notify_url}")
    private String notify_url;
    /**
     * 回调退款URL
     */
    @Value("${wxPay.orderSearchByOut_trade_no_Url}")
    private String orderSearchByOut_trade_no_Url;


    //公众号
    @Value("${wxGZH.secret}")
    private String secret;
    @Value("${wxGZH.gzhappid}")
    private String gzhappid;
    @Value("${wxGZH.templateId}")
    private String templateId;
    String currency = "CNY";

    @SneakyThrows
    @Override
    public String wxOrderSearchJSAPI(String out_trade_no) {
        Thread.sleep(500);
        JSONObject jsonNode = (JSONObject) JSONObject.parse(out_trade_no);
        out_trade_no = (String) jsonNode.get("out_trade_no");

        String url = orderSearchByOut_trade_no_Url + out_trade_no + "?mchid=" + mchId;
        String body = getRequest(url, mchId, mchSerialNo, keyPath);
        JSONObject jsonObject = (JSONObject) JSONObject.parse(body);
        //微信支付单号
        String transaction_id = (String) jsonObject.get("transaction_id");
        /**
         * SUCCESS：支付成功
         * REFUND：转入退款
         * NOTPAY：未支付
         * CLOSED：已关闭
         * REVOKED：已撤销（付款码支付）
         * USERPAYING：用户支付中（付款码支付）
         * PAYERROR：支付失败(其他原因，如银行返回失败)
         * ACCEPT：已接收，等待扣款
         */

        String trade_state = (String) jsonObject.get("trade_state");
        if (!trade_state.equals("SUCCESS") && !trade_state.equals("REFUND")) {
            payOrder.setOrderNeedPoll(RedisConstants.PAY_ONE_PAY + out_trade_no, out_trade_no);
        }

        logger.debug(out_trade_no + ":" + transaction_id + "：支付状态" + trade_state);
        // update订单库
        /*BrReserveOrder brReserveOrder = new BrReserveOrder();
        brReserveOrder.setOrderId();
        brReserveOrder.setOrderTradeNo();
        //订单状态（0：预约中；1：预约失败；2：退款中；3：已退款；4：退款失败；5：待使用；6：已使用）
        brReserveOrder.setOrderState();
        wxOrderMapper.updateByPrimaryKeySelective(brReserveOrder);*/

        //todo    推送 sendWechatNotice();
        return out_trade_no + ":" + transaction_id + "：支付状态" + trade_state;
    }

    @Override
    public HashMap<String, String> wxPayJSAPI(OnePayItem onePayItem) {
        String orderId = onePayItem.getOut_trade_no();
        Map<String, Object> mapInfo = new HashMap<>();
        mapInfo.put("mchid", mchId);
        mapInfo.put("out_trade_no", orderId);
        mapInfo.put("appid", appid);
        mapInfo.put("description", onePayItem.getDescription());
        mapInfo.put("notify_url", notify_url);
        Map<String, Object> amount = new HashMap<>();
        amount.put("total", onePayItem.getTotal());
        amount.put("currency", currency);
        mapInfo.put("amount", amount);
        Map<String, Object> payer = new HashMap<>();
        payer.put("openid", onePayItem.getOpenid());
        mapInfo.put("payer", payer);
        String body = null;
        String jsonInfo = JSONObject.toJSON(mapInfo).toString();
        try {
            body = v3PayGet(singlePayUrl, jsonInfo, mchId, mchSerialNo, "wxPayJSAPI", keyPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        HashMap map = JSON.parseObject(body, HashMap.class);
        String prepay_id = "prepay_id=" + map.get("prepay_id");
        //分析景区系统+公交系统过来的数据


        //执行下单支付操作

        //final 请求日志入库


        return retPrePay(prepay_id);
    }

    @Override
    public String wxCombinePayJSAPI(String jsonInfo) {
        String body = null;
        try {
            body = v3PayGet(combineUrl, jsonInfo, mchId, mchSerialNo, "wxCombinePayJSAPI", keyPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        HashMap map = JSON.parseObject(body, HashMap.class);
        String prepay_id = "prepay_id=" + map.get("prepay_id");
        //分析景区系统+公交系统过来的数据

        //执行下单支付操作

        //订单数据入库mysql

        //final 请求日志入库
        HashMap<String, String> signMap = retPrePay(prepay_id);
        return JSONObject.toJSONString(signMap);
    }

    /**
     * {
     * "sub_mchid": "1900000109",
     * "appid": "wx8888888888888888",
     * "sub_appid": "wx8888888888888889",
     * "transaction_id": "4208450740201411110007820472",
     * "out_order_no": "P20150806125346",
     * "receivers": [
     * {
     * "type": "MERCHANT_ID",
     * "account": "86693852",
     * "name": "hu89ohu89ohu89o",
     * "amount": 30,
     * "description": "分给商户A"
     * }
     * ],
     * "unfreeze_unsplit": true
     * }
     *
     * @param jsonInfo
     * @return
     */
    @Override
    public String wxProfitSharing(String jsonInfo) {
        String body = null;
        try {
            body = v3PayGet(profitSharingUrl, jsonInfo, mchId, mchSerialNo, "wxProfitSharing", keyPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        HashMap map = JSON.parseObject(body, HashMap.class);
        String prepay_id = "prepay_id=" + map.get("prepay_id");
        //分析景区系统+公交系统过来的数据

        //执行下单支付操作

        //订单数据入库mysql

        //final 请求日志入库
        HashMap<String, String> signMap = retPrePay(prepay_id);
        return JSONObject.toJSONString(signMap);
    }

    @Override
    public String wxPayRefund(OnePayRefundsItem onePayRefundsItem) {
        //退单号
        String out_refund_no = String.valueOf(Util.getNum("refund"));
        Map<String, Object> map = new HashMap<>();
        String out_trade_no = onePayRefundsItem.getOut_trade_no();
        map.put("out_trade_no", out_trade_no);
        map.put("out_refund_no", out_refund_no);
        map.put("reason", onePayRefundsItem.getReason());
        map.put("notify_url", notify_url);
        Map<String, Object> amount = new HashMap<>();

        amount.put("refund", onePayRefundsItem.getRefund());
        amount.put("total", onePayRefundsItem.getTotal());
        amount.put("currency", currency);
        map.put("amount", amount);
        String jsonInfo = JSONObject.toJSONString(map);
        String body = null;
        try {
            body = v3PayGet(refundUrl, jsonInfo, mchId, mchSerialNo, "wxPayRefund", keyPath);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = (JSONObject) JSONObject.parse(body);
        String refundStatus = (String) jsonObject.get("status");
        if (refundStatus == null) {
            return String.valueOf(jsonObject);
        }
        if (!refundStatus.equals("SUCCESS")) {
            payOrder.setOrderNeedPoll(RedisConstants.PAY_ONE_PAY_REFUNDS + out_refund_no, out_refund_no);
        }
        //todo switch 退款状态
        //进行退单检验

        //执行退单操作

        //订单数据入库mysql

        //final 请求日志入库
/**
 * 退款返回数据
 * {
 *     "amount": {
 *         "currency": "CNY",
 *         "discount_refund": 0,
 *         "from": [],
 *         "payer_refund": 1,
 *         "payer_total": 1,
 *         "refund": 1,
 *         "settlement_refund": 1,
 *         "settlement_total": 1,
 *         "total": 1
 *     },
 *     "channel": "ORIGINAL",
 *     "create_time": "2021-07-23T09:32:34+08:00",
 *     "funds_account": "AVAILABLE",
 *     "out_refund_no": "4200001182202107221035429014",
 *     "out_trade_no": "nt_ceshi_001",
 *     "promotion_detail": [],
 *     "refund_id": "50300808852021072310874969618",
 *     "status": "PROCESSING",
 *     "transaction_id": "4200001182202107221035429014",
 *     "user_received_account": "支付用户零钱"
 * }
 */
        return "退单成功：" + refundStatus;
    }

    /**
     * 申请支付成功 发送给小程序端 唤起支付
     *
     * @param prepay_id
     * @return
     */
    public HashMap<String, String> retPrePay(String prepay_id) {
        String timeStamp = String.valueOf(Util.getTimeStamp());
        String nonceStr = Util.getNonceStr();
        String k = createSign(timeStamp, nonceStr, prepay_id, appid, keyPath);
        HashMap<String, String> retMap = new HashMap<>();
        retMap.put("timeStamp", timeStamp);
        retMap.put("nonceStr", nonceStr);
        retMap.put("prepay_id", prepay_id);
        retMap.put("appid", appid);
        retMap.put("signType", "RSA");
        retMap.put("paySign", k);
        return retMap;
    }

    /**
     * 创建签名
     *
     * @param timeStamp
     * @param nonceStr
     * @param prepay_id
     * @param appid
     * @param path
     * @return
     */
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

    public static String getDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:dd");
        String endStr = simpleDateFormat.format(new Date());
        return endStr;
    }


    public void sendWechatNotice(String unionId, String msgType, String msg, String openId) {
        //当没有关注公众号的情况下无需推送信息
        if (StringUtils.isEmpty(unionId)) {
            return;
        }
        //数据库内查询unionId
        /*     WXAccount wxAccount = wxAccountMapper.findOne(M.ins("unionid", unionid).getMap());*/
        String accessToken = getAccessToken();
        String wxopenid = "";
        //看看数据库是否存在
    /*    if (wxAccount != null) {
            wxopenid = wxAccount.getOpenid();
        } else {
            //不存在则去请求
            wxopenid = saveWxAccount(unionId, accessToken);
        }*/
        if (StringUtils.isEmpty(wxopenid)) {
            logger.info("未查找到该微信用户[{}]", wxopenid);
            return;
        }
        String sendUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + accessToken;
        Map<String, Map<String, String>> jsonMap = new HashMap<>();
        Map<String, String> firstValueMap = new HashMap<>();
        firstValueMap.put("value", "xxxx提示");
        jsonMap.put("first", firstValueMap);
        Map<String, String> keyword1Map = new HashMap<>();
        keyword1Map.put("value", msgType + "通知");
        jsonMap.put("keyword1", keyword1Map);
        Map<String, String> keyword2Map = new HashMap<>();
        keyword2Map.put("value", getDate());
        jsonMap.put("keyword2", keyword2Map);
        Map<String, String> remarks = new HashMap<>();
        remarks.put("value", msg);
        jsonMap.put("remark", remarks);
        Map<String, Object> sendMsg = new HashMap<>();
        sendMsg.put("touser", wxopenid);
        sendMsg.put("template_id", templateId);
        sendMsg.put("data", jsonMap);

        /* String result = restTemplate.postForObject(sendUrl, sendMsg, String.class);*/
        logger.info("微信接口调用成功【{}】", "result");

    }


    public String getAccessToken() {
        final String constAccessToken = "access_token";
        if (redisTemplate.hasKey(constAccessToken)) {
            return String.valueOf(redisTemplate.opsForValue().get(constAccessToken));
        }
        String accUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
        accUrl = String.format(accUrl, gzhappid, secret);
        String json = "restTemplate.getForEntity(accUrl, String.class).getBody()";
        logger.info("获取json数据为");
        JSONObject jsonObject = null;
        try {
            jsonObject = JSON.parseObject(json);
        } catch (Exception e) {
            logger.error("错误", e);
        }
        assert jsonObject != null;
        if (jsonObject.containsKey(constAccessToken)) {
            String token = jsonObject.getString(constAccessToken);
            //accessToken 过期时间为120分钟
            redisTemplate.opsForValue().set(constAccessToken, token, 7100, TimeUnit.SECONDS);
            return token;
        } else {
            throw new RuntimeException("微信获取授权失败");
        }
    }
}
