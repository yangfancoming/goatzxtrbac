package com.goat.zxt.system.entity;


import java.io.Serializable;


public class SysRoleMenu implements Serializable {

    private static final long serialVersionUID = 8925514045582235875L;

    private Integer roleId;

    private Integer permissionId;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }
}
