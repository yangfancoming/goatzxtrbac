package com.goat.zxt.system.dao;


import com.goat.zxt.system.dto.TreeDto;
import com.goat.zxt.system.entity.SysDept;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;



public interface DeptDao {
    /**
     * 模糊查询部门
     * @param SysDept 查询的名称
     */
    List<SysDept> list(SysDept SysDept);

    /**
     * 部门树
     */
    List<TreeDto> buildAll(TreeDto deptDto);

    /**
     * 校验部门名称是否已经存在  （不同组织的部门名称可以相同，所以必须要带上 parentId判断）
     */
    SysDept isExist(@Param("deptName") String deptName, @Param("parentId") Integer parentId);

    /**
     * 新增部门信息
     * @param dept 岗位信息
     */
    @Insert("INSERT INTO sys_dept(parent_id,ancestors,dept_name,dept_sort,dept_status, create_time, update_time) values(#{parentId},#{ancestors},#{deptName},#{deptSort},#{deptStatus}, now(), now())")
    int insertDept(SysDept dept);
    /**
     * 根据部门ID查询信息
     * @param deptId 部门ID
     * @return 部门信息
     */
    SysDept selectDeptById(Integer deptId);

    /**
     * 通过id查询部门信息
     * @param deptId
     */
    @Select("select d.dept_id,d.parent_id,d.ancestors,d.dept_name,d.dept_sort,d.dept_status,d.create_time,d.update_time from sys_dept d where d.dept_id = #{deptId}")
    SysDept getDeptById(Integer deptId);


    /**
     * 根据ID查询所有子部门
     * @param id 部门ID
     * @return 部门列表
     */
     List<SysDept> selectChildrenDeptById(Integer id);

    /**
     * 根据角色ID查询部门
     */
     List<TreeDto> listByRoleId(Integer roleId);

    /**
     * 修改子元素关系
     * @param depts 子元素
     */
    int updateDeptChildren(@Param("depts") List<SysDept> depts);

    /**
     * 修改部门信息
     * @param dept 部门信息
     */
    int updateDept(SysDept dept);

    /**
     * 修改所在部门的父级部门状态
     * @param dept 部门
     */
     void updateDeptStatus(SysDept dept);

    /**
     * 根据ID查询所有子部门（正常状态）
     * @param deptId 部门ID
     * @return 子部门数
     */
    int selectNormalChildrenDeptById(Integer deptId);

    /**
     * (删除时)判断 是否存在子部门
     */
    Boolean isExistSubDept(Integer dept);

    Boolean isExistSubUser(Integer deptId);

    int deleteDeptById(Integer deptId);
}
