package com.goat.zxt.system.controller;


import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.goat.zxt.common.utils.QueryRequest;
import com.goat.zxt.common.utils.Result;
import com.goat.zxt.common.utils.ResultCode;
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
public class DictController extends BaseController {

    private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private DictService dictService;

    @GetMapping("index")
    public String index(){
        log.info("进入字典页面");
        return "system/dict/dict";
    }

    @GetMapping
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('dict:list')")
    public Result<SysDict> list(QueryRequest request, SysDict dict) {
        PageHelper.startPage(request.getPage(), request.getLimit());
        List<SysDict> list = dictService.findAllDicts(dict);
        PageInfo<SysDict> pageInfo = new PageInfo<>(list);
        return Result.ok().code(ResultCode.TABLE_SUCCESS).count(pageInfo.getTotal()).data(pageInfo.getList());
    }

    @GetMapping("add")
    @PreAuthorize("hasAnyAuthority('dict:add')")
    public String toAdd(Model model){
        log.info("进入字典新增页面");
        model.addAttribute("sysDict",new SysDict());
        return "system/dict/dict-add";
    }

    @PostMapping
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('dict:add')")
    public Result add(@RequestBody SysDict m){
        log.info("新增字典 ---> {}", JSON.toJSONString(m));
//        if (dictService.isExist(m)) { // doit
//            return Result.error().message("新增字典'" + m.getPostName() + "'失败，字典名称已存在");
//        }
        return Result.judge(dictService.save(m),"新增字典");
    }


    @GetMapping(value = "/edit")
    @PreAuthorize("hasAnyAuthority('dict:edit')")
    public String toEdit(Model modelview, SysDict m) {
        log.info("进入修改字典页面 ---> {}", JSON.toJSONString(m.getDictId()));
        modelview.addAttribute("sysDict", dictService.getById(m.getDictId()));
        return "system/dict/dict-edit";
    }

    @PutMapping
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('dict:edit')")
    public Result edit(@RequestBody SysDict m){
        log.info("修改字典 ---> {}", JSON.toJSONString(m));
//        if (dictService.isExist(m)) {
//            return Result.error().message("修改字典'" + m.getPostName() + "'失败，字典名称已存在");
//        }
        return Result.judge(dictService.update(m),"修改字典");
    }
    

    @DeleteMapping
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('dict:del')")
    public Result<SysDict> delete(String ids) {
        log.info("删除字典 ---> {}", JSON.toJSONString(ids));
        dictService.deleteDicts(ids);
        return Result.ok().message("删除成功");
    }

}
