package com.goat.zxt.system.service.impl;

import com.goat.zxt.system.dao.RoleUserDao;
import com.goat.zxt.system.entity.SysRoleUser;
import com.goat.zxt.system.service.RoleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RoleUserServiceImpl implements RoleUserService {

    @Autowired
    private RoleUserDao roleUserDao;

    @Override
    public List<SysRoleUser> getSysRoleUserByUserId(Integer userId) {
       return roleUserDao.getSysRoleUserByUserId(userId);

    }
}
