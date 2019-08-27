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
            map.put("msg","重复提交");

        }else{
            req.getSession().removeAttribute("sessionToken");
            map.put("code",200);
            map.put("msg","操作成功");
        }



        //设置响应格式text/json
        resp.setContentType("application/json; charset=utf-8");
        /*
        获得打印输出流
        resp.getWriter().print(),不仅可以打印输出文本格式的（包括html标签），还可以将一个对象以默认的编码方式转换为二进制字节输出
        resp.getWriter().writer（）,只能打印输出文本格式的（包括html标签），不可以打印对象。
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
