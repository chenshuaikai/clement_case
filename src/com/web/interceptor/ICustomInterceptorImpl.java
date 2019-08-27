package com.web.interceptor;

import java.lang.reflect.Method;

/**
 * @ClassName: ICustomInterceptorImpl
 * @Author: Clement
 * @Date: 2019/8/25 16:55
 * @Version: 1.0
 * @Description: �Զ���������������Ϊ��ʵ����  ���ڷ���֮ǰ֮���쳣��finally�н����߼�����
 */
public class ICustomInterceptorImpl implements ICustomInterceptor {

    @Override
    public void before(Method method, Object[] args) {
        System.out.println("��¼֮ǰ:������"+method.getName()+"()����");
    }

    @Override
    public void after(Method method, Object[] args) {
        System.out.println("��¼֮��:������"+method.getName()+"()����");
    }

    @Override
    public void afterThrowing(Method method, Object[] args, Throwable throwable) {
        System.out.println("�쳣:������"+method.getName()+"()����");
    }

    @Override
    public void afterFinally(Method method, Object[] args) {
        System.out.println("������ִ�е�:������"+method.getName()+"()����");
    }
}
