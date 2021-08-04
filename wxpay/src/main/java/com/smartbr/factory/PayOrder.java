package com.smartbr.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * <p>>@作者 Sc
 * <p>>@所属包 SmartBR:com.smartbr.factory
 * <p>>@创建时间 2021-07-30-09-11
 * <p>>@功能描述 支付订单回执 业务操作
 **/
@Component
public class PayOrder {
    private static final Logger logger = LoggerFactory.getLogger(PayOrder.class);
    @Autowired
    public RedisTemplate redisTemplate;

    public boolean setOrderFinish(String key) {
        logger.debug("key{} was finish", key);
        redisTemplate.delete(key);
        return redisTemplate.hasKey(key);
    }

    public boolean setOrderNeedPoll(String key, String value) {
        logger.debug("key{}setOrderNeedPoll", key);
        redisTemplate.opsForValue().set(key, value);
        return redisTemplate.hasKey(key);
    }

}
