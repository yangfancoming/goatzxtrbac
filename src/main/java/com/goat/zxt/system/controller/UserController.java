package com.goat.zxt.system.controller;


import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.goat.zxt.common.utils.QueryRequest;
import com.goat.zxt.common.utils.Result;
import com.goat.zxt.common.utils.ResultCode;
import com.goat.zxt.system.entity.SysUser;
import com.goat.zxt.system.service.PostService;
import com.goat.zxt.system.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 系统：用户管理
*/
@Controller
@RequestMapping("/api/user")
public class UserController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @GetMapping("/index")
    @PreAuthorize("hasAnyAuthority('user:list')")
    public String index(){
        return "system/user/user";
    }

    @GetMapping
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('user:list')")
    public Result<SysUser> userList(QueryRequest request, SysUser model){
        log.info("查询用户列表");
        PageHelper.startPage(request.getPage(), request.getLimit());
        List<SysUser> results = userService.list(model);
        PageInfo<SysUser> pageInfo = new PageInfo<>(results);
        return Result.ok().code(ResultCode.TABLE_SUCCESS).count(pageInfo.getTotal()).data(pageInfo.getList());
    }

    @GetMapping("/add")
    @PreAuthorize("hasAnyAuthority('user:add')")
    public String toAdd(Model model){
        log.info("去新增用户页面");
        model.addAttribute("sysUser",new SysUser());
        model.addAttribute("posts", postService.selectPostMutex(null));
        return "/system/user/user-add";
    }

    @PostMapping
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('user:add')")
    public Result<SysUser> saveUser(@RequestBody SysUser m){
        log.info("新增用户 ---> {}", JSON.toJSONString(m));
        if (userService.checkPhoneUnique(m)){
            return Result.error().message("手机号已存在");
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        m.setPassword(encoder.encode("123456"));
//        m.setPassword(Md5.crypt("123456"));
        return userService.save(m,m.getRoleId());
    }

    @GetMapping("edit")
    @PreAuthorize("hasAnyAuthority('user:edit')")
    public String editUser(Model model, SysUser tbUser){
        log.info("去修改用户页面");
        model.addAttribute("sysUser",userService.getUserById(tbUser.getUserId()));
        model.addAttribute("posts", postService.selectPostsByUserId2(tbUser.getUserId()));
        return "/system/user/user-edit";
    }

    @PutMapping
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('user:edit')")
    public Result updateUser(@RequestBody SysUser m){
        log.info("修改用户 ---> {}", JSON.toJSONString(m));
        userService.checkUserAllowed(m);
        if (userService.checkPhoneUnique(m)){
            return Result.error().message("手机号已存在");
        }
        return userService.updateUser(m,m.getRoleId());
    }

    /**
     * 用户状态修改
     */
    @PutMapping("/changeStatus")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('user:edit')")
    public Result changeStatus(@RequestBody SysUser m) {
        log.info("修改用户状态 ---> {}", JSON.toJSONString(m));
        userService.checkUserAllowed(m);
        return Result.judge(userService.changeStatus(m),"修改成功");
    }

    @DeleteMapping
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('user:del')")
    public Result deleteUser(Integer userId){
        log.info("删除用户 ---> {}", JSON.toJSONString(userId));
        int count = userService.deleteUser(userId);
        return Result.judge(count,"删除用户");
    }
}
