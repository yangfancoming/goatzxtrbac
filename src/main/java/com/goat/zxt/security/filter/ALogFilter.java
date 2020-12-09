package com.goat.zxt.security.filter;


import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * 增加输出日志traceRootId
 */
@Component
public class ALogFilter extends OncePerRequestFilter {

    private Logger log = LoggerFactory.getLogger(getClass());

    private static final String UNIQUE_ID = "requestId";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        log.info("日志 过滤器 ---> {}", JSON.toJSONString(chain));
        boolean bInsertMDC = insertMDC();
        try {
            chain.doFilter(request, response);
        } finally {
            if(bInsertMDC) {
                MDC.remove(UNIQUE_ID);
            }
        }
    }

    private boolean insertMDC() {
        String uniqueId = UUID.randomUUID().toString().replace("-", "");
        MDC.put(UNIQUE_ID, uniqueId);
        return true;
    }
}
