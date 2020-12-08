package com.goat.zxt.system.service.impl;


import com.goat.zxt.common.exceptionhandler.MyException;
import com.goat.zxt.common.utils.ResultCode;
import com.goat.zxt.common.utils.SecurityUtils;
import com.goat.zxt.security.dto.JwtUserDto;
import com.goat.zxt.system.dao.PostDao;
import com.goat.zxt.system.dao.UserPostDao;
import com.goat.zxt.system.dto.TreeDto;
import com.goat.zxt.system.entity.SysPost;
import com.goat.zxt.system.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;


@Service
public class PostServiceImpl implements PostService {
    
    @Autowired
    private PostDao postDao;

    @Autowired
    private UserPostDao userPostDao;

    @Override
    public List<SysPost> list(SysPost model) {
        List<SysPost> results = postDao.list(model);
        return results;
    }

    @Override
    public List<TreeDto> buildPost() {
        JwtUserDto currentUser = SecurityUtils.getCurrentDtoUser();
        List<String> postIds = currentUser.getPostIds();
        if (postIds.size() == 0) {
            postIds = null;
        }
        return postDao.buildPost(postIds);
    }

    @Override
    public int insertPost(SysPost model) {
        return postDao.insert(model);
    }

    /**
     * 校验岗位名称是否 已经存在
     */
    @Override
    public Boolean isExist(SysPost m) {
        // 判断是新增还是修改(新增postId为null ，修改postId有值)
        Integer postId = ObjectUtils.isEmpty(m.getPostId()) ? -1: m.getPostId();
        SysPost temp = postDao.isExist(m.getPostName());
        // 通过岗位名称查询出对象不为空  并且主键id不同，说明已经存在
        if ( !ObjectUtils.isEmpty(temp) && !temp.getPostId().equals(postId)){
            return true;
        }
        return false;
    }

    @Override
    public SysPost getPostById(String postId) {
        return postDao.getPostById(postId);
    }

    @Override
    public int deleteByIds(String[] ids) throws MyException {
        for (String id:ids){
            if (isExist(id)>0){
                throw new MyException(ResultCode.ERROR,"已分配,不能删除");
            }
        }
        return postDao.deleteByIds(ids);
    }

    @Override
    public int isExist(String postId) {
        return userPostDao.isExistUser(postId);
    }

    @Override
    public List<SysPost> selectPostAll() {
        return postDao.selectPostAll();
    }

    @Override
    public List<SysPost> selectPostMutex(Integer userId) {
        return postDao.selectPostMutex(userId);
    }

    @Override
    public List<SysPost> selectPostsByUserId(Integer userId) {
        return postDao.selectPostsByUserId(userId);
    }

    @Override
    public List<SysPost> selectPostsByUserId2(Integer userId) {
        List<SysPost> userPosts = postDao.selectPostsByUserId(userId);
        List<SysPost>posts = selectPostMutex(userId);
        for (SysPost post : posts){
            for (SysPost userPost : userPosts){
                if (post.getPostId().equals(userPost.getPostId())){
                    post.setPostFlag(true);
                    break;
                }
            }
        }
        return posts;
    }


    @Override
    public int update(SysPost SysPost) {
        return postDao.update(SysPost);
    }
}
