package com.smartbr.wx;

import com.alibaba.fastjson.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>>@作者 Sc
 * <p>>@所属包 smart-br:com.netrust.wx
 * <p>>@创建时间 2021-07-20-10-44
 * <p>>@功能描述
 **/
public class WxPayInfoFactory {


    /**
     * 根据传入信息填充支付信息
     *
     * @return json信息串
     */
    public String getPayInfo() {
        List<ComplexInfoBank.sub_orders> sub_ordersList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            ComplexInfoBank.sub_orders subOrders = new ComplexInfoBank.sub_orders();
            subOrders.setMchid("");
            subOrders.setAttach("");
            subOrders.setTotal_amount("");
            subOrders.setCurrency("");
            subOrders.setOut_trade_no("");
            subOrders.setDescription("");
            subOrders.setAmount();
            subOrders.setProfit_sharing("");
            subOrders.setSubsidy_amount("");
            subOrders.setSettle_info();
            sub_ordersList.add(subOrders);
        }
        WeChatPayInfo wc = new WeChatPayInfo();
        wc.setSub_orders(sub_ordersList);
        wc.setCombine_appid("");
        wc.setCombine_mchid("");
        wc.setCombine_out_trade_no("");
        wc.setNotify_url("www.**");
        wc.setTime_start(getTimeStampToRfc3339(System.currentTimeMillis()));
        wc.setCombine_payer_info("openId");
        wc.setScene_info("de", "ip");
        String kk = JSONObject.toJSONString(wc);
        System.out.println(kk);
        return kk;

    }
    //todo 等待支付实际信息

    static String getTimeStampToRfc3339(long timeStamp) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        String formatDate = simpleDateFormat.format(new Date(timeStamp));
        return formatDate;
    }

    /**
     * 根据传入信息填充退款信息
     *
     * @return json信息串
     */
    public static String getRefundsInfo() {
        List<ComplexInfoBank.from> fromList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ComplexInfoBank.from from = new ComplexInfoBank.from();
            from.setAccount("");
            from.setAmount("");
            fromList.add(from);
        }
        WeChatRefundsInfo wc = new WeChatRefundsInfo();
        wc.setOut_refund_no("");
        wc.setTransaction_id("");
        wc.setReason("");
        wc.setNotify_url("");
        wc.setFunds_account("");
        wc.setRefund("");
        wc.setAmount("", "", "", fromList);
        wc.setGoods_detail(null);
        String kk = JSONObject.toJSONString(wc);

        return kk;

    }

}
