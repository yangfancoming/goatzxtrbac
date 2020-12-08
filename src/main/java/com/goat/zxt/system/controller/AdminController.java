package com.goat.zxt.system.controller;


import com.goat.zxt.security.dto.JwtUserDto;
import com.goat.zxt.system.dto.MenuIndexDto;
import com.goat.zxt.system.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("/api")
public class AdminController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MenuService menuService;

    @GetMapping(value = "/index")
    @ResponseBody
    public List<MenuIndexDto> getMenu() {
        log.info("通过用户id获取菜单"); // doit  这里没有使用用户id参数，如果多个用户登录 那么是怎么样区分  用户菜单的呢？
        JwtUserDto jwtUserDto = (JwtUserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer userId = jwtUserDto.getSysUser().getUserId();
        return menuService.getMenuByUserId(userId);
    }
    
    @GetMapping("/console")
    public String console(){
        log.info("后台首页");
        return "console/console1";
    }

    @GetMapping("/403")
    public String error403(){
        log.info("403页面");
        return "error/403";
    }

    @GetMapping("/404")
    public String error404(){
        log.info("404页面");
        return "error/404";
    }

    @GetMapping("/500")
    public String error500(){
        log.info("500页面");
        return "error/500";
    }

}
