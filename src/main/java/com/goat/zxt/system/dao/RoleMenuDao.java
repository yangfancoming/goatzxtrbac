package com.goat.zxt.system.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;



public interface RoleMenuDao {
    /**
     * 通过id删除rolemenu
     * @param roleId
     * @return
     */
    @Delete("delete from sys_role_menu where role_id = #{roleId}")
    int deleteRoleMenu(Integer roleId);

    /**
     * 新建角色与menu的联系
     * @param roleId
     * @param menuIds
     */
    void save(@Param("roleId") Integer roleId, @Param("menuIds") List<Integer> menuIds);

    /**
     * 通过role_id计算权限数量
     * @param id
     * @return
     */
    @Select("select count(*) from sys_role_menu t where t.menu_id = #{menuId}")
    Integer countRoleMenuByRoleId(Integer id);
}
