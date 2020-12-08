package com.goat.zxt.common.exceptionhandler;


import com.alibaba.fastjson.JSON;
import com.goat.zxt.common.utils.Result;
import com.goat.zxt.common.utils.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    private Logger log = LoggerFactory.getLogger(getClass());

    // 指定处理什么异常
    @ExceptionHandler(Exception.class)
    public Result error(Exception e){
        log.error("执行了全局异常 ---> {}", JSON.toJSONString(e));
        e.printStackTrace();
        return Result.error().message("执行了全局异常");
    }

    // 自定义异常
    @ExceptionHandler(MyException.class)
    public Result error(MyException e){
        log.error("自定义异常 ---> {}", JSON.toJSONString(e));
        e.printStackTrace();
        return Result.error().code(e.getCode()).message(e.getMsg());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public Result handleAuthorizationException(AccessDeniedException e){
        log.error("没有权限，请联系管理员授权 ---> {}", JSON.toJSONString(e));
        return Result.error().code(ResultCode.FORBIDDEN).message("没有权限，请联系管理员授权");
    }

    @ExceptionHandler(BadCredentialsException.class)
    public Result userNotFound(BadCredentialsException e){
        log.error("用户名或者密码错误 ---> {}", JSON.toJSONString(e));
        return Result.error().code(ResultCode.FORBIDDEN).message("用户名或者密码错误");
    }

    @ExceptionHandler(LockedException.class)
    public Result userLocked(LockedException e){
        log.error("用户锁定 ---> {}", JSON.toJSONString(e));
        return Result.error().code(ResultCode.FORBIDDEN).message(e.getMessage());
    }

    @ExceptionHandler(AuthenticationServiceException.class)
    public Result handleAuthenticationServiceException(AuthenticationServiceException e){
        log.error("验证码错误 ---> {}", JSON.toJSONString(e));
        return Result.error().message("验证码错误");
    }
}
