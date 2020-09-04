package com.study.wqh.jdbc.proxy.Impl;

import com.study.wqh.jdbc.proxy.IUserDao;

/**
 * @author: 王其浩
 * @ClassName: UserDaoImpl
 * @Description:
 * @Date 2020/9/4
 * @version:
 */
public class UserDaoImpl implements IUserDao {
    @Override
    public void del(Integer id) {
        System.out.println("日志..."+id);
    }

    @Override
    public String getName() {
        //业务的核心功能
        System.out.println("getName()...");

        //System.out.println("日志之后...");
        return "代理模式";
    }
}
