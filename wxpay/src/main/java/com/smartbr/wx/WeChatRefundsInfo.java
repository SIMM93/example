package com.smartbr.wx;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 合单 JSAPI 支付 请求json组建
 *
 * @author sunchao
 */
@Getter
@Setter
@Accessors(chain = true)
public class WeChatRefundsInfo {
    /**
     * 数据模型
     * {
     *   "transaction_id": "1217752501201407033233368018",
     *   "out_refund_no": "1217752501201407033233368018",
     *   "reason": "商品已售完",
     *   "notify_url": "https://weixin.qq.com",
     *   "funds_account": "AVAILABLE",
     *   "amount": {
     *     "refund": 888,
     *     "from": [
     *       {
     *         "account": "AVAILABLE",
     *         "amount": 444
     *       }
     *     ],
     *     "total": 888,
     *     "currency": "CNY"
     *   },
     *   "goods_detail": [
     *     {
     *       "merchant_goods_id": "1217752501201407033233368018",
     *       "wechatpay_goods_id": "1001",
     *       "goods_name": "iPhone6s 16G",
     *       "unit_price": 528800,
     *       "refund_amount": 528800,
     *       "refund_quantity": 1
     *     }
     *   ]
     * }
     * **/


    /**
     * 微信支付订单号
     * 商户订单号
     * 二选一
     */
    public String transaction_id = "";
    public String out_trade_no = "";


    /**
     * 商户退款单号
     */
    public String out_refund_no = "";
    /**
     * 原因
     */
    public String reason = "";


    /**
     * 接收微信支付异步通知回调地址，通知url必须为直接可访问的URL，不能携带参数。
     */
    public String notify_url = "";
    /**
     * 退款资金来源
     **/
    public String funds_account = "";

    /**
     * 金额信息
     */
    public HashMap<String, Object> amount = new HashMap<>();
    /**
     * 退款金额
     */
    public String refund = "";
    /**
     * 原订单金额
     */
    public String total = "";
    /**
     * 退款币种
     */
    public String currency = "";

    public void setAmount(String refund,
                          String total,
                          String currency,
                          List<ComplexInfoBank.from> fromList) {
        amount.put("refund", refund);
        amount.put("total", total);
        amount.put("currency", currency);
        amount.put("from", fromList);
        this.refund = refund;
        this.total = total;
        this.currency = currency;
    }

    /**
     * 指定商品退款需要传此参数，其他场景无需传递
     */
    public List<ComplexInfoBank.goods_detail> goods_detail = new ArrayList<>();


}
