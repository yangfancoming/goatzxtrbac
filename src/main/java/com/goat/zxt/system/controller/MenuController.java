package com.goat.zxt.system.controller;


import com.alibaba.fastjson.JSON;
import com.goat.zxt.common.utils.Result;
import com.goat.zxt.common.utils.ResultCode;
import com.goat.zxt.system.dto.MenuDto;
import com.goat.zxt.system.entity.SysMenu;
import com.goat.zxt.system.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/api/menu")
public class MenuController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MenuService menuService;

    @GetMapping("index")
    @PreAuthorize("hasAnyAuthority('menu:list')")
    public String index(){
        log.info("返回菜单页面");
        return "system/menu/menu";
    }

    @GetMapping
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('menu:list')")
    public Result getMenuAll(String queryName,Integer queryType){
        log.info("查询菜单列表");
        return Result.ok().data(menuService.getMenuAll(queryName,queryType)).code(ResultCode.TABLE_SUCCESS);
    }


    @GetMapping(value = "/edit")
    @PreAuthorize("hasAnyAuthority('menu:edit')")
    public String toEdit(Model model, SysMenu sysMenu) {
        log.info("修改菜单页面");
        model.addAttribute("sysMenu",menuService.getMenuById(sysMenu.getMenuId()));
        return "system/menu/menu-edit";
    }

    @GetMapping("/ebuild/{roleId}")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('role:add','role:edit')")
    public Result buildMenuAllByRoleId(@PathVariable Integer roleId){
        log.info("通过角色id绘制 回显菜单树 ---> roleId = {}", JSON.toJSONString(roleId));
        List<MenuDto> menuAll = menuService.buildMenuAllByRoleId(roleId);
        return Result.ok().data(menuAll);
    }

    @PutMapping
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('menu:edit')")
    public Result edit(@RequestBody SysMenu menu) {
        log.info("修改菜单");
        return menuService.updateMenu(menu);
    }

    @GetMapping(value = "/add")
    @PreAuthorize("hasAnyAuthority('menu:add')")
    public String add(Model model) {
        log.info("添加菜单页面");
        model.addAttribute("sysMenu",new SysMenu());
        return "system/menu/menu-add";
    }

    @GetMapping("/build")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('menu:add','menu:edit')")
    public Result buildMenuAll(){
        log.info("绘制 全量菜单树");
        List<MenuDto> menuAll = menuService.buildMenuAll();
        return Result.ok().data(menuAll);
    }

    @PostMapping
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('menu:add')")
    public Result<SysMenu> save(@RequestBody SysMenu sysMenu) {
        log.info("添加菜单");
        return menuService.save(sysMenu);
    }

    @DeleteMapping
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('menu:del')")
    public Result deleteMenu(Integer menuId) {
        log.info("删除菜单");
        return menuService.delete(menuId);
    }
}
