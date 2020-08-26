package com.study.wqh.jdbc.util.template;

import com.study.wqh.jdbc.util.JDBCUtils;

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
}
