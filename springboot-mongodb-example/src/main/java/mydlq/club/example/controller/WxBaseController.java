package mydlq.club.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mydlq.club.example.service.runStart;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author sunchao
 */
@Api(tags = "微信小程序后台 基础功能")
@RestController
@RequestMapping("exa")
public class WxBaseController {

    @ApiOperation(value = "微信小程序登录")
    @GetMapping("/go")
    public Object wxlogin(String code) {
        runStart mythread = new runStart();
        mythread.start();
        return "小程序登录成功";
    }


}
