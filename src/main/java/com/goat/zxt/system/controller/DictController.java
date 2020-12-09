package com.goat.zxt.system.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.goat.zxt.common.utils.QueryRequest;
import com.goat.zxt.common.utils.Result;
import com.goat.zxt.common.utils.ResultCode;
import com.goat.zxt.common.utils.UserConstants;
import com.goat.zxt.system.entity.SysDict;
import com.goat.zxt.system.service.DictService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/dict")
public class DictController {

    private Logger log = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private DictService dictService;


    @GetMapping("/index")
    @PreAuthorize("hasAnyAuthority('dict:list')")
    public String index(){
        log.info("进入字典页面");
        return "system/dict/dict";
    }


    @GetMapping
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('dict:list')")
    public Result getDictAll(QueryRequest request, SysDict model){
        log.info("查询字典列表");
        PageHelper.startPage(request.getPage(), request.getLimit());
        List<SysDict> results = dictService.list(model);
        PageInfo<SysDict> pageInfo = new PageInfo<>(results);
        return Result.ok().code(ResultCode.TABLE_SUCCESS).count(pageInfo.getTotal()).data(pageInfo.getList());
    }


    @GetMapping("/add")
    @PreAuthorize("hasAnyAuthority('dict:add')")
    public String addDict(Model model){
        log.info("添加字典页面");
        model.addAttribute("SysDict",new SysDict());
        return "/system/dict/dict-add";
    }

    @PostMapping
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('dict:add')")
    public Result saveDict(@RequestBody SysDict myDict){
        log.info("添加字典");
        if (UserConstants.NOT_UNIQUE.equals(dictService.checkDictNameUnique(myDict))) {
            return Result.error().message("新增字典'" + myDict.getDictName() + "'失败，字典名称已存在");
        }
        return Result.judge(dictService.insertDict(myDict),"添加字典");
    }

    @GetMapping(value = "/edit")
    @PreAuthorize("hasAnyAuthority('dict:edit')")
    public String editDict(Model model, SysDict myDict) {
        log.info("修改字典页面");
        model.addAttribute("SysDict",dictService.getDictById(myDict.getDictId()));
        return "system/dict/dict-edit";
    }

    @PutMapping
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('dict:edit')")
    public Result updateDict(@RequestBody SysDict myDict){
        log.info("修改字典");
        if (UserConstants.NOT_UNIQUE.equals(dictService.checkDictNameUnique(myDict))) {
            return Result.error().message("修改字典'" + myDict.getDictName() + "'失败，字典名称已存在");
        }
        return Result.judge(dictService.updateDict(myDict),"修改字典");
    }

    @DeleteMapping
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('dict:del')")
    public Result<SysDict> deleteDict(String ids) {
        log.info("删除字典");
        dictService.deleteDictByIds(ids);
        return Result.ok().message("删除成功");
    }
}
