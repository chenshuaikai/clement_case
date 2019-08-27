package com.web.service;

import java.util.Map;

/**
 * @ClassName: ILoginService
 * @Author: Clement
 * @Date: 2019/8/25 16:59
 * @Version: 1.0
 * @Description: 登录接口
 */
public interface ILoginService {

    /**
     * 登录
     * @param loginInfo 登录信息
     * @return
     */
    Map<String,Object> login(Map<String,Object> loginInfo);
}
