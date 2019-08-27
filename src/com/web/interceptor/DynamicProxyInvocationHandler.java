package com.web.interceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @ClassName: DynamicProxyInvocationHandler
 * @Author: Clement
 * @Date: 2019/8/25 16:39
 * @Version: 1.0
 * @Description: 动态代理调用处理器 做拦截的功能
 */
public class DynamicProxyInvocationHandler implements InvocationHandler {


    private Object target;
    private ICustomInterceptor iCustomInterceptorImpl;

    public DynamicProxyInvocationHandler(){}
    /**
     *
     * @param target 代理类
     * @param iCustomInterceptorImpl 拦截器的行为接口
     */
    public DynamicProxyInvocationHandler(Object target,ICustomInterceptor iCustomInterceptorImpl ) {
        this.target = target;
        this.iCustomInterceptorImpl = iCustomInterceptorImpl;
    }
    /**
     * 在invoke()方法里我们要打印被代理的类的类名+“.”+方法名+”(“+参数+”,”+参数+...+”)”。
     * @param proxy 代理对象
     * @param method 处理的方法
     * @param args 方法的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Object result;
        try {
            /*
            注意：这里可以获得注解再进行处理(这里不做演示)
              SelfAnnotation methodAnnotation = method.getAnnotation(SelfAnnotation.class);
              SelfAnnotation classAnnotation  = this.target.getClass().getAnnotation(SelfAnnotation.class);
             */

            //在执行method之前进行调用
            this.iCustomInterceptorImpl.before(method,args);

            //保存代理对象
            result=method.invoke(this.target,args);

            //在执行method之后调用interceptor去做什么事
            this.iCustomInterceptorImpl.after(method,args);

        }catch (Throwable throwable) {
            //在发生异常之后调用interceptor去做什么事
            this.iCustomInterceptorImpl.afterThrowing(method,args,throwable);
            throw throwable;
        }finally {
            //在finally之后调用interceptor去做什么事
            this.iCustomInterceptorImpl.afterFinally(method,args);
        }
        return result;
    }
}
