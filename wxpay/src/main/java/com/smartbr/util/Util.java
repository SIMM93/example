package com.smartbr.util;

import java.util.UUID;

/**
 * <p>>@作者 Sc
 * <p>>@所属包 SmartBR:com.smartbr.util
 * <p>>@创建时间 2021-07-28-13-48
 * <p>>@功能描述
 **/
public class Util {

    public static long getTimeStamp() {
        return System.currentTimeMillis() / 1000;
    }


    public static long getNum(String orderType) {
        return new SnowflakeIdWorker(21, 31).nextId();
    }

    public static String getNonceStr() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
