package com.goat.zxt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan({"com.goat.zxt.buzz.dao","com.goat.zxt.system.dao"})
//@EnableScheduling // 开启定时任务
public class GoatrbacApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(GoatrbacApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(GoatrbacApplication.class);
    }
}
