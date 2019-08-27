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
 * @Description: �����ظ��ύ������
 */
public  class AvoidRepeatCommitInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            /*
            HandlerMethod
            ��װ�˺ܶ����ԣ��ڷ������󷽷���ʱ����Է���ķ��ʵ�������
            ���������������ϵ�ע�⡢������Ȳ��ҶԷ���������װ����
            Ҳ���Է���ķ��ʵ�����������ע�����Ϣ
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
                    ������֤�Ƿ����ظ��ύ�ķ���������֤
                    �ظ��ύ�򷵻�true
                     */
                    if (isRepeatSubmit(request)) {
                        //�����ص���������Ӧ
                        response.setCharacterEncoding("UTF-8");
                        response.setContentType("application/json;charset=UTF-8");
                        PrintWriter out = response.getWriter();
                        HashMap<String, Object> map = new HashMap<>();
                        map.put("code",200);
                        map.put("msg","����Ϊ�ظ������Ѿ�������");
                        JSONObject jsonObject = new JSONObject(map);
                        out.print(jsonObject);
                        return false;
                    }
                    //ִ��ҵ���������֮ǰ������session�е�tokenɾ������ֹҳ��ǰ�����˵�ʱ������ظ��ύ��session�е�token�Ա�
                    request.getSession(false).removeAttribute("formToken");
                }
            }
            return true;
        }else {
            return super.preHandle(request, response, handler);
        }
    }


    /**
     * �Ƿ����ظ��ύ�ж�
     * @param request
     * @return
     */
    public boolean isRepeatSubmit(HttpServletRequest request){
        String serverToken = (String) request.getSession(false).getAttribute("formToken");
        //�����һ���ύ����session�е�token��Ϊ��
        if(serverToken==null) {
            return true;
        }

        String clientToken = request.getParameter("formToken");

        if (clientToken == null) {
            return true;
        }

        //���ظ��û���token��session�е�token����ֵ�Ա�,��ͬ���ظ��ύ
        if (!serverToken.equals(clientToken)) {
            return true;
        }
        return false;
    }
}
