package com.goat.zxt.system.service;


import com.goat.zxt.common.exceptionhandler.MyException;
import com.goat.zxt.system.dto.TreeDto;
import com.goat.zxt.system.entity.SysPost;

import java.util.List;


public interface PostService {

    /**
     * 返回岗位
     */
    List<SysPost> list(SysPost model);

    List<TreeDto> buildPost();

    /**
     * 新增岗位信息
     */
    int insertPost(SysPost model);

    /**
     * 校验岗位名称
     */
    Boolean isExist(SysPost model);

    /**
     * 通过id获得岗位信息
     */
    SysPost getPostById(String postId);

    /**
     * 批量删除岗位信息
     * @throws MyException 异常
     */
    int deleteByIds(String[] ids) throws MyException;

    /**
     * 通过岗位ID查询岗位使用数量
     */
     int isExist(String postId);

    /**
     * 查询所有岗位
     */
     List<SysPost> selectPostAll();

     List<SysPost> selectPostMutex(Integer ownPostId);

    /**
     * 根据用户ID查询岗位
     */
     List<SysPost> selectPostsByUserId(Integer userId);

     List<SysPost> selectPostsByUserId2(Integer userId);

     /**
      * 修改保存岗位信息
      */
    int update(SysPost SysPost);
}
