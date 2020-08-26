package com.study.wqh.jdbc.util.template;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author: 王其浩
 * @ClassName: IPreparedStatementCallback
 * @Description: 接口函数
 * @Date 2020/8/26
 * @version:
 */
public interface IPreparedStatementCallback {

    PreparedStatement executeSQL(Connection conn) throws SQLException;
}
