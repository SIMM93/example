package com.smartbr.entity.bo;

import lombok.Data;

/**
 * <p>>@作者 Sc
 * <p>>@所属包 SmartBR:com.smartbr.entity.bo
 * <p>>@创建时间 2021-07-27-16-38
 * <p>>@功能描述
 **/
@Data
public class OnePayItem {
    /**
     * 金额
     */
    int total;
    String openid;
    /**
     * 内部后台订单号
     */
    String out_trade_no;
    /**
     * 商品描述
     */
    String description;
}
