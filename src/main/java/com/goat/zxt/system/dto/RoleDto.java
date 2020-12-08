package com.goat.zxt.system.dto;


import com.goat.zxt.system.entity.SysRole;

import java.util.List;

public class RoleDto extends SysRole {

    private static final long serialVersionUID = -5784234789156935003L;

    private List<Integer> menuIds;

    private  List<Integer> deptIds;

    public List<Integer> getDeptIds() {
        return deptIds;
    }

    public void setDeptIds(List<Integer> deptIds) {
        this.deptIds = deptIds;
    }

    public List<Integer> getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(List<Integer> menuIds) {
        this.menuIds = menuIds;
    }

}
