package com.goat.zxt.system.service;


import com.goat.zxt.common.utils.Result;
import com.goat.zxt.system.dto.RoleDto;
import com.goat.zxt.system.entity.SysRole;


public interface RoleService {
    /**
     * 返回角色
     * @param startPosition
     * @param limit
     * @param SysRole
     * @return
     */
    Result<SysRole> getFuzzyRolesByPage(Integer startPosition, Integer limit, SysRole SysRole);

    /**
     * 通过id获得角色信息
     * @param roleId
     * @return
     */
    SysRole getRoleById(Integer roleId);

    /**
     * 更新角色
     * @param roleDto
     * @return
     */
    Result update(RoleDto roleDto);

    /**
     * 数据权限
     * @param roleDto
     * @return
     */
    Result authDataScope(RoleDto roleDto);
    /**
     * 新建角色
     * @param roleDto
     * @return
     */
    Result save(RoleDto roleDto);

    /**
     * 删除角色
     * @param roleId
     * @return
     */
    Result<SysRole> delete(Integer roleId);

    /**
     * 获取全部角色
     * @return
     */
    Result<SysRole> getAllRoles();
}
