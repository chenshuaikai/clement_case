package com.web.interceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @ClassName: DynamicProxyInvocationHandler
 * @Author: Clement
 * @Date: 2019/8/25 16:39
 * @Version: 1.0
 * @Description: ��̬������ô����� �����صĹ���
 */
public class DynamicProxyInvocationHandler implements InvocationHandler {


    private Object target;
    private ICustomInterceptor iCustomInterceptorImpl;

    public DynamicProxyInvocationHandler(){}
    /**
     *
     * @param target ������
     * @param iCustomInterceptorImpl ����������Ϊ�ӿ�
     */
    public DynamicProxyInvocationHandler(Object target,ICustomInterceptor iCustomInterceptorImpl ) {
        this.target = target;
        this.iCustomInterceptorImpl = iCustomInterceptorImpl;
    }
    /**
     * ��invoke()����������Ҫ��ӡ��������������+��.��+������+��(��+����+��,��+����+...+��)����
     * @param proxy �������
     * @param method ����ķ���
     * @param args �����Ĳ���
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Object result;
        try {
            /*
            ע�⣺������Ի��ע���ٽ��д���(���ﲻ����ʾ)
              SelfAnnotation methodAnnotation = method.getAnnotation(SelfAnnotation.class);
              SelfAnnotation classAnnotation  = this.target.getClass().getAnnotation(SelfAnnotation.class);
             */

            //��ִ��method֮ǰ���е���
            this.iCustomInterceptorImpl.before(method,args);

            //����������
            result=method.invoke(this.target,args);

            //��ִ��method֮�����interceptorȥ��ʲô��
            this.iCustomInterceptorImpl.after(method,args);

        }catch (Throwable throwable) {
            //�ڷ����쳣֮�����interceptorȥ��ʲô��
            this.iCustomInterceptorImpl.afterThrowing(method,args,throwable);
            throw throwable;
        }finally {
            //��finally֮�����interceptorȥ��ʲô��
            this.iCustomInterceptorImpl.afterFinally(method,args);
        }
        return result;
    }
}
