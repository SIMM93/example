package com.smartbr.wx;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>>@作者 Sc
 * <p>>@所属包 smart-br:com.netrust.wx
 * <p>>@创建时间 2021-07-20-09-20
 * <p>>@功能描述 复杂信息 暂且收纳
 **/
public class ComplexInfoBank {
    /**
     * 合并支付-子单信息
     */
    @Data
    public static class sub_orders {
        String mchid;
        String attach;
        Map<String, String> amount = new HashMap<>();
        String total_amount;
        String currency;
        String out_trade_no;
        String description;

        public void setAmount() {
            amount.put("total_amount", total_amount);
            amount.put("currency", currency);
        }

        Map<String, String> settle_info = new HashMap<>();
        String profit_sharing;
        String subsidy_amount;

        public void setSettle_info() {
            settle_info.put("profit_sharing", profit_sharing);
            settle_info.put("subsidy_amount", subsidy_amount);
        }
    }


    /**
     * 退单信息--退款出资账户及金额
     */
    @Data
    public static class from {
        String account;
        String amount;
    }


    @Data
    public static class goods_detail {
        String merchant_goods_id;
        String wechatpay_goods_id;
        String goods_name;
        String unit_price;
        String refund_amount;
        String refund_quantity;
    }
}
