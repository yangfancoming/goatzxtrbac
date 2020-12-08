package com.goat.zxt.system.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/login").setViewName("/login/login");
//        registry.addViewController("/logout").setViewName("login");
//        registry.addViewController("/").setViewName("redirect:/login"); // sos  shiro退出后会请求到这里需要重定向下 否则页面报错 用户不存在。
//        registry.addViewController("/logout").setViewName("redirect:/login");
//        registry.addViewController("/").setViewName("redirect:/index");

    }

}

