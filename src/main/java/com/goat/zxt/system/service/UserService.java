package com.goat.zxt.system.service;


import com.goat.zxt.common.utils.Result;
import com.goat.zxt.system.entity.SysUser;

import java.util.List;


public interface UserService {

    // 返回用户列表
    List<SysUser> list(SysUser SysUser);

    // 根据id返回用户信息
    SysUser getUserById(Integer id);

    // 校验用户是否允许操作
    public void checkUserAllowed(SysUser user);

    // 通过手机查询用户
    Boolean checkPhoneUnique(SysUser user);

    // 更新用户
    Result<SysUser> updateUser(SysUser SysUser, Integer roleId);

    // 用户信息
    int changeStatus(SysUser user);

    // 新建用户
    Result<SysUser> save(SysUser SysUser, Integer roleId);

    int deleteUser(Integer userId);

    // 根据用户名查询用户
    SysUser getUserByName(String userName);

    List<String> getPostIdsByUserId(Integer userId);
}
