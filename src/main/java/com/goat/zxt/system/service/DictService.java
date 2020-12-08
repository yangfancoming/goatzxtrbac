package com.goat.zxt.system.service;


import com.goat.zxt.common.exceptionhandler.MyException;
import com.goat.zxt.common.utils.Result;
import com.goat.zxt.system.entity.SysDict;

public interface DictService {

    /**
     * 分页返回字典
     * @return
     */
    Result<SysDict> getDictPage(Integer offectPosition, Integer limit, SysDict myDict);

    /**
     * 通过字典名获取字典
     * @param dictName
     * @return
     */
    SysDict getDictByName(String dictName);

    /**
     * 校验字典名称
     *
     * @param myDict 岗位信息
     * @return 结果
     */
    String checkDictNameUnique(SysDict myDict);

    /**
     * 新增字典信息
     * @param myDict 岗位信息
     * @return 结果
     */
    int insertDict(SysDict myDict);

    /**
     * 通过id获得字典信息
     * @param dictId
     * @return
     */
    SysDict getDictById(Integer dictId);

    /**
     * 修改保存自带你信息
     *
     * @param myDict 岗位信息
     * @return 结果
     */
    int updateDict(SysDict myDict);

    /**
     * 批量删除岗位信息
     * @param ids 需要删除的数据ID
     * @return 结果
     * @throws MyException 异常
     */
    int deleteDictByIds(String ids)throws MyException;
}
