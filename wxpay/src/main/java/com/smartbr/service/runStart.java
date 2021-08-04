package com.smartbr.service;

import com.smartbr.common.core.redis.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * <p>>@作者 Sc
 * <p>>@所属包 springboot-mongodb-example:mydlq.club.example.service
 * <p>>@创建时间 2021-07-29-11-28
 * <p>>@功能描述
 **/
public class runStart {
    @Autowired
    private RedisCache redisCache;
    @Autowired
    public RedisTemplate redisTemplate;

    public void ScanPayOrder() {
        Collection<String> s = redisCache.keys("order");
        List<String> ss = new ArrayList<>();
        for (String key : s) {
         /*   ss.add(Objects.requireNonNull(redisTemplate.opsForValue().get(key)).toString());*/


        }
    }
}
