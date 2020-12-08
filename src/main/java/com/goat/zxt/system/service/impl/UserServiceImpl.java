package com.goat.zxt.system.service.impl;



import com.goat.zxt.common.exceptionhandler.MyException;
import com.goat.zxt.common.utils.Result;
import com.goat.zxt.common.utils.ResultCode;
import com.goat.zxt.system.annotation.DataPermission;
import com.goat.zxt.system.dao.RoleUserDao;
import com.goat.zxt.system.dao.UserDao;
import com.goat.zxt.system.dao.UserPostDao;
import com.goat.zxt.system.entity.SysRoleUser;
import com.goat.zxt.system.entity.SysUser;
import com.goat.zxt.system.entity.SysUserPost;
import com.goat.zxt.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleUserDao roleUserDao;

    @Autowired
    private UserPostDao userPostDao;

    @Override
    @DataPermission(deptAlias = "d", userAlias = "u")
    public List<SysUser> list(SysUser SysUser) {
        return userDao.list(SysUser);
    }

    @Override
    public SysUser getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public void checkUserAllowed(SysUser user) {
        SysUser temp = userDao.getUserById(user.getUserId());
        if (!StringUtils.isEmpty(temp.getUserId()) && temp.isAdmin()){
            throw new MyException(ResultCode.ERROR,"不允许操作超级管理员用户");
        }
    }

    @Override
    public Boolean checkPhoneUnique(SysUser SysUser) {
        Integer userId = ObjectUtils.isEmpty(SysUser.getUserId()) ? -1: SysUser.getUserId();
        SysUser info = userDao.checkPhoneUnique(SysUser.getPhone());
        if ( !ObjectUtils.isEmpty(info)&& !info.getUserId().equals(userId)){
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public Result<SysUser> updateUser(SysUser SysUser, Integer roleId) {
        if (roleId!=null){
            // 更新用户
            userDao.updateUser(SysUser);
            SysRoleUser SysRoleUser = new SysRoleUser();
            SysRoleUser.setUserId(SysUser.getUserId());
            SysRoleUser.setRoleId(roleId);
            // 更新 角色-用户
            if(roleUserDao.getRoleUserByUserId(SysUser.getUserId())!=null){
                roleUserDao.updateSysRoleUser(SysRoleUser);
            }else {
                roleUserDao.save(SysRoleUser);
            }
            // 更新 用户-岗位
            userPostDao.deleteUserJobByUserId(SysUser.getUserId());
            insertUserPost(SysUser);
            return Result.ok().message("更新成功");
        }else {
            return Result.error().message("更新失败");
        }
    }

    @Override
    public int changeStatus(SysUser user) {
        return userDao.updateUser(user);
    }

    @Override
    @Transactional
    public Result<SysUser> save(SysUser SysUser, Integer roleId) {
        if(roleId!= null){
            userDao.save(SysUser);
            SysRoleUser SysRoleUser = new SysRoleUser();
            SysRoleUser.setRoleId(roleId);
            SysRoleUser.setUserId(SysUser.getUserId().intValue());
            roleUserDao.save(SysRoleUser);
            insertUserPost(SysUser);
            return Result.ok().message("添加成功，初始密码123456");
        }
        return Result.error().message("添加失败");
    }

    @Override
    @Transactional
    public int deleteUser(Integer userId) {
        checkUserAllowed(new SysUser(userId));
        // 删除用户 角色相关信息
        roleUserDao.deleteRoleUserByUserId(userId);
        // 删除用户 岗位相关信息
        userPostDao.deleteUserJobByUserId(userId);
        // 删除用户
        return userDao.deleteUserById(userId);
    }

    @Override
    public SysUser getUserByName(String userName) {
        return userDao.getUser(userName);
    }

    @Override
    public List<String> getPostIdsByUserId(Integer userId) {
        return userPostDao.getPostIdsByUserId(userId);
    }

    /**
     * 新增用户岗位信息
     */
    public void insertUserPost(SysUser user) {
        String[] posts = user.getPostIds();
        // 如果前台传来的 教师所管理的岗位信息为空 则直接返回
        if (posts != null && posts.length ==1 && posts[0].equals("")) return;
        // 新增用户与岗位管理
        List<SysUserPost> list = new ArrayList<>();
        for (String postId : posts){
            SysUserPost up = new SysUserPost();
            up.setUserId(user.getUserId());
            up.setPostId(postId);
            list.add(up);
        }
        if (list.size() > 0){
            userPostDao.batchUserJob(list);
        }
        return;
    }
}
