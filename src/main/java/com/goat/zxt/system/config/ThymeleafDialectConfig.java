package com.goat.zxt.system.config;

import com.goat.zxt.system.dialect.SysDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ThymeleafDialectConfig {

    /**
     * 系统方言
     * 主要作用有：
     * 1. 处理字典数据展示
     */
    @Bean
    public SysDialect sysDialect() {
        return new SysDialect();
    }
}