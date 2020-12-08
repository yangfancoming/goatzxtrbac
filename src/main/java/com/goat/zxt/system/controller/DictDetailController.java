package com.goat.zxt.system.controller;


import com.goat.zxt.common.utils.PageTableRequest;
import com.goat.zxt.common.utils.Result;
import com.goat.zxt.system.entity.SysDict;
import com.goat.zxt.system.entity.SysDictDetail;
import com.goat.zxt.system.service.DictDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/dictDetail")
public class DictDetailController {

    private Logger log = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private DictDetailService detailService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('dict:list')")
    @ResponseBody
    public Result getDictDetailMaps(PageTableRequest pageTableRequest, String dictName){
        log.info("查询字典详情");
        pageTableRequest.countOffset();
        return detailService.getDictByName(pageTableRequest.getOffset(),pageTableRequest.getLimit(),dictName);
    }

    @GetMapping("/add")
    @PreAuthorize("hasAnyAuthority('dict:add')")
    public String addDictDetail(Model model, SysDictDetail myDictDetail){
        log.info("添加字典详情页面");
        model.addAttribute("SysDictDetail",myDictDetail);
        return "/system/dict/dict-detail-add";
    }

    @PostMapping
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('dict:add')")
    public Result saveDictDetail(@RequestBody SysDictDetail myDictDetail){
        log.info("添加字典详情");
        return Result.judge(detailService.insertDictDetail(myDictDetail),"添加字典详情");
    }

    @GetMapping(value = "/edit")
    @PreAuthorize("hasAnyAuthority('dict:edit')")
    public String editDictDetail(Model model, SysDictDetail myDictDetail) {
        log.info("修改字典详情页面");
        model.addAttribute("SysDictDetail",detailService.getDictDetailById(myDictDetail.getId()));
        return "system/dict/dict-detail-edit";
    }

    @PutMapping
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('dict:edit')")
    public Result updateDictDetail(@RequestBody SysDictDetail myDictDetail){
        log.info("修改字典详情");
        return Result.judge(detailService.updateDictDetail(myDictDetail),"修改字典详情");
    }

    @DeleteMapping
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('dict:del')")
    public Result<SysDict> deleteDict(String ids) {
        log.info("删除字典详情");
        detailService.deleteDictDetailByIds(ids);
        return Result.ok().message("删除成功");
    }
}
