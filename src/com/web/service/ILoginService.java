package com.web.service;

import java.util.Map;

/**
 * @ClassName: ILoginService
 * @Author: Clement
 * @Date: 2019/8/25 16:59
 * @Version: 1.0
 * @Description: ��¼�ӿ�
 */
public interface ILoginService {

    /**
     * ��¼
     * @param loginInfo ��¼��Ϣ
     * @return
     */
    Map<String,Object> login(Map<String,Object> loginInfo);
}
