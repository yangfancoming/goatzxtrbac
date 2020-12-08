package com.goat.zxt.system.controller;


import com.goat.zxt.common.utils.Result;
import com.goat.zxt.system.entity.SysRoleUser;
import com.goat.zxt.system.service.RoleUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api/roleuser")
public class RoleUserController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RoleUserService roleUserService;

    @PostMapping()
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('user:list')")
    public Result getRoleUserByUserId(Integer userId) {
        log.info("通过用户id返回用户角色");
        List<SysRoleUser> tbRoleUser =roleUserService.getSysRoleUserByUserId(userId);
        if(tbRoleUser != null){
            return Result.ok().data(tbRoleUser);
        }else{
            return Result.error();
        }
    }
}
