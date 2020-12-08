package com.goat.zxt.security.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.goat.zxt.system.entity.SysRole;
import com.goat.zxt.system.entity.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.stream.Collectors;


public class JwtUserDto implements UserDetails {

    // 用户数据
    private SysUser SysUser;

    // 用户的角色信息
    private List<SysRole> roleInfo;

    // 用户 所管理的岗位id
    private List<String> postIds;

    // 用户权限的集合
    @JsonIgnore
    private List<GrantedAuthority> authorities;

    public List<String> getRoles() {
        return authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
    }

    // 加密后的密码
    @Override
    public String getPassword() {
        return SysUser.getPassword();
    }

    // 用户名
    @Override
    public String getUsername() {
        return SysUser.getUserName();
    }

    // 是否过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    // 是否锁定
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 凭证是否过期
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 是否可用
    @Override
    public boolean isEnabled() {
        return SysUser.getStatus() == 1 ? true : false;
    }

    public JwtUserDto(SysUser SysUser, List<GrantedAuthority> authorities) {
        this.SysUser = SysUser;
        this.authorities = authorities;
    }

    public SysUser getSysUser() {
        return SysUser;
    }

    public void setSysUser(SysUser sysUser) {
        SysUser = sysUser;
    }

    public List<SysRole> getRoleInfo() {
        return roleInfo;
    }

    public void setRoleInfo(List<SysRole> roleInfo) {
        this.roleInfo = roleInfo;
    }

    @Override
    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public List<String> getPostIds() {
        return postIds;
    }

    public void setPostIds(List<String> postIds) {
        this.postIds = postIds;
    }
}