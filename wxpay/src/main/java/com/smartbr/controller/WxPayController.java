package com.smartbr.controller;


import com.smartbr.common.core.domain.AjaxResult;
import com.smartbr.entity.bo.OnePayItem;
import com.smartbr.entity.bo.OnePayRefundsItem;
import com.smartbr.entity.bo.PayItem;
import com.smartbr.service.WxPayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Api(tags = "微信支付")
@RestController
@RequestMapping("wxPay")
public class WxPayController {
    @Autowired
    private WxPayService wxPayService;

    @ApiOperation(value = "单笔支付")
    @PostMapping("/onePay")
    public AjaxResult onePay(@RequestBody OnePayItem onePayItem) {
        return AjaxResult.success("操作成功", wxPayService.wxPayJSAPI(onePayItem));
    }

    @ApiOperation(value = "单笔支付完成告知")
    @PostMapping("/onePayAfter")
    public AjaxResult onePayAfter(@RequestBody String out_trade_no) {

        return AjaxResult.success("操作成功", wxPayService.wxOrderSearchJSAPI(out_trade_no));
    }


    @ApiOperation(value = "微信合单JSAPI支付")
    @PostMapping("/wxPayJSAPI")
    public AjaxResult wxPay(List<PayItem> payItems) throws Exception {
        //todo 支付申请入库 需要
        //支付的请求参数信息(此参数与微信支付文档一致，文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter3_1_1.shtml)
        String payInfo = "";
        String prepayId = "";
        //todo  支付完成 更新库中信息
        return AjaxResult.success("操作成功", prepayId);
    }

    @ApiOperation(value = "微信退款")
    @PostMapping("/wxPayRefund")
    public AjaxResult wxRefund(@RequestBody OnePayRefundsItem onePayRefundsItem) throws Exception {

        return AjaxResult.success("操作成功", wxPayService.wxPayRefund(onePayRefundsItem));

    }

}
