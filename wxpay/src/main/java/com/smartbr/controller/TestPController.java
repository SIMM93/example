package com.smartbr.controller;

import com.smartbr.common.core.domain.AjaxResult;
import com.smartbr.common.core.redis.RedisCache;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/**
 * @author sunchao
 */
@Api(tags = "测试")
@RestController
@RequestMapping("test")
public class TestPController {

    @Autowired
    private RedisCache redisCache;
    @Autowired
    public RedisTemplate redisTemplate;

    @ApiOperation(value = "")
    @PostMapping("/setRedis")
    public AjaxResult wxUserInfo(String path, String vlaue) {
        redisCache.setCacheObject(path, vlaue);
        return AjaxResult.success();
    }

    @ApiOperation(value = "")
    @GetMapping("/getRedis")
    public AjaxResult getRedis(String path) {

      /*  Collection<String> s = redisCache.keys(path);
        List<String> ss = new ArrayList<>();
        for (String key : s) {
            ss.add(Objects.requireNonNull(redisTemplate.opsForValue().get(key)).toString());
        }*/


        return AjaxResult.success("ss");
    }

    @ApiOperation(value = "")
    @GetMapping("/startPayCheck")
    public AjaxResult startPayCheck(String path) {
        Collection<String> s = redisCache.keys(path);
        List<String> ss = new ArrayList<>();
        for (String key : s) {
            ss.add(Objects.requireNonNull(redisTemplate.opsForValue().get(key)).toString());
        }

        return AjaxResult.success(ss);
    }
}
