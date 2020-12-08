package com.goat.zxt.system.dao;


import com.goat.zxt.system.entity.SysDict;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface DictDao {

    /**
     * 模糊查询字典
     */
    List<SysDict> list(SysDict myDict);

    /**
     * 通过字典名称获取字典信息
     */
    SysDict getDictByName(String dictName);

    /**
     * 插入字典
     */
    @Insert("INSERT INTO sys_dict(dict_id,dict_name,description, sort,create_time, update_time)values(#{dictId},#{dictName},#{description},#{sort}, now(), now())")
    int insertDict(SysDict myDict);

    /**
     * 通过id获得字典信息
     */
    @Select("select di.dict_id,di.dict_name,di.description,di.sort,di.create_time,di.update_time from sys_dict di  where di.dict_id = #{dictId}")
    SysDict getDictById(Integer dictId);

    /**
     * 修改保存字典信息
     */
    int updateDict(SysDict myDict);

    /**
     * 批量删除岗位信息
     */
    int deleteDictByIds(String[] dictIds);
}
