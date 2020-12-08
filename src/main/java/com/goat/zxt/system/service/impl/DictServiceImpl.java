package com.goat.zxt.system.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.goat.zxt.common.exceptionhandler.MyException;
import com.goat.zxt.common.utils.Result;
import com.goat.zxt.common.utils.ResultCode;
import com.goat.zxt.common.utils.UserConstants;
import com.goat.zxt.system.dao.DictDao;
import com.goat.zxt.system.dao.DictDetailDao;
import com.goat.zxt.system.entity.SysDict;
import com.goat.zxt.system.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class DictServiceImpl implements DictService {
    
    @Autowired
    private DictDao dictDao;

    @Autowired
    private DictDetailDao dictDetailDao;
    @Override
    public Result<SysDict> getDictPage(Integer offectPosition, Integer limit, SysDict myDict) {
        Page page = PageHelper.offsetPage(offectPosition,limit);
        List<SysDict> fuzzyDictByPage = dictDao.getFuzzyDictByPage(myDict);
        return Result.ok().count(page.getTotal()).data(fuzzyDictByPage).code(ResultCode.TABLE_SUCCESS);
    }

    @Override
    public SysDict getDictByName(String dictName) {
        return dictDao.getDictByName(dictName);
    }

    @Override
    public String checkDictNameUnique(SysDict myDict) {
        SysDict info = dictDao.getDictByName(myDict.getDictName());
        if ( !ObjectUtils.isEmpty(info) && !info.getDictId().equals (myDict.getDictId())){
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public int insertDict(SysDict myDict) {
        return dictDao.insertDict(myDict);
    }

    @Override
    public SysDict getDictById(Integer dictId) {
        return dictDao.getDictById(dictId);
    }

    @Override
    public int updateDict(SysDict myDict) {
        return dictDao.updateDict(myDict);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public int deleteDictByIds(String ids)  throws MyException {
        dictDetailDao.deleteDictDetailByIds(ids.split(","));
        return dictDao.deleteDictByIds(ids.split(","));
    }
}
