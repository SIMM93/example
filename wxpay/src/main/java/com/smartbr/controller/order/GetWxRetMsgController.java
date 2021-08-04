package com.smartbr.controller.order;

import com.smartbr.service.WxRetMsgService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author sunchao
 */
@Api(tags = "测试")
@RestController
@RequestMapping("document")
public class GetWxRetMsgController {
    @Autowired
    WxRetMsgService wxRetMsgService;

    @PostMapping("/insert/oneJson")
    public Object insertDataJson(@RequestBody String newMssage) {
        wxRetMsgService.insertJson(newMssage);
        return "{   \n" +
                "    \"code\": \"SUCCESS\",\n" +
                "    \"message\": \"成功\"\n" +
                "}";
    }

}
