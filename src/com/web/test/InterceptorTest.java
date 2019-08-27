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
 * @Description: ���ز���
 */
public class InterceptorTest {

    /**
     * ʵ����̬������
     */
    IDynamicProxyFactory dynamicProxyFactory = new IDynamicProxyFactoryImpl();

    /**
     * ʵ����������Ϊ����
     */
    ICustomInterceptor iCustomInterceptor = new ICustomInterceptorImpl();

    /**
     * ʵ����ҵ���¼
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
        ע�⣨�Լ�����⣩������е����ش�������Ҳ��ͨ�������õĶ�ȡ��������ͨ�õķ�װ����·�������ط��к�ע��Ķ�ȡ
         */
    }
}
