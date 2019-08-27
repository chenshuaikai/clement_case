package com.web.interceptor;

import java.lang.reflect.Method;

/**
 * @ClassName: ICustomInterceptorImpl
 * @Author: Clement
 * @Date: 2019/8/25 16:55
 * @Version: 1.0
 * @Description: 自定义拦截器处理行为的实现类  可在方法之前之后，异常、finally中进行逻辑处理
 */
public class ICustomInterceptorImpl implements ICustomInterceptor {

    @Override
    public void before(Method method, Object[] args) {
        System.out.println("登录之前:调用了"+method.getName()+"()方法");
    }

    @Override
    public void after(Method method, Object[] args) {
        System.out.println("登录之后:调用了"+method.getName()+"()方法");
    }

    @Override
    public void afterThrowing(Method method, Object[] args, Throwable throwable) {
        System.out.println("异常:调用了"+method.getName()+"()方法");
    }

    @Override
    public void afterFinally(Method method, Object[] args) {
        System.out.println("最后必须执行的:调用了"+method.getName()+"()方法");
    }
}
