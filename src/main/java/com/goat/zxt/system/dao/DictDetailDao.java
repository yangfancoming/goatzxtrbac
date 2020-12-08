package com.goat.zxt.system.dao;

import com.goat.zxt.system.entity.SysDictDetail;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface DictDetailDao {

    List<SysDictDetail> getDictDetail(Integer dictId);

    /**
     * 插入字典详情
     */
    @Insert("INSERT INTO my_dict_detail(dict_id,label,value, sort,create_time, update_time)values(#{dictId},#{label},#{value},#{sort}, now(), now())")
    int insertDictDetail(SysDictDetail myDictDetail);

    /**
     * 通过id获得字典详情信息
     */
    @Select("select did.id,did.dict_id,did.label,did.value,did.sort,did.create_time,did.update_time from my_dict_detail did  where did.id = #{id}")
    SysDictDetail getDictDetailById(Integer id);

    /**
     * 修改保存字典详情信息
     */
    int updateDictDetail(SysDictDetail myDictDetail);

    /**
     * 批量删除字典详情
     * @param dictDetailIds 需要删除的数据ID
     */
    int deleteDictDetailByIds(String[] dictDetailIds);

    /**
     * 根据字典id删除字典详情
     */
    @Delete("DELETE from my_dict_detail where dict_id = #{dictId}")
    int deleteDictDetailByDictId(Integer dictId);
}
