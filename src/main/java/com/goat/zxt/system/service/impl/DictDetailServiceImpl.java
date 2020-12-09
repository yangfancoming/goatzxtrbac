package com.goat.zxt.system.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.goat.zxt.common.utils.Result;
import com.goat.zxt.common.utils.ResultCode;
import com.goat.zxt.system.dao.DictDetailDao;
import com.goat.zxt.system.entity.SysDict;
import com.goat.zxt.system.entity.SysDictDetail;
import com.goat.zxt.system.service.DictDetailService;
import com.goat.zxt.system.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictDetailServiceImpl implements DictDetailService {
    @Autowired
    private DictService dictService;

    @Autowired
    private DictDetailDao dictDetailDao;

    @Override
    public Result<SysDictDetail> getDictByName(Integer offectPosition, Integer limit,String dictName) {
        SysDict dictByName = dictService.getDictByName(dictName);
        Integer dictId = dictByName.getDictId();
        Page page = PageHelper.offsetPage(offectPosition,limit);
        List<SysDictDetail> fuzzyDictDetailByPage = getDictDetail(dictId);
        return Result.ok().count(page.getTotal()).data(fuzzyDictDetailByPage).code(ResultCode.TABLE_SUCCESS);
    }

    @Override
    public List<SysDictDetail> getDictDetail(Integer dictId) {
        return dictDetailDao.getDictDetail(dictId);
    }

    @Override
    public int insertDictDetail(SysDictDetail myDictDetail) {
        return dictDetailDao.insertDictDetail(myDictDetail);
    }

    @Override
    public SysDictDetail getDictDetailById(Integer id) {
        return dictDetailDao.getDictDetailById(id);
    }

    @Override
    public int updateDictDetail(SysDictDetail myDictDetail) {
        return dictDetailDao.updateDictDetail(myDictDetail);
    }

    @Override
    public int deleteDictDetailByIds(String ids) {
        return dictDetailDao.deleteDictDetailByIds(ids.split(","));
    }

    @Override
    public int deleteDictDetailByDictId(Integer dictId) {
        return dictDetailDao.deleteDictDetailByDictId(dictId);
    }
}
