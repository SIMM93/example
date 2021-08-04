package com.smartbr.wx;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 合单 JSAPI 支付 请求json组建
 *
 * @author sunchao
 */
@Getter
@Setter
@Accessors(chain = true)
public class WeChatPayInfo {
    /**
     * 数据模型{
     * {
     * 	"combine_appid": "wxd678efh567hg6787",
     * 	"combine_out_trade_no": "20150806125346",
     * 	"combine_mchid": "1900000109",
     * 	"scene_info": {
     * 		"device_id": "POS1:123",
     * 		"payer_client_ip": "14.17.22.32"
     *        },
     * 	"sub_orders": [{
     * 		"mchid": "1230000119",
     * 		"attach": "公交",
     * 		"amount": {
     * 			"total_amount": 10,
     * 			"currency": "CNY"
     *        },
     * 		"out_trade_no": "20150806125346",
     * 		"description": "腾讯充值中心-QQ会员充值",
     * 		"settle_info": {
     * 			"profit_sharing": true,
     * 			"subsidy_amount": 10
     *        }
     *    }, {
     * 		"mchid": "1230000120",
     * 		"attach": "景区",
     * 		"amount": {
     * 			"total_amount": 10,
     * 			"currency": "CNY"
     *        },
     * 		"out_trade_no": "20150806125346",
     * 		"description": "腾讯充值中心-QQ会员充值",
     * 		"settle_info": {
     * 			"profit_sharing": true,
     * 			"subsidy_amount": 10
     *        }
     *    }],
     * 	"time_start": "2019-12-31T15:59:59+08:00",
     * 	"time_expire": "2019-12-31T16:59:59+08:00",
     * 	"notify_url": "https://yourapp.com/notify"
     * }
     * **/

    /**
     * 合单发起方的appid
     */
    public String combine_appid = "";
    /**
     * 合单商户号
     */
    public String combine_mchid = "";
    /**
     * 合单支付总订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一 。    示例值：P20150806125346
     */
    public String combine_out_trade_no = "";


    /**
     * 商户端设备号
     */
    public String device_id = "";
    /**
     * 用户终端IP
     */
    public String payer_client_ip = "";
    /**
     * 支付场景信息描述
     */
    public Map<String, String> scene_info = new HashMap<>();

    public void setScene_info(String device_id, String payer_client_ip) {
        scene_info.put("device_id", device_id);
        scene_info.put("payer_client_ip", payer_client_ip);
        this.device_id = device_id;
        this.payer_client_ip = payer_client_ip;
    }


    /**
     * 支付者信息
     */
    public HashMap<String, String> combine_payer_info = new HashMap<>();

    public String openid = "";

    public void setCombine_payer_info(String openid) {
        combine_payer_info.put("openid", openid);
        this.openid = openid;
    }



/*    public void setCombine_payer_info(String openid) {
        combine_payer_info.put("openid", openid);
        this.openid = openid;
    }*/

    /**
     * 子单信息 	sub_orders 	array 	是 	body最多支持子单条数：10
     */
    public List<ComplexInfoBank.sub_orders> sub_orders = new ArrayList<>();

    /**
     * 交易起始时间
     */
    public String time_start = "";


    /**
     * 交易结束时间
     */
    public String time_expire = "";
    /**
     * 接收微信支付异步通知回调地址，通知url必须为直接可访问的URL，不能携带参数。
     */
    public String notify_url = "";


}
