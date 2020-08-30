package com.study.wqh.jdbc.util.template;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author: 王其浩
 * @ClassName: IResultSetCallA
 * @Description:
 * @Date 2020/8/30
 * @version:
 */
public interface IResultSetCallB<T> {

    List<T> handler(ResultSet rs) throws SQLException;
}
