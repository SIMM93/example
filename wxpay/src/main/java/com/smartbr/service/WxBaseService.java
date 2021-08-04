package com.smartbr.service;

import com.smartbr.entity.BrWxUser;

import java.io.IOException;
import java.util.Map;

/**
 * <p>>@作者 Sc
 * <p>>@所属包 SmartBR:com.smartbr.service
 * <p>>@创建时间 2021-07-30-13-04
 * <p>>@功能描述
 **/
public interface WxBaseService {
    /**
     * 微信登录
     *
     * @param js_code 小程序登录时候获得的jsCode
     * @return
     * @throws IOException
     */
    Map<String, Object> weChatLogin(String js_code) throws IOException;

    /**
     * 小程序登录后 调用
     * 补充openId和 unionid
     *
     * @return
     */
    String saveWxInfo(BrWxUser brWxUser);
    String disableWXUser(BrWxUser brWxUser);


}
