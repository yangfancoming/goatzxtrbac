package com.goat.zxt.system.dao;

import com.goat.zxt.system.entity.SysUserPost;

import java.util.List;



public interface UserPostDao {

    /**
     * 通过岗位ID查询岗位 是否已经分配到用户
     */
    int isExistUser(String postId);

    /**
     * 批量新增用户岗位信息
     */
    int batchUserJob(List<SysUserPost> userJobList);

    /**
     * 通过用户ID删除用户和岗位关联
     */
    int deleteUserJobByUserId(Integer id);

    /**
     * 通过用户ID 获取其所管辖的岗位
     */
    List<String> getPostIdsByUserId(Integer userId);
}
