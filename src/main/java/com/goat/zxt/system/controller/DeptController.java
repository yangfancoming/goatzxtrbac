package com.goat.zxt.system.controller;


import com.alibaba.fastjson.JSON;
import com.goat.zxt.common.utils.Result;
import com.goat.zxt.common.utils.ResultCode;
import com.goat.zxt.common.utils.UserConstants;
import com.goat.zxt.system.dto.TreeDto;
import com.goat.zxt.system.entity.SysDept;
import com.goat.zxt.system.service.DeptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/api/dept")
//@Api(tags = "系统：部门管理")
public class DeptController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private DeptService deptService;

    @GetMapping("index")
    public String index(){
        log.info("进入部门页面");
        return "system/dept/dept";
    }

    @GetMapping
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('dept:list')")
    public Result getDeptAll(SysDept m){
        log.info("查询部门列表 ---> {}", JSON.toJSONString(m));
        return Result.ok().data(deptService.list(m)).code(ResultCode.TABLE_SUCCESS);
    }

    @GetMapping("build")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('dept:add','dept:edit')")
    public Result buildDeptAll(TreeDto deptDto){
        log.info("绘制部门树 ---> {}", JSON.toJSONString(deptDto));
        List<TreeDto> deptAll = deptService.buildDeptAll(deptDto);
        return Result.ok().data(deptAll);
    }

    @GetMapping("/add")
    @PreAuthorize("hasAnyAuthority('dept:add')")
    public String toAdd(Model model){
        log.info("去新增部门页面");
        model.addAttribute("sysDept",new SysDept());
        return "/system/dept/dept-add";
    }

    @PostMapping
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('dept:add')")
    public Result<SysDept> savePermission(@RequestBody SysDept m) {
        log.info("新增部门 ---> {}", JSON.toJSONString(m));
        if (deptService.isExist(m)) {
            return Result.error().message("新增岗位'" + m.getDeptName() + "'失败，岗位名称已存在");
        }
        int i = deptService.insertDept(m);
        return Result.judge(i,"添加");
    }

    @GetMapping(value = "/edit")
    @PreAuthorize("hasAnyAuthority('dept:edit')")
    public String toEdit(Model model, SysDept dept) {
        log.info("去修改部门页面");
        model.addAttribute("sysDept",deptService.getDeptById(dept.getDeptId()));
        return "system/dept/dept-edit";
    }

    @PutMapping
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('dept:edit')")
    public Result updateMenu(@RequestBody SysDept m) {
        log.info("修改部门 ---> {}", JSON.toJSONString(m));
        if (deptService.isExist(m)) {
            return Result.error().message("更新岗位'" + m.getDeptName() + "'失败，岗位名称已存在");
        } else if (m.getParentId().equals(m.getDeptId())){
            return Result.error().message("修改部门'" + m.getDeptName() + "'失败，上级部门不能是自己");
        }else if (m.getDeptStatus().toString().equals(UserConstants.DEPT_DISABLE) && deptService.selectNormalChildrenDeptById(m.getDeptId()) > 0) {
            return Result.error().message("该部门包含未停用的子部门！");
        }
        int i = deptService.updateDept(m);
        return Result.judge(i,"修改");
    }

    @PutMapping("/changeStatus")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('dept:edit')")
    public Result changeStatus(@RequestBody SysDept sysDept){
        log.info("修改部门状态 ---> {}", JSON.toJSONString(sysDept));
        return Result.judge(deptService.changeStatus(sysDept),"修改");
    }


    @DeleteMapping
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('dept:del')")
    public Result<SysDept> deleteRole(Integer deptId) {
        log.info("删除部门 ---> {}", JSON.toJSONString(deptId));
        if (deptService.isExistSubDept(deptId)){
            return Result.error().message("存在下级部门,请先删除子级部门");
        }else if (deptService.isExistSubUser(deptId)){
            return Result.error().message("部门存在用户,请先删除部门用户");
        }
        int i = deptService.deleteDeptById(deptId);
        return Result.judge(i,"删除");
    }

    /**
     * 加载角色部门（数据权限）列表树
     */
    @GetMapping("/ebuild/{roleId}")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('role:add','role:edit')")
    public Result deptTreeData(@PathVariable Integer roleId){
        log.info("通过角色id绘制部门树 ---> roleId = {}", JSON.toJSONString(roleId));
        List<TreeDto> deptDtos = deptService.roleDeptTreeData(roleId);
        return Result.ok().data(deptDtos);
    }
}
