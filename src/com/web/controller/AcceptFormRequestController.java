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
 * @Description: ����form���Ŀ�����
 */
@RestController
@RequestMapping("/acceptFormRequest")
public class AcceptFormRequestController {


    @RequestMapping("/formRequest")
    @FormToken(remove=true)
    public Map processFormRequest(Map params) {
        HashMap<String, Object> map = new HashMap<>();
        System.out.println("���յ����ύ����:"+params);
        map.put("code",200);
        map.put("msg","�Ѿ����յ�����");
        return map;
    }
}
