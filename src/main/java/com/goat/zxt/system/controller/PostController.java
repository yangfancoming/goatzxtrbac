package com.goat.zxt.system.controller;


import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.goat.zxt.common.utils.QueryRequest;
import com.goat.zxt.common.utils.Result;
import com.goat.zxt.common.utils.ResultCode;
import com.goat.zxt.system.entity.SysPost;
import com.goat.zxt.system.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 系统：岗位管理
*/
@Controller
@RequestMapping("/api/post")
public class PostController extends BaseController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private PostService postService;
    
    @GetMapping("index")
    public String index(){
        log.info("进入岗位页面");
        return "system/post/post";
    }

    @GetMapping
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('post:list')")
    public Result<SysPost> list(QueryRequest request, SysPost model){
        log.info("查询岗位列表");
        PageHelper.startPage(request.getPage(), request.getLimit());
        List<SysPost> results = postService.list(model);
        PageInfo<SysPost> pageInfo = new PageInfo<>(results);
        return Result.ok().code(ResultCode.TABLE_SUCCESS).count(pageInfo.getTotal()).data(pageInfo.getList());
    }

    @GetMapping("/add")
    @PreAuthorize("hasAnyAuthority('post:add')")
    public String toAdd(Model model){
        log.info("进入岗位新增页面");
        SysPost sysPost = new SysPost();
        model.addAttribute("sysPost",sysPost);
        return "system/post/post-add";
    }

    @PostMapping
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('post:add')")
    public Result add(@RequestBody SysPost m){
        log.info("新增岗位 ---> {}", JSON.toJSONString(m));
        if (postService.isExist(m)) {
            return Result.error().message("新增岗位'" + m.getPostName() + "'失败，岗位名称已存在");
        }
        return Result.judge(postService.insertPost(m),"新增岗位");
    }

    @GetMapping(value = "/edit")
    @PreAuthorize("hasAnyAuthority('post:edit')")
    public String toEdit(Model modelview, SysPost model) {
        log.info("进入修改岗位页面 ---> {}", JSON.toJSONString(model.getPostId()));
        modelview.addAttribute("sysPost", postService.getPostById(model.getPostId().toString()));
        return "system/post/post-edit";
    }

    @PutMapping
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('post:edit')")
    public Result edit(@RequestBody SysPost m){
        log.info("修改岗位 ---> {}", JSON.toJSONString(m));
        if (postService.isExist(m)) {
            return Result.error().message("修改岗位'" + m.getPostName() + "'失败，岗位名称已存在");
        }
        return Result.judge(postService.update(m),"修改岗位");
    }

    @PutMapping("/changeStatus")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('post:edit')")
    public Result changeStatus(@RequestBody SysPost m){
        log.info("修改岗位状态 ---> {}", JSON.toJSONString(m));
        return Result.judge(postService.update(m),"修改");
    }

    @DeleteMapping
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('post:del')")
    public Result<SysPost> del(String ids) {
        log.info("删除岗位 ---> {}", JSON.toJSONString(ids));
        postService.deleteByIds(ids.split(","));
        return Result.ok().message("删除成功");
    }
}
