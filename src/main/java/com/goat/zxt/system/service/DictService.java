package com.goat.zxt.system.service;


import com.goat.zxt.system.entity.SysDict;

import java.util.List;

public interface DictService {

	List<SysDict> findAllDicts(SysDict dict);

    SysDict getById(Integer dictId);

    int save(SysDict dict);

	void deleteDicts(String dictIds);

	int update(SysDict dicdt);

    List<SysDict> findDictKV(SysDict dict);
}
