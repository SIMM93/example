
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.smartbr.common.core.redis.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>>@���� Sc
 * <p>>@������ SmartBR:com.netrust.test
 * <p>>@����ʱ�� 2021-07-28-15-58
 * <p>>@��������
 **/
public class todo {

    public static void main(String[] args) {
        Date date = new Date();
      long ee=  date.getTime();
        System.out.println(ee);
    }

    public static String onePay() {
        Map<String, Object> mapInfo = new HashMap<>();
        mapInfo.put("out_trade_no", "");
        mapInfo.put("appid", "appid");
        mapInfo.put("description", "onePayItem.getDescription()");
        mapInfo.put("notify_url", "notify_url");
        Map<String, Object> amount = new HashMap<>();
        amount.put("total", 1);
        amount.put("currency", "currency");
        mapInfo.put("amount", amount);
        Map<String, Object> payer = new HashMap<>();
        payer.put("openid", "onePayItem.getOpenid()");
        mapInfo.put("payer", payer);
        String body = null;
        String jsonInfo = JSONObject.toJSONString(mapInfo);
        System.out.println(jsonInfo);
        return jsonInfo;

    }


}
