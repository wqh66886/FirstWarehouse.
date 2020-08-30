package com.study.wqh.jdbc.util.template;

import com.study.wqh.jdbc.util.JDBCUtils;
import com.sun.xml.internal.ws.org.objectweb.asm.ClassAdapter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author: 王其浩
 * @ClassName: JdbcTamplate
 * @Description: jdbc模板工具类-模板方法设计模式+函数接口-函数回调
 * @Date 2020/8/26
 * @version:
 */
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
