package com.smartbr.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.smartbr.config.RedisConstants;
import com.smartbr.factory.PayOrder;
import com.smartbr.service.WxRetMsgService;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.smartbr.wx.BasePay.verifyNotify;

/**
 * @author sc
 */
@Service
public class WxRetMsgServiceImpl implements WxRetMsgService {
    private static final Logger logger = LoggerFactory.getLogger(WxRetMsgServiceImpl.class);
    @Resource
    private MongoTemplate mongoTemplate;
    @Autowired
    private PayOrder payOrder;
    @Value("${wxPay.apiV3Key}")
    private String apiV3Key;

    @SneakyThrows
    @Override
    public Object insertJson(String json) {
        JSONObject parseObject = JSONObject.parseObject(json);
        JSONObject fxMsgJson = JSONObject.parseObject(verifyNotify(json, apiV3Key));
        String event_type = (String) parseObject.get("event_type");
        switch (event_type) {
            case "TRANSACTION.SUCCESS":
                setOnePayOrder(fxMsgJson);
                break;
            case "REFUND.SUCCESS":
                setOnePayRefundOrder(fxMsgJson);
                break;
            default:
                break;
        }
        logger.debug("fxmsg:{}", fxMsgJson);
        //解密
        parseObject.put("fixMsg", fxMsgJson);

        //入参解析
        JSONObject ret = mongoTemplate.insert(parseObject, "class");
        // 输出存储结果
        logger.debug("微信接受信息：{}", ret);
        return ret;
    }

    /**
     * 单笔支付订单回调
     *
     * @param fxMsgJson
     */
    private void setOnePayOrder(JSONObject fxMsgJson) {
        String out_trade_no = (String) fxMsgJson.get("out_trade_no");
        String trade_state = (String) fxMsgJson.get("trade_state");
        String transaction_id = (String) fxMsgJson.get("transaction_id");
        String trade_type = (String) fxMsgJson.get("trade_type");
        //入库
        logger.debug("支付订单{}状态{}", out_trade_no, trade_state);
        //轮询清除
        payOrder.setOrderFinish(RedisConstants.PAY_ONE_PAY_REFUNDS + out_trade_no);

    }

    /**
     * 单笔退单订单回调
     *
     * @param fxMsgJson
     */
    private void setOnePayRefundOrder(JSONObject fxMsgJson) {
        String out_trade_no = (String) fxMsgJson.get("out_trade_no");
        String refund_status = (String) fxMsgJson.get("refund_status");
        String out_refund_no = (String) fxMsgJson.get("out_refund_no");
        //入库
        logger.debug("退单订单{}状态{}", out_trade_no, refund_status);
        //轮询清除
        payOrder.setOrderFinish(RedisConstants.PAY_ONE_PAY_REFUNDS + out_refund_no);

    }

}
