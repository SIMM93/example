package com.smartbr.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.smartbr.entity.BrWxUser;
import com.smartbr.mapper.BrWxUserMapper;
import com.smartbr.service.WxBaseService;
import com.smartbr.util.Util;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class WxBaseServiceImpl implements WxBaseService {
    private static final Logger logger = LoggerFactory.getLogger(WxBaseServiceImpl.class);
    @Autowired
    private BrWxUserMapper brWxUserMapper;
    @Value("${wxPay.appid}")
    private String appid;

    @Override
    public Map<String, Object> weChatLogin(String js_code) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        String secret = "24b6aae145c953ecd00f333e12577b51";
        String grant_type = "authorization_code";

        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appid
                + "&secret=" + secret
                + "&js_code=" + js_code
                + "&grant_type=" + grant_type;
        Request request = new Request.Builder()
                .url(url)
                .method("GET", null)
                .build();
        Response response = client.newCall(request).execute();
        String retBody = Objects.requireNonNull(response.body()).string();
        JSONObject jsonObject = (JSONObject) JSONObject.parse(retBody);
        String openid = (String) jsonObject.get("openid");
        String unionid = (String) jsonObject.get("unionid");
        BrWxUser brWxUser = brWxUserMapper.selectByOpenId(openid);
        //unionid 发生变化则更新数据库
        if (brWxUser != null) {
            if (unionid != null && !unionid.equals(brWxUser.getUnionId())) {
                brWxUser.setUnionId(unionid);
                brWxUser.setUpdateTime(new Date());
                brWxUserMapper.updateByPrimaryKey(brWxUser);
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("token", "暂无");
        map.put("tokenType", "暂无");
        map.put("exp", "暂无");
        map.put("refreshToken", "暂无");
        map.put("userInfo", brWxUser);
        map.put("expiresIn", Util.getTimeStamp());
        map.put("wxInfo", jsonObject);
        logger.info("小程序后台登录成功");
        return map;
    }

    @Override
    public String saveWxInfo(BrWxUser brWxUser) {
        if (brWxUser.getWxUserId() == null) {
            brWxUser.setCreateTime(new Date());
            brWxUserMapper.insert(brWxUser);
        } /*else {
            brWxUser.setUpdateTime(new Date());
            brWxUserMapper.updateByPrimaryKey(brWxUser);
        }
*/
        return "微信账号首次更新成功";
    }

    @Override
    public String disableWXUser(BrWxUser brWxUser) {
        if (brWxUser.getWxUserId() != null) {
            brWxUser.setState(1);
            brWxUser.setUpdateTime(new Date());
            brWxUserMapper.disableWXUser(brWxUser);
        }
        return "禁用微信用户" + brWxUser.getOpenId();
    }



}
