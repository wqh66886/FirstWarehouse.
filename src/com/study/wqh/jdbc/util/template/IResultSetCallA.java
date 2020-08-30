package com.study.wqh.jdbc.util.template;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author: 王其浩
 * @ClassName: IResultSetCallA
 * @Description:
 * @Date 2020/8/30
 * @version:
 */
public interface IResultSetCallA<T> {
    T handler(ResultSet rs) throws SQLException;
}
