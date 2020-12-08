package com.goat.zxt.system.dao;

import com.goat.zxt.system.entity.SysRoleUser;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;



public interface RoleUserDao {
    /**
     * 通过角色id返回所有用户
     * @param id
     * @return
     */
    @Select("select * from sys_role_user ru where ru.role_id = #{roleId}")
    List<SysRoleUser> listAllRoleUserByRoleId(Integer id);



    /**
     * 通过用户id查询权限id
     * @param userId
     * @return
     */
    @Select("select * from sys_role_user ru where ru.user_id = #{userId}")
    List<SysRoleUser> getSysRoleUserByUserId(Integer userId);

    /**
     * 通过用户id返回角色
     * @param intValue
     * @return
     */
    @Select("select * from sys_role_user ru where ru.user_id = #{userId}")
    SysRoleUser getRoleUserByUserId(int intValue);

    /**
     * 更新
     * @param SysRoleUser
     * @return
     */
    int updateSysRoleUser(SysRoleUser SysRoleUser);

    /**
     * 新建
     * @param SysRoleUser
     * @return
     */
    @Insert("insert into sys_role_user(user_id, role_id) values(#{userId}, #{roleId})")
    int save(SysRoleUser SysRoleUser);

    /**
     * 删除
     * @param id
     * @return
     */
    @Delete("delete from sys_role_user where user_id = #{userId}")
    int deleteRoleUserByUserId(Integer id);
}
