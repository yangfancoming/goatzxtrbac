//package com.goat.zxt.system.aspectj;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.slf4j.MDC;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.Arrays;
//import java.util.UUID;
//
///**
// * 依赖的是log4j 和 logback 提供的一种方便在多线程条件下记录日志的功能MDC（Mapped Diagnostic Context，映射调试上下文）。
// *
// * MDC 可以看成是一个与当前线程绑定的哈希表，可以往其中添加键值对。MDC 中包含的内容可以被同一线程中执行的代码所访问。当前线程的子线程会继承其父线程中的 MDC 的内容。
// * 当需要记录日志时，只需要从 MDC 中获取所需的信息即可。MDC 的内容则由程序在适当的时候保存进去。对于一个 Web 应用来说，通常是在请求被处理的最开始保存这些数据。
// * 具体内容请参考：
// * https://blog.csdn.net/sunzhenhua0608/article/details/29175283
// */
//@Aspect
//@Configuration
//public class SpringAOP {
//
//    private static final Logger logger = LoggerFactory.getLogger(SpringAOP.class);
//
//    /**
//     * 定义切点Pointcut
//     * 第一个*号：表示返回类型， *号表示所有的类型
//     * 第二个*号：表示类名，*号表示所有的类
//     * 第三个*号：表示方法名，*号表示所有的方法
//     * 后面括弧里面表示方法的参数，两个句点表示任何参数
//     */
//    @Pointcut("execution(*  com.goat.zxt.system.controller.*.*(..))")
//    public void executionService() {}
//
//    /**
//     * 方法调用之前调用
//     */
//    @Before(value = "executionService()")
//    public void doBefore(JoinPoint joinPoint){
//        String uniqueId = UUID.randomUUID().toString().replace("-", "");
//        MDC.put("requestId",uniqueId);
//    }
//
//    /**
//     * 方法之后调用
//     */
//    @AfterReturning(pointcut = "executionService()",returning="returnValue")
//    public void  doAfterReturning(JoinPoint joinPoint,Object returnValue){
//        MDC.clear();
//    }
//}
