package com.goat.zxt.common.utils;

import com.goat.zxt.common.exceptionhandler.MyException;
import com.goat.zxt.system.dto.MenuDto;
import com.goat.zxt.system.dto.MenuIndexDto;
import com.goat.zxt.system.dto.TreeDto;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class TreeUtil {

    /**
     * @param menuList 通过角色id查询的menuid
     * @param menuListAll 返回的menutree
     */
    public static List<MenuDto> menutree(List<MenuDto> menuList, List<MenuDto> menuListAll){
        if (ObjectUtils.isEmpty(menuList) || ObjectUtils.isEmpty(menuListAll)){
            throw new MyException(ResultCode.ERROR,"权限菜单为空！");
        }
        List<Integer> menuListIds = menuList.stream().map(MenuDto::getId).collect(Collectors.toList());
        List<Integer> allIds = menuListAll.stream().map(MenuDto::getId).collect(Collectors.toList());
        for (Integer id : menuListIds) {
            // 如果存在这个数
            if (allIds.contains(id)) {
                int i = allIds.indexOf(id);
                MenuDto menuDto = menuListAll.get(i);
                menuDto.setCheckArr("1");
                menuListAll.set(i,menuDto);
            }
        }
        return menuListAll;
    }

    public static List<TreeDto> deptTree(List<TreeDto> listById, List<TreeDto> lists ){
        List<Integer> deptIds = listById.stream().map(TreeDto::getId).collect(Collectors.toList());
        List<Integer> allIds = lists.stream().map(TreeDto::getId).collect(Collectors.toList());
        for (Integer item : deptIds) {
            if (allIds.contains(item)) {
                int i = allIds.indexOf(item);
                TreeDto deptDto = lists.get(i);
                deptDto.setCheckArr("1");
                lists.set(i,deptDto);
            }
        }
        return lists;
    }

//    public static void setMenuTree(Integer parentId, List<MenuIndexDto> menusAll, JSONArray array) {
//        for (MenuIndexDto per : menusAll) {
//            if (per.getParentId().equals(parentId)) {
//                String string = JSONObject.toJSONString(per);
//                JSONObject parent = (JSONObject) JSONObject.parse(string);
//                array.add(parent);
//                if (menusAll.stream().filter(p -> p.getParentId().equals(per.getId())).findAny() != null) {
//                    JSONArray child = new JSONArray();
//                    parent.put("child", child);
//                    setMenuTree(per.getId(), menusAll, child);
//                }
//            }
//        }
//    }

    public static List<MenuIndexDto> parseMenuTree(List<MenuIndexDto> list){
        List<MenuIndexDto> result = new ArrayList<>();
        // 1、获取第一级节点
        for (MenuIndexDto menu : list) {
            if(menu.getParentId() == 0) {
                result.add(menu);
            }
        }
        // 2、递归获取子节点
        for (MenuIndexDto parent : result) {
            recursiveTree(parent, list);
        }
        return result;
    }

    public static MenuIndexDto recursiveTree(MenuIndexDto parent, List<MenuIndexDto> list) {
        List<MenuIndexDto>children = new ArrayList<>();
        for (MenuIndexDto menu : list) {
            if (Objects.equals(parent.getId(), menu.getParentId())) {
                children.add(menu);
            }
            parent.setChildren(children);
        }
        return parent;
    }
}
