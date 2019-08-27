package com.web.interceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @ClassName: IDynamicProxyFactoryImpl
 * @Author: Clement
 * @Date: 2019/8/25 16:34
 * @Version: 1.0
 * @Description: ��̬������ʵ����
 */
public class IDynamicProxyFactoryImpl implements IDynamicProxyFactory {

    @Override
    public <T> T createProxy(T target, ICustomInterceptor interceptor) {
        /*
        ��õ�ǰ������������
        getClassLoader():���ظ�����������,����˶����ʾһ���������ͻ� void���򷵻� null
         */
        ClassLoader classLoader=target.getClass().getClassLoader();


        /*
        ��õ�ǰ����ʵ�ֵ����нӿ�
        getInterfaces():����ֵ��һ�����飬�������˱�ʾ������ʵ�ֵ����нӿڵĶ���
         */
        Class<?>[] interfaces=target.getClass().getInterfaces();



        //����Ҫ���������ص����ٴ�������
        InvocationHandler handler=new DynamicProxyInvocationHandler(target,interceptor);

        /*
        newProxyInstance:����һ��ָ���ӿڵĴ�����ʵ��,�ýӿڿ��Խ���������ָ�ɵ�ָ���ĵ��ô������
        �൱�ڣ�
        Proxy.getProxyClass(classLoader,interfaces).getConstructor(new Class[] {InvocationHandler.class }).newInstance(new Object[]{handler})
        classLoader:�����������������
        interfaces:������Ҫʵ�ֵĽӿ��б�
        handler: ָ�ɷ������õĵ��ô������

        getProxyClass(classLoader,interfaces):���ش������ java.lang.Class ���� ��������������͸�����ʵ�ֵ����нӿڵĶ���
        getConstructor(new Class[] {InvocationHandler.class }):����һ�� Constructor ����  ������ Class �����һ������
        newInstance(new Object[]{handler})��ʹ�ô� Constructor �����ʾ�Ĺ��췽���������ù��췽�������������ʵ��������ָ���ĳ�ʼ��������ʼ����ʵ��
        ����������Ϊ�������ݸ����췽�����õĶ������飻�������͵�ֵ����װ���ʵ����͵İ�װ������

         */
        T t = (T)Proxy.newProxyInstance(classLoader, interfaces, handler);
        return t;
    }
}
