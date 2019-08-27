package com.web.annotation;


import java.lang.annotation.*;

@Inherited //如果一个使用了@Inherited修饰的annotation类型被用于一个class，则这个annotation将被用于该class的子类。
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FormToken {

    /**
     * 如果该页面需要重复验证产生token，加上该标记 置值为true
     * @return
     */
    boolean save() default false;

    /**
     * 如果需要验证是否是重复提交的请求，加上该标记 置值为true
     * @return
     */
    boolean remove() default false;
}
