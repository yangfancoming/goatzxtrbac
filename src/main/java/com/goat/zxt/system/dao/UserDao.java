package com.goat.zxt.system.dao;


import com.goat.zxt.system.entity.SysUser;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface UserDao {

    // 分页返回所有用户
     List<SysUser> list(SysUser SysUser);

    // 通过id返回用户
    @Select("select u.user_id,u.dept_id,u.user_name,u.password,u.nick_name,u.phone,u.email,u.status,u.create_time,u.update_time from sys_user u where u.user_id = #{userId}")
    SysUser getUserById(Integer userId);

    // 通过手机返回用户
    SysUser checkPhoneUnique(String phone);

    // 更新用户
    int updateUser(SysUser SysUser);

    // 新增用户
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    @Insert("insert into sys_user(dept_id,user_name, password, nick_name, phone, email, status, create_time, update_time) values(#{deptId},#{userName}, #{password}, #{nickName}, #{phone}, #{email}, #{status}, now(), now())")
    int save(SysUser SysUser);

    // 通过id删除用户
    @Delete("delete from sys_user where user_id = #{userId}")
    int deleteUserById(Integer userId);

    // 根据用户名查询用户
    @Select("select * from sys_user t where t.user_name = #{userName}")
    SysUser getUser(String userName);
}
