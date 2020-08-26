package com.study.wqh.jdbc.day03;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author: 王其浩
 * @ClassName: PreparedStatementCrud
 * @Description: 使用PreparedStatement来代替Statement来
 * @Date 2020/8/17
 * @version:
 */
public class PreparedStatementCrud {
    public static void main(String[] args) {
        //想数据库中Customers添加一条数据
        Properties properties = new Properties();
        InputStream in = PreparedStatementCrud.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Connection connection = null;
        PreparedStatement preparedStatement = null;



        try {
            properties.load(in);

            String url = properties.getProperty("url");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");

            Class.forName(properties.getProperty("driver"));

            connection = DriverManager.getConnection(url,user,password);

            String sql = "insert into customers(name,email,birth) values(?,?,?)";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,"王其浩");
            preparedStatement.setString(2,"wqh13815596705@163.com");
            preparedStatement.setDate(3, new Date(System.currentTimeMillis()));

            preparedStatement.execute();


//            connection.commit();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(null != preparedStatement){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(null != connection){//避免获取连接的时候没有成功,是个空指针
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
