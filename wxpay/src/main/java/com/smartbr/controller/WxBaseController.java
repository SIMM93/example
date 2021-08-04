package com.smartbr.controller;

import com.smartbr.common.core.domain.AjaxResult;
import com.smartbr.entity.BrWxUser;
import com.smartbr.service.WxBaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


/**
 * @author sunchao
 */
@Api(tags = "微信小程序后台 基础功能")
@RestController
@RequestMapping("wxMini")
public class WxBaseController {
    @Autowired
    private WxBaseService wxBaseService;

    @ApiOperation(value = "微信小程序登录")
    @GetMapping("/wxLogin")
    public AjaxResult wxlogin(String code) {
        try {
            return AjaxResult.success("操作成功", wxBaseService.weChatLogin(code));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return AjaxResult.success("小程序登录成功");
    }

    @ApiOperation(value = "更新微信用户信息  传递ID视为更新 不传递ID视为新增")
    @PostMapping("/wxUserInfoInsert")
    public AjaxResult wxUserInfo(@RequestBody BrWxUser brWxUser) {
        wxBaseService.saveWxInfo(brWxUser);
        return AjaxResult.success("操作成功");
    }
    @ApiOperation(value = "禁用微信用户")
    @GetMapping("/disableWXUser")
    public AjaxResult disableWXUser(@RequestBody BrWxUser brWxUser) {
        wxBaseService.disableWXUser(brWxUser);
        return AjaxResult.success("操作成功");
    }
}
