package com.goat.zxt.system.dao;

import com.goat.zxt.system.dto.MenuDto;
import com.goat.zxt.system.dto.MenuIndexDto;
import com.goat.zxt.system.entity.SysMenu;
import org.apache.ibatis.annotations.*;

import java.util.List;


@SuppressWarnings("ALL")
public interface MenuDao {
    /**
     * 模糊查询菜单
     * @param queryName 查询的表题
     * @param queryType 查询类型
     */
    @SuppressWarnings("AlibabaAbstractMethodOrInterfaceMethodMustUseJavadoc")
    List<SysMenu> getFuzzyMenu(String queryName, Integer queryType);

    /**
     * 通过id查询菜单
     */
    @Select("select m.menu_id,m.parent_id,m.menu_name,m.icon,m.url,m.permission,m.sort,m.type,m.create_time,m.update_time from sys_menu m where m.menu_id = #{menuId}")
    SysMenu getMenuById(Integer menuId);

    /**
     * 菜单树
     */
    @Select("select m.menu_id,m.parent_id,m.menu_name from sys_menu m")
    @Result(property = "title",column = "menu_name")
    @Result(property = "id",column = "menu_id")
    List<MenuDto> buildAll();

    /**
     * 更新菜单
     */
    int update(SysMenu menu);

    /**
     * 新建菜单
     */
    @Options(useGeneratedKeys = true, keyProperty = "menuId")
    @Insert("insert into sys_menu(parent_id, menu_name, icon, url, permission, sort, type, create_time, update_time)values(#{parentId}, #{menuName}, #{icon}, #{url}, #{permission}, #{sort}, #{type}, now(), now())")
    int save(SysMenu menu);

    /**
     * 通过id删除菜单
     */
    @Delete("delete from sys_menu where menu_id = #{menuId}")
    int deleteById(Integer menuId);

    /**
     * 通过父节点删除子菜单
     */
    @Delete("delete from sys_menu where parent_id = #{parentId}")
    int deleteByParentId(Integer parentId);

    /**
     * 通过父节点返回字节点
     */
    @Select("select m.menu_id from sys_menu m where parent_id = #{parentId}")
    List<Integer> selectByParentId(Integer parentId);

    /**
     * 通过角色id返回菜单
     */
    @Select("select m.menu_id,m.parent_id,m.menu_name from sys_menu m inner join sys_role_menu rm on m.menu_id = rm.menu_id where rm.role_id = #{roleId}")
    @Result(property = "title",column = "menu_name")
    @Result(property = "id",column = "menu_id")
    List<MenuDto> listByRoleId(Integer roleId);

    /**
     * 通过用户id返回菜单
     */
    List<MenuIndexDto> listByUserId(@Param("userId") Integer userId);
}
