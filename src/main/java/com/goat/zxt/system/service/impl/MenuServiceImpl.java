package com.goat.zxt.system.service.impl;


import com.goat.zxt.common.utils.Result;
import com.goat.zxt.common.utils.TreeUtil;
import com.goat.zxt.system.dao.MenuDao;
import com.goat.zxt.system.dao.RoleMenuDao;
import com.goat.zxt.system.dto.MenuDto;
import com.goat.zxt.system.dto.MenuIndexDto;
import com.goat.zxt.system.entity.SysMenu;
import com.goat.zxt.system.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private RoleMenuDao roleMenuDao;

    @Override
    public List<SysMenu> getMenuAll(String queryName, Integer queryType) {
        return menuDao.getFuzzyMenu(queryName,queryType);
    }

    @Override
    public SysMenu getMenuById(Integer id) {
        return menuDao.getMenuById(id);
    }

    @Override
    public List<MenuDto> buildMenuAll() {
        return menuDao.buildAll();
    }

    @Override
    public Result updateMenu(SysMenu menu) {
        menu.setIcon("layui-icon "+menu.getIcon());
        return (menuDao.update(menu) > 0) ? Result.ok().message("修改成功") : Result.error().message("修改失败");
    }

    @Override
    public Result<SysMenu> save(SysMenu menu) {
        menu.setIcon("layui-icon "+menu.getIcon());
        return (menuDao.save(menu) > 0) ? Result.ok().message("添加成功") : Result.error().message("添加失败");
    }


    /**
     * 如果这里删除了菜单树的父节点，把它的子节点一并删除
     */
    @Override
    @Transactional
    public Result delete(Integer id) {
        Integer count = roleMenuDao.countRoleMenuByRoleId(id);
        if (count == 0){
            menuDao.deleteById(id);
            List<Integer> list = menuDao.selectByParentId(id);
            if(list.size()>0){
                for (Integer parentId: list){
                    menuDao.deleteByParentId(parentId);
                }
                menuDao.deleteByParentId(id);
            }
            return Result.ok().message("删除成功");
        } else {
            return Result.error().message("已经有角色分配该菜单，无法删除");
        }

    }

    @Override
    public List<MenuDto> buildMenuAllByRoleId(Integer roleId) {
        List<MenuDto> menuList = menuDao.listByRoleId(roleId);
        List<MenuDto> menuListAll = menuDao.buildAll();
        List<MenuDto> tree = TreeUtil.menutree(menuList, menuListAll);
        return tree;
    }

    @Override
    public List<MenuIndexDto> getMenuByUserId(Integer userId) {
        List<MenuIndexDto> list = menuDao.listByUserId(userId);
        List<MenuIndexDto> result = TreeUtil.parseMenuTree(list);
        return result;
    }
}
