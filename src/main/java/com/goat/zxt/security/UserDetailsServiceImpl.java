package com.goat.zxt.security;


import com.alibaba.fastjson.JSON;
import com.goat.zxt.security.dto.JwtUserDto;
import com.goat.zxt.system.dao.MenuDao;
import com.goat.zxt.system.dto.MenuIndexDto;
import com.goat.zxt.system.entity.SysRole;
import com.goat.zxt.system.entity.SysRoleUser;
import com.goat.zxt.system.entity.SysUser;
import com.goat.zxt.system.service.RoleService;
import com.goat.zxt.system.service.RoleUserService;
import com.goat.zxt.system.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private RoleUserService roleUserService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuDao menuDao;

    @Override
    public JwtUserDto loadUserByUsername(String userName){
        log.info("loadUserByUsername ---> {}", JSON.toJSONString(userName));
        //根据用户名获取用户
        SysUser user = userService.getUserByName(userName);

        if (user == null ){
            throw new BadCredentialsException("用户名或密码错误");
        }else if (user.getStatus().equals(SysUser.Status.LOCKED)) {
            throw new LockedException("用户被锁定,请联系管理员解锁");
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        List<MenuIndexDto> list = menuDao.listByUserId(user.getUserId());
        List<String> collect = list.stream().map(MenuIndexDto::getPermission).collect(Collectors.toList());
        for (String authority : collect){
            if (!("").equals(authority) & authority !=null){
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority);
                grantedAuthorities.add(grantedAuthority);
            }
        }

        List<String> postIds = userService.getPostIdsByUserId(user.getUserId());
        //将用户所拥有的权限加入GrantedAuthority集合中
        JwtUserDto loginUser = new JwtUserDto(user,grantedAuthorities);
        loginUser.setRoleInfo(getRoleInfo(user));
        loginUser.setPostIds(postIds);
        return loginUser;
    }


    public List<SysRole> getRoleInfo(SysUser SysUser) {
        SysUser userByName = userService.getUserByName(SysUser.getUserName());
        List<SysRoleUser> roleUserByUserId = roleUserService.getSysRoleUserByUserId(userByName.getUserId());
        List <SysRole> roleList = new ArrayList<>();
        for (SysRoleUser roleUser:roleUserByUserId){
            Integer roleId = roleUser.getRoleId();
            SysRole roleById = roleService.getRoleById(roleId);
            roleList.add(roleById);
        }
        return roleList;
    }

}