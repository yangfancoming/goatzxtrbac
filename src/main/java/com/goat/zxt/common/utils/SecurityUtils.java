package com.goat.zxt.common.utils;


import com.goat.zxt.common.exceptionhandler.MyException;
import com.goat.zxt.security.dto.JwtUserDto;
import com.goat.zxt.system.entity.SysUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import java.util.List;


public class SecurityUtils {

    /**
     * 获取系统用户名称
     */
    public static String getCurrentUsername() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new MyException(ResultCode.UNAUTHORIZED, "当前登录状态过期");
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userDetails.getUsername();
    }

    /**
     * 取得当前用户登录IP, 如果当前用户未登录则返回空字符串.
     * 无用
     */
    public static String getCurrentUserIp() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            throw new MyException(ResultCode.UNAUTHORIZED, "当前登录状态过期");
        }
        Object details = authentication.getDetails();
        if (!(details instanceof WebAuthenticationDetails)) {
            return "";
        }
        WebAuthenticationDetails webDetails = (WebAuthenticationDetails) details;
        return webDetails.getRemoteAddress();
    }

    /**
     * 获取系统用户
     * @return 系统用户名称
     */
    public static JwtUserDto getCurrentDtoUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new MyException(ResultCode.UNAUTHORIZED, "当前登录状态过期");
        }
        JwtUserDto userDetails = (JwtUserDto) authentication.getPrincipal();
        return userDetails;
    }

    public static SysUser getCurrentUser() {
        JwtUserDto currentDtoUser = getCurrentDtoUser();
        return currentDtoUser.getSysUser();
    }

    public static Integer getCurrentUserId() {
       return  getCurrentUser().getUserId();
    }

    public static List<String> getCurrentUserPostIds() {
        return getCurrentDtoUser().getPostIds();
    }
}
