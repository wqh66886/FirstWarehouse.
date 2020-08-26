package com.study.wqh.jdbc.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author: 王其浩
 * @ClassName: ConfigUtil
 * @Description:
 * @Date 2020/8/26
 * @version:
 */
public class ConfigUtil {

    private  static Properties properties = new Properties();

    /**
     * 通过静态代码块来加载配置文件
     */
    static {
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("com/study/wqh/jdbc/util/config.properties");

        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getValue(String key){
        return null == key?null:properties.getProperty(key);
    }
}
