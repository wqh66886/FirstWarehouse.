package com.study.wqh.jdbc.util.pools;

import com.study.wqh.jdbc.util.ConfigUtil;
import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author: 王其浩
 * @ClassName: MyDataSourceUtil
 * @Description:
 * @Date 2020/9/4
 * @version:
 */
public class MyDataSourceUtil {

    private static BasicDataSource bds = new BasicDataSource();

    static {
        bds.setUrl(ConfigUtil.getValue("url"));
        bds.setUsername(ConfigUtil.getValue("user"));
        bds.setPassword(ConfigUtil.getValue("password"));
        bds.setInitialSize(Integer.parseInt(ConfigUtil.getValue("initialSize")));
        bds.setMinIdle(Integer.parseInt(ConfigUtil.getValue("minIdle")));
        bds.setMaxIdle(Integer.parseInt(ConfigUtil.getValue("maxIdle")));
        bds.setMaxWait(Long.valueOf(ConfigUtil.getValue("maxWait")));
        bds.setMaxActive(Integer.parseInt(ConfigUtil.getValue("maxActive")));
    }

    public static Connection getConnection() throws SQLException {
        return bds.getConnection();
    }
}
