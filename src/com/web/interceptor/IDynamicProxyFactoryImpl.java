package com.web.interceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @ClassName: IDynamicProxyFactoryImpl
 * @Author: Clement
 * @Date: 2019/8/25 16:34
 * @Version: 1.0
 * @Description: 动态代理工厂实现类
 */
public class IDynamicProxyFactoryImpl implements IDynamicProxyFactory {

    @Override
    public <T> T createProxy(T target, ICustomInterceptor interceptor) {
        /*
        获得当前对象的类加载器
        getClassLoader():返回该类的类加载器,如果此对象表示一个基本类型或 void，则返回 null
         */
        ClassLoader classLoader=target.getClass().getClassLoader();


        /*
        获得当前对象实现的所有接口
        getInterfaces():返回值是一个数组，它包含了表示该类所实现的所有接口的对象
         */
        Class<?>[] interfaces=target.getClass().getInterfaces();



        //把需要处理的类加载到跟踪处理器中
        InvocationHandler handler=new DynamicProxyInvocationHandler(target,interceptor);

        /*
        newProxyInstance:返回一个指定接口的代理类实例,该接口可以将方法调用指派到指定的调用处理程序
        相当于：
        Proxy.getProxyClass(classLoader,interfaces).getConstructor(new Class[] {InvocationHandler.class }).newInstance(new Object[]{handler})
        classLoader:定义代理类的类加载器
        interfaces:代理类要实现的接口列表
        handler: 指派方法调用的调用处理程序

        getProxyClass(classLoader,interfaces):返回代理类的 java.lang.Class 对象 参数是类加载器和该类所实现的所有接口的对象
        getConstructor(new Class[] {InvocationHandler.class }):返回一个 Constructor 对象  参数是 Class 对象的一个数组
        newInstance(new Object[]{handler})：使用此 Constructor 对象表示的构造方法来创建该构造方法的声明类的新实例，并用指定的初始化参数初始化该实例
        参数：将作为变量传递给构造方法调用的对象数组；基本类型的值被包装在适当类型的包装器对象

         */
        T t = (T)Proxy.newProxyInstance(classLoader, interfaces, handler);
        return t;
    }
}
