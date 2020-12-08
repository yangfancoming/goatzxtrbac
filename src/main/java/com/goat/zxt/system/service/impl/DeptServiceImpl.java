package com.goat.zxt.system.service.impl;


import com.goat.zxt.common.exceptionhandler.MyException;
import com.goat.zxt.common.utils.ResultCode;
import com.goat.zxt.common.utils.TreeUtil;
import com.goat.zxt.common.utils.UserConstants;
import com.goat.zxt.system.annotation.DataPermission;
import com.goat.zxt.system.dao.DeptDao;
import com.goat.zxt.system.dto.TreeDto;
import com.goat.zxt.system.entity.SysDept;
import com.goat.zxt.system.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;


@Service
public class DeptServiceImpl implements DeptService {
    
    @Autowired
    private DeptDao deptDao;

    @Override
    @DataPermission(deptAlias = "d")
    public List<SysDept> list(SysDept SysDept) {
        return deptDao.list(SysDept);
    }

    @Override
    @DataPermission(deptAlias = "d")
    public List<TreeDto> buildDeptAll(TreeDto deptDto) {
        return deptDao.buildAll(deptDto);
    }

    @Override
    @DataPermission(deptAlias = "d")
    public List<TreeDto> roleDeptTreeData(Integer roleId) {
        List<TreeDto> deptList = deptDao.listByRoleId(roleId);
        TreeDto deptDto = new TreeDto();
        List<TreeDto> buildAll = deptDao.buildAll(deptDto);
        return TreeUtil.deptTree(deptList, buildAll);
    }

    @Override
    public int insertDept(SysDept dept) {
        Integer parentId = dept.getParentId();
        if (parentId!=0){ // 非顶级部门
            SysDept info = deptDao.selectDeptById(parentId);
            if (UserConstants.DEPT_DISABLE.equals(info.getDeptStatus().toString())){
                throw new MyException(ResultCode.ERROR,"部门停用，不允许新增");
            }
            dept.setAncestors(info.getAncestors() + "," + parentId);
        }else { // 顶级部门
            dept.setAncestors(parentId.toString());
        }
        return deptDao.insertDept(dept);
    }

    @Override
    public Boolean isExist(SysDept m) {
        // 无论新增还是修改 这里的parentId都不会是null
        // 通过岗位名称查询出对象不为空  并且主键id不同，说明已经存在
        SysDept temp = deptDao.isExist(m.getDeptName(),m.getParentId());
        if (!ObjectUtils.isEmpty(temp) && !temp.getDeptId().equals(m.getParentId())){
            return true;
        }
        return false;
    }

    @Override
    public SysDept selectDeptById(Integer deptId) {
        return deptDao.selectDeptById(deptId);
    }

    @Override
    public SysDept getDeptById(Integer deptId) {
        return deptDao.getDeptById(deptId);
    }

    @Override
    @Transactional
    public int updateDept(SysDept dept) {
        SysDept parentInfo = deptDao.selectDeptById(dept.getParentId());
        SysDept oldInfo = selectDeptById(dept.getDeptId());
        if( !ObjectUtils.isEmpty(parentInfo)  && !ObjectUtils.isEmpty(oldInfo)){
            String newAncestors = parentInfo.getAncestors() + "," + parentInfo.getDeptId();
            String oldAncestors = oldInfo.getAncestors();
            dept.setAncestors(newAncestors);
            updateDeptChildren(dept.getDeptId(), newAncestors, oldAncestors);
        }
        int result =deptDao.updateDept(dept);
        if (UserConstants.DEPT_NORMAL.equals(dept.getDeptStatus().toString())){
            // 如果该部门是启用状态，则启用该部门的所有上级部门
            updateParentDeptStatus(dept);
        }
        return result;
    }

    @Override
    public int selectNormalChildrenDeptById(Integer deptId) {
        return deptDao.selectNormalChildrenDeptById(deptId);
    }

    @Override
    public Boolean isExistSubDept(Integer parentId) {
        return deptDao.isExistSubDept(parentId);
    }

    @Override
    public Boolean isExistSubUser(Integer deptId) {
        return deptDao.isExistSubUser(deptId);
    }

    @Override
    public int deleteDeptById(Integer deptId) {
        return deptDao.deleteDeptById(deptId);
    }

    @Override
    public int changeStatus(SysDept SysDept) {
        return deptDao.updateDept(SysDept);
    }

    /**
     * 修改子元素关系
     * @param id 被修改的部门ID
     * @param newAncestors 新的父ID集合
     * @param oldAncestors 旧的父ID集合
     */
    public void updateDeptChildren(Integer id, String newAncestors, String oldAncestors){
        List<SysDept> children = deptDao.selectChildrenDeptById(id);
        for (SysDept child : children){
            child.setAncestors(child.getAncestors().replace(oldAncestors, newAncestors));
        }
        if (children.size() > 0){
            deptDao.updateDeptChildren(children);
        }
    }

    /**
     * 修改该部门的父级部门状态
     * @param dept 当前部门
     */
    private void updateParentDeptStatus(SysDept dept){
        dept = deptDao.selectDeptById(dept.getDeptId());
        deptDao.updateDeptStatus(dept);
    }
}
