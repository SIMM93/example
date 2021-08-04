package com.smartbr.controller.order;

import com.smartbr.common.core.domain.AjaxResult;
import com.smartbr.entity.BrWxUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>>@作者 Sc
 * <p>>@所属包 SmartBR:com.smartbr.controller.order
 * <p>>@创建时间 2021-07-29-08-57
 * <p>>@功能描述
 **/
@Api(tags = "支付订单相关")
@RestController
@RequestMapping("orderPay")
public class OrderPayController {

    @ApiOperation(value = "查询支付订单状态")
    @PutMapping("/queryPayStatusBy")
    public AjaxResult queryPayStatusBy(@RequestBody BrWxUser brWxUser) {

        return AjaxResult.success();
    }
    @ApiOperation(value = "查询退款订单状态")
    @PutMapping("/queryRefundsStatusBy")
    public AjaxResult queryRefundsStatus(@RequestBody BrWxUser brWxUser) {
        return AjaxResult.success();
    }

    @ApiOperation(value = "更新订单状态")
    @PutMapping("/putPayStatus")
    public AjaxResult putPayStatus(@RequestBody BrWxUser brWxUser) {

        return AjaxResult.success();
    }

    @ApiOperation(value = "资金解冻/分账/核销")
    @PutMapping("/unfreeze")
    public AjaxResult unfreeze(@RequestBody BrWxUser brWxUser) {

        return AjaxResult.success();
    }

}
