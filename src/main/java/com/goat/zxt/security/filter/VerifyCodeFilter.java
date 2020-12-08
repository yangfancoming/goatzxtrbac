//package com.goat.zxt.security.filter;
//
//import com.alibaba.fastjson.JSON;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//
//@Component
//public class VerifyCodeFilter extends OncePerRequestFilter {
//
//    private Logger log = LoggerFactory.getLogger(getClass());
//
//    private String defaultFilterProcessUrl = "/login";
//    private String method = "POST";
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
//        log.info("VerifyCodeFilter ---> {}", JSON.toJSONString(chain));
//
//        if (method.equalsIgnoreCase(request.getMethod()) && defaultFilterProcessUrl.equals(request.getServletPath())) {
//            // 登录请求校验验证码，非登录请求不用校验
//            HttpSession session = request.getSession();
//            String requestCaptcha = request.getParameter("captcha");
//            //验证码的信息存放在seesion种，具体看EasyCaptcha官方解释
//            String genCaptcha = (String) request.getSession().getAttribute("captcha");
//            response.setContentType("application/json;charset=UTF-8");
////            if (StringUtils.isEmpty(requestCaptcha)){
////                //删除缓存里的验证码信息
////                session.removeAttribute("captcha");
////                response.getWriter().write(JSON.toJSONString(Result.error().message("验证码不能为空!")));
////                return;
////            }
////            if (!genCaptcha.toLowerCase().equals(requestCaptcha.toLowerCase())) {
////                session.removeAttribute("captcha");
////                response.getWriter().write(JSON.toJSONString(Result.error().message("验证码错误!")));
////                return;
////            }
//        }
//        chain.doFilter(request, response);
//    }
//}
