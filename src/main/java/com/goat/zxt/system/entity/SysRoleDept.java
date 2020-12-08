package com.goat.zxt.system.entity;


import java.io.Serializable;


public class SysRoleDept implements Serializable {

    private static final long serialVersionUID = 8925514042332235875L;

    private Integer roleId;

    private Integer deptId;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }
}
