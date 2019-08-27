package com.web.interceptor;


/**
 * @InterfaceName: IDynamicProxyFactory
 * @Author: Clement
 * @Date: 2019/8/25 16:07
 * @Version: 1.0
 * @Description: ��̬�������ӿڣ����������������
 */
public interface IDynamicProxyFactory {

    /**
     * <T>:����÷�����һ�����ͷ������� ���ͷ����ſ��Է��ط���ֵ
     * ʹ�÷��͵����壺���Է��ز�ͬ�����ͣ�������������ʹ����Ҫ���ز�ͬ�����ͣ���Ҳ�Ǵ������г�����
     * @param target ��Ҫ�������
     * @param interceptor ��Ҫʵ�ֵĽӿ�
     * @param <T>
     * @return
     */
    <T> T createProxy(T target, ICustomInterceptor interceptor);
}
