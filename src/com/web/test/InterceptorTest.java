package com.web.test;

import com.web.interceptor.ICustomInterceptor;
import com.web.interceptor.ICustomInterceptorImpl;
import com.web.interceptor.IDynamicProxyFactory;
import com.web.interceptor.IDynamicProxyFactoryImpl;
import com.web.service.ILoginService;
import com.web.service.ILoginServiceImpl;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: InterceptorTest
 * @Author: Clement
 * @Date: 2019/8/25 17:23
 * @Version: 1.0
 * @Description: 拦截测试
 */
public class InterceptorTest {

    /**
     * 实例动态代理工厂
     */
    IDynamicProxyFactory dynamicProxyFactory = new IDynamicProxyFactoryImpl();

    /**
     * 实例拦截器行为定义
     */
    ICustomInterceptor iCustomInterceptor = new ICustomInterceptorImpl();

    /**
     * 实例化业务登录
     */
    ILoginService iLoginService = new ILoginServiceImpl();

    @Test
    public void loginTest() {
        Map<String,Object> userInfo = new HashMap<>(1);
        userInfo.put("username","Tony");
        userInfo.put("password","123456");

        ILoginService proxy = dynamicProxyFactory.createProxy(iLoginService, iCustomInterceptor);
        Map<String, Object> loginResult = proxy.login(userInfo);
        System.out.println(loginResult);

        /*
        注意（自己的理解）：框架中的拦截处理器，也是通过对配置的读取分析，作通用的封装进行路径的拦截放行和注解的读取
         */
    }
}
