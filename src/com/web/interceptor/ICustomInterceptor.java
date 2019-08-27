package com.web.interceptor;

import java.lang.reflect.Method;

/**
 * @ClassName: Interceptor
 * @Author: Clement
 * @Date: 2019/8/25 16:22
 * @Version: 1.0
 * @Description: 自定义的拦截器接口 定义拦截器的拦截处理行为
 */
public interface ICustomInterceptor {

    /**
     *
     * @param method 处理的方法
     * @param args 方法里边的参数
     */
    void before(Method method, Object[]args);

    /**
     *
     * @param method 处理的方法
     * @param args 方法里边的参数
     */
    void after(Method method,Object[]args);

    /**
     * 异常之后执行
     * throwable中包括exception（异常）和error（错误）
     * Error是一种严重的问题，应用程序不应该捕捉它
     * Exception一般可能是程序和业务上的错误，是可以恢复的。
     * Exception又分为检查性异常和非检查性异常
     * @param method 处理的方法
     * @param args 方法里边的参数
     * @param throwable 异常
     */
    void afterThrowing(Method method,Object[]args,Throwable throwable);

    /**
     * 在finally之后执行
     * @param method 处理的方法
     * @param args 方法里边的参数
     */
    void afterFinally(Method method,Object[]args);
}
