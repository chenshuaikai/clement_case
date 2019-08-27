package com.web.controller;

import com.web.annotation.FormToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: RouteFowardController
 * @Author: Clement
 * @Date: 2019/8/24 22:30
 * @Version: 1.0
 * @Description:  路由转发的控制器
 */
@Controller
@RequestMapping("/routeForward")
public class RouteFowardController {
    private final String ONE_PAGE="one";
    private final String TWO_PAGE="two";
    private final String THREE_PAGE="three";

    /**
     * 加入需要生成token标示的注解
     * @param flag
     * @return
     */
    @RequestMapping("/formPageGoto")
    @FormToken(save = true)
    public String formPageGoto(String flag) {
        if (flag.equals(ONE_PAGE)) {
             return "/formSubmitTest1.jsp";
        }else if (flag.equals(TWO_PAGE)) {
            return "/formSubmitTest2.jsp";
        }else if(flag.equals(THREE_PAGE)){
            return "/formSubmitTest3.jsp";
        }else {
            return "/index.jsp";
        }
    }
}
