package com.goat.zxt.system.service;


import com.goat.zxt.system.dto.TreeDto;
import com.goat.zxt.system.entity.SysDept;

import java.util.List;


public interface DeptService {

    /**
     * 返回部门
     */
    List<SysDept> list(SysDept SysDept);

    /**
     * 部门树
     */
    List<TreeDto> buildDeptAll(TreeDto deptDto);

    /**
     * 根据角色ID查询菜单
     * @param roleId 角色对象
     * @return 菜单列表
     */
    List<TreeDto> roleDeptTreeData(Integer roleId);

    /**
     * 新增部门信息
     * @param SysDept 岗位信息
     * @return 结果
     */
    int insertDept(SysDept SysDept);

    /**
     * 校验部门名称是否唯一
     * @param dept 部门信息
     * @return 结果
     */
    Boolean isExist(SysDept dept);

    /**
     * 根据部门ID查询信息
     * @param deptId 部门ID
     * @return 部门信息
     */
    SysDept selectDeptById(Integer deptId);

    /**
     * 通过id查询部门信息
     * @param deptId
     * @return
     */
    SysDept getDeptById(Integer deptId);

    /**
     * 修改保存部门信息
     * @param dept 部门信息
     * @return 结果
     */
    int updateDept(SysDept dept);

    /**
     * 根据ID查询所有子部门（正常状态）
     * @param deptId 部门ID
     * @return 子部门数
     */
    int selectNormalChildrenDeptById(Integer deptId);

    /**
     * (删除时)判断 是否存在子部门
     * @param parentId 父部门ID
     */
    Boolean isExistSubDept(Integer parentId);

    /**
     * 查询部门是否存在用户
     * @param deptId 部门ID
     * @return 结果 true 存在 false 不存在
     */
    Boolean isExistSubUser(Integer deptId);

    /**
     * (删除时)判断 部门是否存在用户
     * @param deptId 部门ID
     * @return 结果 true 存在 false 不存在
     */
     int deleteDeptById(Integer deptId);

    /**
     * 修改部门状态
     */
     int changeStatus(SysDept SysDept);
}
