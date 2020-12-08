package com.goat.zxt.system.service;


import com.goat.zxt.system.entity.SysRoleUser;

import java.util.List;


public interface RoleUserService {
    /**
     * 返回用户拥有的角色
     * @param userId
     * @return
     */
    List<SysRoleUser> getSysRoleUserByUserId(Integer userId);
}
