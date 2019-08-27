package com.web.interceptor;

import java.lang.reflect.Method;

/**
 * @ClassName: Interceptor
 * @Author: Clement
 * @Date: 2019/8/25 16:22
 * @Version: 1.0
 * @Description: �Զ�����������ӿ� ���������������ش�����Ϊ
 */
public interface ICustomInterceptor {

    /**
     *
     * @param method ����ķ���
     * @param args ������ߵĲ���
     */
    void before(Method method, Object[]args);

    /**
     *
     * @param method ����ķ���
     * @param args ������ߵĲ���
     */
    void after(Method method,Object[]args);

    /**
     * �쳣֮��ִ��
     * throwable�а���exception���쳣����error������
     * Error��һ�����ص����⣬Ӧ�ó���Ӧ�ò�׽��
     * Exceptionһ������ǳ����ҵ���ϵĴ����ǿ��Իָ��ġ�
     * Exception�ַ�Ϊ������쳣�ͷǼ�����쳣
     * @param method ����ķ���
     * @param args ������ߵĲ���
     * @param throwable �쳣
     */
    void afterThrowing(Method method,Object[]args,Throwable throwable);

    /**
     * ��finally֮��ִ��
     * @param method ����ķ���
     * @param args ������ߵĲ���
     */
    void afterFinally(Method method,Object[]args);
}
