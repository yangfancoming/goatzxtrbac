package com.goat.zxt.system.dao;


import com.goat.zxt.system.dto.RoleDto;
import com.goat.zxt.system.entity.SysRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface RoleDao {

    /**
     * 分页模糊查询角色
     */
    List<SysRole> getFuzzyRolesByPage(SysRole role);

    /**
     * 通过id查询角色
     */
    @Select("select r.role_id,r.role_name,r.data_scope,r.description,r.create_time,r.update_time from sys_role r where r.role_id = #{roleId}")
    SysRole getRoleById(Integer roleId);

    /**
     * 更新角色
     */
    int update(RoleDto roleDto);

    /**
     * 新建角色
     */
    int save(RoleDto roleDto);

    /**
     * 通过id删除角色
     */
    @Delete("delete from sys_role where role_id = #{roleId}")
    int delete(Integer roleId);

    /**
     * 返回所有角色
     */
    @Select("select r.role_id,r.role_name,r.description from sys_role r")
    List<SysRole> getAllRoles();
}
