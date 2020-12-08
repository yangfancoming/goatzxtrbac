package com.goat.zxt.system.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.goat.zxt.common.utils.Result;
import com.goat.zxt.common.utils.ResultCode;
import com.goat.zxt.common.utils.UserConstants;
import com.goat.zxt.system.annotation.DataPermission;
import com.goat.zxt.system.dao.RoleDao;
import com.goat.zxt.system.dao.RoleDeptDao;
import com.goat.zxt.system.dao.RoleMenuDao;
import com.goat.zxt.system.dao.RoleUserDao;
import com.goat.zxt.system.dto.RoleDto;
import com.goat.zxt.system.entity.SysRole;
import com.goat.zxt.system.entity.SysRoleUser;
import com.goat.zxt.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;


@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private RoleMenuDao roleMenuDao;

    @Autowired
    private RoleUserDao roleUserDao;

    @Autowired
    private RoleDeptDao roleDeptDao;

    @Override
    @DataPermission(deptAlias = "d")
    public Result<SysRole> getFuzzyRolesByPage(Integer offectPosition, Integer limit, SysRole SysRole) {
        Page page = PageHelper.offsetPage(offectPosition,limit);
        List<SysRole> fuzzyRolesByPage = roleDao.getFuzzyRolesByPage(SysRole);
        return Result.ok().count(page.getTotal()).data(fuzzyRolesByPage).code(ResultCode.TABLE_SUCCESS);
    }

    @Override
    public SysRole getRoleById(Integer id) {
        return roleDao.getRoleById(id);
    }

    @Override
    @Transactional
    public Result update(RoleDto roleDto) {
        List<Integer> menuIds = roleDto.getMenuIds();
        menuIds.remove(0L);
        //1、更新角色权限之前要删除该角色之前的所有权限
        roleMenuDao.deleteRoleMenu(roleDto.getRoleId());
        //2、判断该角色是否有赋予权限值，有就添加"
        if (! CollectionUtils.isEmpty(menuIds)) {
            roleMenuDao.save(roleDto.getRoleId(), menuIds);
        }
        //3、更新角色表
        int countData = roleDao.update(roleDto);
        if(countData > 0){
            return Result.ok().message("更新成功");
        }else{
            return Result.error().message("更新失败");
        }
    }

    @Override
    @Transactional
    public Result authDataScope(RoleDto roleDto) {
        if (roleDto.getDataScope().equals(UserConstants.DATA_SCOPE_CUSTOM)){
            List<Integer> deptIds = roleDto.getDeptIds();
            deptIds.remove(0L);
            roleDeptDao.deleteRoleDept(roleDto.getRoleId());
            if (! CollectionUtils.isEmpty(deptIds)) {
                roleDeptDao.save(roleDto.getRoleId(), deptIds);
            }
            roleDao.update(roleDto);
        }else {
            roleDao.update(roleDto);
            roleDeptDao.deleteRoleDept(roleDto.getRoleId());
        }
        return Result.ok().message("更新成功");
    }

    @Override
    @Transactional
    public Result save(RoleDto roleDto) {
        // data-scope 默认为4  数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）
        roleDto.setDataScope("4");
        //1、先保存角色"
        roleDao.save(roleDto);
        List<Integer> menuIds = roleDto.getMenuIds();
        //移除0,permission id是从1开始
        //2、保存角色对应的所有权限
        if (! CollectionUtils.isEmpty(menuIds)) {
            roleMenuDao.save(roleDto.getRoleId(), menuIds);
        }
        return Result.ok().message("插入成功");
    }

    @Override
    @Transactional
    public Result<SysRole> delete(Integer id) {
        List<SysRoleUser> tbRoleUsers = roleUserDao.listAllRoleUserByRoleId(id);
        if(tbRoleUsers.size() <= 0){
            roleMenuDao.deleteRoleMenu(id);
            roleDao.delete(id);
            roleDeptDao.deleteRoleDept(id);
            return Result.ok().message("删除成功");
        }
        return Result.error().message("该角色已经关联,无法删除");
    }

    @Override
    public Result<SysRole> getAllRoles() {
        return Result.ok().data(roleDao.getAllRoles());
    }
}
