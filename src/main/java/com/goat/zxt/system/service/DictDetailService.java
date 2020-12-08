package com.goat.zxt.system.service;



import com.goat.zxt.common.utils.Result;
import com.goat.zxt.system.entity.SysDictDetail;

import java.util.List;

public interface DictDetailService {

    /**
     * 通过字典名获取字典详情
     * @param dictName
     * @return
     */
    Result<SysDictDetail> getDictByName(Integer offectPosition, Integer limit, String dictName);


    /**
     * 根据字典id获取字典详情
     * @param dictId
     * @return
     */
    List<SysDictDetail> getDictDetail(Integer dictId);

    /**
     * 新增字典详情
     * @param myDictDetail
     * @return
     */
    int insertDictDetail(SysDictDetail myDictDetail);

    /**
     * 通过id获得字典信息
     * @param id
     * @return
     */
    SysDictDetail getDictDetailById(Integer id);

    /**
     * 修改保存字典详情信息
     *
     * @param myDictDetail 岗位信息
     * @return 结果
     */
    int updateDictDetail(SysDictDetail myDictDetail);

    /**
     * 批量删除字典详情信息
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteDictDetailByIds(String ids);

    /**
     *
     * 根据字典id删除字典详情
     * @param dictId
     * @return
     */
    int deleteDictDetailByDictId(Integer dictId);
}
