package com.web.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.web.annotation.FormToken;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.UUID;

/**
 * @ClassName: AvoidRepeatableCommitInterceptor
 * @Author: Clement
 * @Date: 2019/8/24 22:01
 * @Version: 1.0
 * @Description: 避免重复提交拦截器
 */
public  class AvoidRepeatCommitInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            /*
            HandlerMethod
            封装了很多属性，在访问请求方法的时候可以方便的访问到方法、
            方法参数、方法上的注解、所属类等并且对方法参数封装处理，
            也可以方便的访问到方法参数的注解等信息
             */
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            FormToken annotation = method.getAnnotation(FormToken.class);
            if (annotation != null) {
                boolean needSaveSession = annotation.save();
                System.out.println("needSaveSession:"+needSaveSession);

                if (needSaveSession) {
                    request.getSession(false).setAttribute("formToken", UUID.randomUUID().toString());
                }

                boolean needRemoveSession = annotation.remove();
                if (needRemoveSession) {
                    /*
                    调用验证是否是重复提交的方法进行验证
                    重复提交则返回true
                     */
                    if (isRepeatSubmit(request)) {
                        //对拦截的请求作响应
                        response.setCharacterEncoding("UTF-8");
                        response.setContentType("application/json;charset=UTF-8");
                        PrintWriter out = response.getWriter();
                        HashMap<String, Object> map = new HashMap<>();
                        map.put("code",200);
                        map.put("msg","请求为重复请求已经被拦截");
                        JSONObject jsonObject = new JSONObject(map);
                        out.print(jsonObject);
                        return false;
                    }
                    //执行业务操作方法之前，进行session中的token删除，防止页面前进后退的时候进行重复提交和session中的token对比
                    request.getSession(false).removeAttribute("formToken");
                }
            }
            return true;
        }else {
            return super.preHandle(request, response, handler);
        }
    }


    /**
     * 是否是重复提交判断
     * @param request
     * @return
     */
    public boolean isRepeatSubmit(HttpServletRequest request){
        String serverToken = (String) request.getSession(false).getAttribute("formToken");
        //如果第一次提交，则session中的token不为空
        if(serverToken==null) {
            return true;
        }

        String clientToken = request.getParameter("formToken");

        if (clientToken == null) {
            return true;
        }

        //返回给用户的token和session中的token进行值对比,不同则重复提交
        if (!serverToken.equals(clientToken)) {
            return true;
        }
        return false;
    }
}
