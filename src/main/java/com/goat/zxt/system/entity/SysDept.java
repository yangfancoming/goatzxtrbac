package com.goat.zxt.system.entity;



public class SysDept extends BaseEntity {

    private static final long serialVersionUID = 8925514045582235633L;

    private Integer deptId;

    private Integer parentId;

    private String ancestors;

    private String deptName;

    private Integer deptSort;

    private Integer deptStatus;

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getAncestors() {
        return ancestors;
    }

    public void setAncestors(String ancestors) {
        this.ancestors = ancestors;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }


    public Integer getDeptSort() {
        return deptSort;
    }

    public void setDeptSort(Integer deptSort) {
        this.deptSort = deptSort;
    }

    public Integer getDeptStatus() {
        return deptStatus;
    }

    public void setDeptStatus(Integer deptStatus) {
        this.deptStatus = deptStatus;
    }
}
