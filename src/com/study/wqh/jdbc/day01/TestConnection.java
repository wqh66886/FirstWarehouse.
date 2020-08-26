package com.study.wqh.jdbc.day01;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author: 王其浩
 * @ClassName: TestConnect
 * @Description:
 * @Date 2020/8/10
 * @version:
 */
@SuppressWarnings("all")
public class TestConnection {
    //方式一
    @Test
    public void testConnection() throws SQLException {
        Driver driver = new com.mysql.jdbc.Driver();
        String url = "jdbc:mysql://localhost:3306/test?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
        Properties info = new Properties();
        info.setProperty("user","root");
        info.setProperty("password","root");
        Connection connection = driver.connect(url,info);

        System.out.println(connection );
    }

    //方式二:方式一的迭代(使用反射):在如下的程序中不出现第三方的api,使得程序有更好的可移植性
    @Test
    public void testConnection2() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        Class clazz = Class.forName("com.mysql.jdbc.Driver");//反射
        Driver driver = (Driver) clazz.newInstance();

        String url = "jdbc:mysql://localhost:3306/test?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
        Properties info = new Properties();
        info.setProperty("user","root");
        info.setProperty("password","root");
        Connection connection = driver.connect(url,info);

        System.out.println(connection );
    }

    //方式三:使用DriverManager替代Driver
    @Test
    public void testConnection3() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {

        //1.获取Driver的实现类对象
        Class clazz = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) clazz.newInstance();
        //2.注册驱动
        DriverManager.registerDriver(driver);

        String url = "jdbc:mysql://localhost:3306/test?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
        String user = "root";
        String password = "root";

        //获取连接
        Connection conn = DriverManager.getConnection(url,user,password);

        System.out.println(conn);
    }

    @Test
    public void testConnection4(){
        try {
            //加载Driver驱动类进入JVM内存
            Class.forName("com.mysql.jdbc.Driver");
            //准备连接参数
            String url = "jdbc:mysql://localhost:3306/test?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
            String user = "root";
            String password = "root";
            //获取连接
            Connection connection = DriverManager.getConnection(url,user,password);
            System.out.println(connection);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void finallyConnection(){
        //将数据库连接需要的4种基本声明放入配置文件中,通过读取配置文件的方式,来获取信息

        //自定义类加载器都是ApplicationLoader(系统加载器)
        InputStream in = TestConnection.class.getClassLoader().getResourceAsStream("jdbc.properties");

        Properties properties = new Properties();
        try {
            properties.load(in);

            Class.forName(properties.getProperty("driver"));

            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            String url = properties.getProperty("url");

            Connection connection = DriverManager.getConnection(url,user,password);

            System.out.println(connection);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
