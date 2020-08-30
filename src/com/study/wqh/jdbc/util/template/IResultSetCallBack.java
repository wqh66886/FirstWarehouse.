package com.study.wqh.jdbc.util.template;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author: 王其浩
 * @ClassName: IResultSetCallBack
 * @Description:
 * @Date 2020/8/30
 * @version:
 */
public interface IResultSetCallBack {
    //回调函数

    Object handler(ResultSet rs) throws SQLException;
}
