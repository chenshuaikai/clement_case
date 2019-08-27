package com.web.controller;

import com.web.annotation.FormToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: AcceptFormRequestController
 * @Author: Clement
 * @Date: 2019/8/24 22:37
 * @Version: 1.0
 * @Description: 接收form表单的控制器
 */
@RestController
@RequestMapping("/acceptFormRequest")
public class AcceptFormRequestController {


    @RequestMapping("/formRequest")
    @FormToken(remove=true)
    public Map processFormRequest(Map params) {
        HashMap<String, Object> map = new HashMap<>();
        System.out.println("接收到的提交数据:"+params);
        map.put("code",200);
        map.put("msg","已经接收到请求");
        return map;
    }
}
