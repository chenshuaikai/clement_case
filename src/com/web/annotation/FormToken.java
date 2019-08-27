package com.web.annotation;


import java.lang.annotation.*;

@Inherited //���һ��ʹ����@Inherited���ε�annotation���ͱ�����һ��class�������annotation�������ڸ�class�����ࡣ
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FormToken {

    /**
     * �����ҳ����Ҫ�ظ���֤����token�����ϸñ�� ��ֵΪtrue
     * @return
     */
    boolean save() default false;

    /**
     * �����Ҫ��֤�Ƿ����ظ��ύ�����󣬼��ϸñ�� ��ֵΪtrue
     * @return
     */
    boolean remove() default false;
}
