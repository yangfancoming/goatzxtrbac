package com.goat.zxt.security.handler;

import com.alibaba.fastjson.JSON;
import com.goat.zxt.common.utils.Result;
import com.goat.zxt.security.utils.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录成功
 */
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private JwtUtils jwtUtils;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        // JwtUserDto userDetails = (JwtUserDto)authentication.getPrincipal();//拿到登录用户信息
        // String jwtToken = jwtUtils.generateToken(userDetails.getUsername());//生成token
        Result result = Result.ok().message("登录成功");
        //修改编码格式
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setContentType("application/json");
        //输出结果
        httpServletResponse.getWriter().write(JSON.toJSONString(result));

    }
}
