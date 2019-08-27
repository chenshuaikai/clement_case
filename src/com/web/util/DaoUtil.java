package com.web.util;

import com.web.config.LoadDBConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @ClassName: DaoUtil
 * @Author: Clement
 * @Date: 2019/8/22 12:40
 * @Version: 1.0
 * @Description:
 */
public class DaoUtil {

    private static final String DRIVER= (String) LoadDBConfig.getDBProperties().get("driver");
    private static final String URL=(String)LoadDBConfig.getDBProperties().get("url");
    private static final String USER=(String)LoadDBConfig.getDBProperties().get("user");
    private static final String PASSWORD=(String)LoadDBConfig.getDBProperties().get("password");
    static {
        try {
            Class.forName(DRIVER);
            System.out.println("驱动加载成功");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Connection getConnection(){
        try {
            return DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
