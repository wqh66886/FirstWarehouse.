package com.study.wqh.jdbc.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author: 王其浩
 * @ClassName: JDBCUtils
 * @Description: 获取数据库连接的工具类
 * @Date 2020/8/17
 * @version:
 */
@SuppressWarnings("all")
public class JDBCUtils {
    /**
     * 获取数据库连接
     * @return
     */
    public static Connection getConnection() throws SQLException{


        Connection conn = null;

        String driver = ConfigUtil.getValue("driver");
        String url = ConfigUtil.getValue("url");
        String user = ConfigUtil.getValue("user");
        String password =  ConfigUtil.getValue("password");

        conn = DriverManager.getConnection(url,user,password);

        return conn;
    }

    /**
     * 关闭数据库连接
     * @param conn
     */
    public static void CloseResource(Connection conn, PreparedStatement preparedStatement){
        if(null != preparedStatement){
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(null != conn){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭连接
     * @param connection
     * @param preparedStatement
     * @param resultSet
     */
    public static void CloseResourse(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet){
        if(null != resultSet){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(null != preparedStatement){
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(null != connection){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
