package com.goat.zxt.system.service;


import com.goat.zxt.common.utils.Result;
import com.goat.zxt.system.dto.MenuDto;
import com.goat.zxt.system.dto.MenuIndexDto;
import com.goat.zxt.system.entity.SysMenu;

import java.util.List;


public interface MenuService {
    /**
     * 返回菜单
     * @param queryName 用户名
     * @param queryType 类型
     */
    List<SysMenu> getMenuAll(String queryName, Integer queryType);

    /**
     * 获取菜单信息
     */
    SysMenu getMenuById(Integer id);

    /**
     * 菜单树
     */
    List<MenuDto> buildMenuAll();

    /**
     * 跟新菜单
     */
    Result updateMenu(SysMenu menu);

    /**
     * 保存菜单
     */
    Result<SysMenu> save(SysMenu menu);

    /**
     * 删除菜单
     */
    Result delete(Integer id);

    /**
     * 通过角色id绘制菜单树
     * @param roleId
     */
    List<MenuDto> buildMenuAllByRoleId(Integer roleId);

    /**
     * 通过用户id获取菜单
     */
    List<MenuIndexDto> getMenuByUserId(Integer userId);
}
