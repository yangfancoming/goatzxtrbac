package com.goat.zxt.system.service.impl;



import com.goat.zxt.system.dao.DictDao;
import com.goat.zxt.system.entity.SysDict;
import com.goat.zxt.system.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DictServiceImpl implements DictService {

    @Autowired
    private DictDao dictDao;

    @Override
    public List<SysDict> findAllDicts(SysDict dict) {
        return dictDao.findDictList(dict);
    }

    @Override
    public int save(SysDict dict) {
        return dictDao.save(dict);
    }

    @Override
    public void deleteDicts(String ids) {
        dictDao.deleteByIds(Arrays.asList(ids.split(",")));
    }

    @Override
    public int update(SysDict dict) {
        return  dictDao.update(dict);
    }

    @Override
    public List<SysDict> findDictKV(SysDict dict) {
        List<SysDict> dictKV = dictDao.findDictKV(dict);
        return dictKV;
    }

    @Override
    public SysDict getById(Integer dictId) {
        return dictDao.getById(dictId);
    }

}
