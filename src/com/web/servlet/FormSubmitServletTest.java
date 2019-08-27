package com.web.servlet;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: FormSubmitServletTest
 * @Author: Clement
 * @Date: 2019/8/22 19:37
 * @Version: 1.0
 * @Description:
 */
public class FormSubmitServletTest extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String,Object> map = new HashMap<>();

        boolean isRepeatSubmit = isRepeatSubmit(req);
        if(isRepeatSubmit) {
            map.put("code",666);
            map.put("msg","�ظ��ύ");

        }else{
            req.getSession().removeAttribute("sessionToken");
            map.put("code",200);
            map.put("msg","�����ɹ�");
        }



        //������Ӧ��ʽtext/json
        resp.setContentType("application/json; charset=utf-8");
        /*
        ��ô�ӡ�����
        resp.getWriter().print(),�������Դ�ӡ����ı���ʽ�ģ�����html��ǩ���������Խ�һ��������Ĭ�ϵı��뷽ʽת��Ϊ�������ֽ����
        resp.getWriter().writer����,ֻ�ܴ�ӡ����ı���ʽ�ģ�����html��ǩ���������Դ�ӡ����
         */
        PrintWriter out = resp.getWriter();

        JSONObject jsonObject = new JSONObject(map);
        out.print(jsonObject);
        out.flush();
        out.close();
    }


    private boolean isRepeatSubmit(HttpServletRequest request) {
        String clientToken = request.getParameter("token");
        if (clientToken==null||clientToken=="") {
            return true;
        }

        String serverToken = (String) request.getSession().getAttribute("sessionToken");
        if(serverToken==null||serverToken=="") {
            return true;
        }

        if (!clientToken.equals(serverToken)) {
            return true;
        }

        return false;
    }
}
