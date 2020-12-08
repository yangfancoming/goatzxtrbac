package com.goat.zxt.system.controller;


import com.goat.zxt.common.utils.PageTableRequest;
import com.goat.zxt.common.utils.Result;
import com.goat.zxt.system.dto.RoleDto;
import com.goat.zxt.system.entity.SysRole;
import com.goat.zxt.system.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/api/role")
public class RoleController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RoleService roleService;

    @GetMapping("/index")
    @PreAuthorize("hasAnyAuthority('role:list')")
    public String index(){
        log.info("进入角色管理页面");
        return "system/role/role";
    }

    @GetMapping
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('role:list')")
    public Result list(PageTableRequest request, SysRole model) {
        log.info("分页返回角色列表");
        request.countOffset();
        return roleService.getFuzzyRolesByPage(request.getOffset(), request.getLimit(),model);
    }

    @GetMapping(value = "/edit")
    @PreAuthorize("hasAnyAuthority('role:edit')")
    public String toEdit(Model model, SysRole role) {
        log.info("修改角色页面");
        model.addAttribute("sysRole",roleService.getRoleById(role.getRoleId()));
        return "system/role/role-edit";
    }

    @GetMapping(value = "/edit/dataScope")
    @PreAuthorize("hasAnyAuthority('role:edit')")
    public String editRoleDataScope(Model model, SysRole role) {
        log.info("修改角色页面");
        model.addAttribute("sysRole",roleService.getRoleById(role.getRoleId()));
        return "system/role/role-dataScope";
    }

    @PutMapping
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('role:edit')")
    public Result edit(@RequestBody RoleDto roleDto) {
        log.info("修改角色");
        return roleService.update(roleDto);
    }

    @PutMapping(value = "/authDataScope")
    @ResponseBody
    public Result updateauthDataScope(@RequestBody RoleDto roleDto) {
        log.info("修改角色数据权限");
        return roleService.authDataScope(roleDto);
    }

    @GetMapping(value = "/add")
    @PreAuthorize("hasAnyAuthority('role:add')")
    public String toAdd(Model model) {
        log.info("添加角色页面");
        model.addAttribute("sysRole",new SysRole());
        return "system/role/role-add";
    }

    @PostMapping
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('role:add')")
    public Result add(@RequestBody RoleDto roleDto) {
        log.info("添加角色");
        return roleService.save(roleDto);
    }

    @DeleteMapping
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('role:del')")
    public Result<SysRole> deleteRole(RoleDto roleDto) {
        log.info("删除角色");
        return roleService.delete(roleDto.getRoleId());
    }

    @GetMapping("/all")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('user:list')")
    public Result<SysRole> getAll(){
        log.info("角色列表");
        return roleService.getAllRoles();
    }
}
