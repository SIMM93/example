package com.smartbr.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * <p>>@作者 Sc
 * <p>>@所属包 SmartBR:com.smartbr.config
 * <p>>@创建时间 2021-07-30-10-35
 * <p>>@功能描述 增加配置项 便于替换
 **/
@Configuration
@PropertySource("classpath:Wxconfig.properties")
public class WxPayBaseConfigInfo {

}
