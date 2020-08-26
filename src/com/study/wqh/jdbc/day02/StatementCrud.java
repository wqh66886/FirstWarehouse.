package com.study.wqh.jdbc.day02;


import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

/**
 * @author: 王其浩
 * @ClassName: StatementCrud
 * @Description:
 * @Date 2020/8/13
 * @version:
 */
public class StatementCrud {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.print("请输入用户名: ");
        String username = scanner.nextLine();

        System.out.print("请输入密码:");
        String password = scanner.nextLine();

        InputStream in = StatementCrud.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        Connection connection = null;

        Statement statement = null;
        ResultSet resultSet = null;
        try {
            properties.load(in);

            Class.forName(properties.getProperty("driver"));
            connection = DriverManager.getConnection(properties.getProperty("url"),properties.getProperty("user"),properties.getProperty("password"));

            String sql = "select * from user_table";

            statement = connection.createStatement();

            resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                if(username.equals(resultSet.getString("user"))&&password.equals(resultSet.getString("password"))){
                    System.out.println("登录成功!");
                }else{
                    System.out.println("登录失败!");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(resultSet!=null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
