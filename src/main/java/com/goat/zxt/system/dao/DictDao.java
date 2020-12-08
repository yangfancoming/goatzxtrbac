package com.goat.zxt.system.dao;


import com.goat.zxt.system.entity.SysDict;

import java.util.List;


public interface DictDao {

    int save(SysDict dict);

    int update(SysDict dict);

    List<SysDict> findDictList(SysDict dict);

    int deleteByIds(List<String> ids);

    List<SysDict> findDictKV(SysDict dict);

    SysDict getById(Integer dictId);
}