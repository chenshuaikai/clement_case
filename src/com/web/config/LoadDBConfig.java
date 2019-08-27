package com.web.config;

import java.io.IOException;
import java.util.Properties;

/**
 * @ClassName: LoadDBConfig
 * @Author: Clement
 * @Date: 2019/8/22 12:30
 * @Version: 1.0
 * @Description: 加载数据库连接的配置类
 */
public class LoadDBConfig {
    /**
     * 数据库连接配置的相对路径
     */
    private static final String DB_CONFIG_FILE_PATH = "/com/web/config/DBConfig.properties";
    private static Properties dbProperties;

    public static Properties getDBProperties() {
        dbProperties = new Properties();
        try {
            dbProperties.load(LoadDBConfig.class.getResourceAsStream(DB_CONFIG_FILE_PATH));
            return dbProperties;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
