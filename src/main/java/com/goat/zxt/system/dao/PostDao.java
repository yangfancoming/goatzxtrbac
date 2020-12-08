package com.goat.zxt.system.dao;

import com.goat.zxt.system.dto.TreeDto;
import com.goat.zxt.system.entity.SysPost;
import org.apache.ibatis.annotations.Param;

import java.util.List;



public interface PostDao {

    /**
     * 模糊查询岗位
     */
    List<SysPost> list(SysPost model);

    List<TreeDto> buildPost(@Param("postIds") List<String> postIds);

    /**
     * 新增岗位信息
     */
    int insert(SysPost model);

    /**
     * 校验岗位名称 是否已经存在
     */
    SysPost isExist(String postName);

    /**
     * 通过id查询岗位信息
     */
    SysPost getPostById(String postId);

    /**
     * 批量删除岗位信息
     */
    int deleteByIds(String[] ids);

    /**
     * 根据用户ID查询岗位
     */
    List<SysPost> selectPostsByUserId(Integer userId);

    /**
     * 查询所有岗位
     */
    List<SysPost> selectPostAll();

    /**
     * 查询还未分配到教师的岗位
     */
    List<SysPost> selectPostMutex(Integer userId);

    /**
     * 修改岗位信息
     */
    int update(SysPost SysPost);
}
