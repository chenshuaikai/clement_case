package com.web.service;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: ILoginServiceImpl
 * @Author: Clement
 * @Date: 2019/8/25 17:04
 * @Version: 1.0
 * @Description: ÓÃ»§µÇÂ¼
 */
public class ILoginServiceImpl implements ILoginService {

    private String USER_NAME_KEY = "username";
    private String PASSWORD_KEY = "password";
    private String username="Tony";
    private String password="123456";
    private Map<String,Object> resultMap;

    @Override
    public Map<String,Object> login(Map<String,Object> loginInfo) {
        resultMap  = new HashMap<>(1);
        if(loginInfo.get(USER_NAME_KEY).equals(username)&&loginInfo.get(PASSWORD_KEY).equals(password)) {
            resultMap.put("code",200);
            resultMap.put("msg","µÇÂ¼³É¹¦");
        }else {
            resultMap.put("code",401);
            resultMap.put("msg","µÇÂ¼Ê§°Ü");
        }
        return resultMap;
    }
}
