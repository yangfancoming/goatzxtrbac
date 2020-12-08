package com.goat.zxt.system.controller;

import com.wf.captcha.utils.CaptchaUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class CaptchaController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("验证码");
        CaptchaUtil.out(120, 45, 4, request, response);
    }
}
