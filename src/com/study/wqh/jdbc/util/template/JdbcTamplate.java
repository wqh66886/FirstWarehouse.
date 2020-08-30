package com.study.wqh.jdbc.util.template;

import com.study.wqh.jdbc.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author: 王其浩
 * @ClassName: JdbcTamplate
 * @Description: jdbc模板工具类-模板方法设计模式+函数接口-函数回调
 * @Date 2020/8/26
 * @version:
 */
@SuppressWarnings("all")
public class JdbcTamplate {

    /**
     * 封装DML操作
     */
    public static void executeDML(IPreparedStatementCallback pcb){
        Connection conn = null;
        PreparedStatement pst = null;

        //1.获取连接
        try {
            conn = JDBCUtils.getConnection();

            //发送参数
            pst = pcb.executeSQL(conn);

            pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.CloseResource(conn,pst);
        }
    }

    /**
     * 封装DQL
     * @param pcb
     * @param rcb
     */
//    public static Object executeDQL(IPreparedStatementCallback pcb,IResultSetCallBack rcb){
//        Connection conn = null;
//        PreparedStatement pst = null;
//        ResultSet rs = null;
//        Object obj = null;
//        //1.获取连接
//        try {
//            conn = JDBCUtils.getConnection();
//
//            //发送参数
//            pst = pcb.executeSQL(conn);
//
//            rs = pst.executeQuery();
//
//            obj = rcb.handler(rs);
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }finally {
//            JDBCUtils.CloseResourse(conn,pst,rs );
//        }
//        return obj;
//    }

    /**
     * 使用泛型 返回单个结果
     * @param pcb
     * @param rcb
     * @return
     */
    public static <T> T executeDQL(IPreparedStatementCallback pcb,IResultSetCallA<T> rcb){
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        T obj = null;
        //1.获取连接
        try {
            conn = JDBCUtils.getConnection();

            //发送参数
            pst = pcb.executeSQL(conn);

            rs = pst.executeQuery();

            obj = rcb.handler(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.CloseResourse(conn,pst,rs );
        }
        return obj;
    }

    /**
     * 使用泛型-返回多个结果
     * @param pcb
     * @param rcb
     * @param <T>
     * @return
     */
    public static <T> List<T> executeMore(IPreparedStatementCallback pcb, IResultSetCallB<T> rcb){
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<T> obj = null;
        //1.获取连接
        try {
            conn = JDBCUtils.getConnection();

            //发送参数
            pst = pcb.executeSQL(conn);

            rs = pst.executeQuery();

            obj = rcb.handler(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.CloseResourse(conn,pst,rs );
        }
        return obj;
    }

    /**
     * 删除表中任意数据 -- 根据任意列删除数据
     *
     * 反射应用
     */

    public static void delete(Class<?> c,String colus,Object value){
        Connection conn = null;
        PreparedStatement pst = null;

        //1.获取连接
        try {
            conn = JDBCUtils.getConnection();

            //框架讲究的是"契约精神" -- ORM映射
            //数据库表名要和类名高度保持一致
            String tableName = c.getSimpleName();

            String sql = "delete from " + tableName + " where  " + colus + " = " + value;

            //发送参数
            pst = conn.prepareStatement(sql);

            pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.CloseResource(conn,pst);
        }
    }
}
