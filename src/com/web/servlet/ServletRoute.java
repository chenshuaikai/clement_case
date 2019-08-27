package com.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * @ClassName: ServletRoute
 * @Author: Clement
 * @Date: 2019/8/23 15:28
 * @Version: 1.0
 * @Description:
 */
public class ServletRoute extends HttpServlet {

    private final String ONE_PAGE="one";
    private final String TWO_PAGE="two";
    private final String THREE_PAGE="three";
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("sessionToken", UUID.randomUUID().toString());
        String flag = req.getParameter("flag");
        if (flag.equals(ONE_PAGE)) {
            req.getRequestDispatcher("/formSubmitTest1.jsp").forward(req, resp);
        }else if (flag.equals(TWO_PAGE)) {
            req.getRequestDispatcher("/formSubmitTest2.jsp").forward(req, resp);
        }else if(flag.equals(THREE_PAGE)){
            req.getRequestDispatcher("/formSubmitTest3.jsp").forward(req, resp);
        }else {
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }
}
