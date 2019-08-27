package com.web.interceptor;


/**
 * @InterfaceName: IDynamicProxyFactory
 * @Author: Clement
 * @Date: 2019/8/25 16:07
 * @Version: 1.0
 * @Description: 动态代理工厂接口，用来创建代理对象
 */
public interface IDynamicProxyFactory {

    /**
     * <T>:代表该方法是一个泛型方法（） 泛型方法才可以返回泛型值
     * 使用泛型的意义：可以返回不同的类型，更加灵活，避免多个使用需要返回不同的类型，这也是代理工厂中常见的
     * @param target 需要代理的类
     * @param interceptor 需要实现的接口
     * @param <T>
     * @return
     */
    <T> T createProxy(T target, ICustomInterceptor interceptor);
}
