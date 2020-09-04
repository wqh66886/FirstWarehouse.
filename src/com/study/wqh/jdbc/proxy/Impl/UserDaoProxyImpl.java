package com.study.wqh.jdbc.proxy.Impl;

import com.study.wqh.jdbc.proxy.IUserDao;

import java.util.UUID;

/**
 * @author: 王其浩
 * @ClassName: UserDaoProxyImpl
 * @Description:
 * @Date 2020/9/4
 * @version:
 */
public class UserDaoProxyImpl implements IUserDao {

    private IUserDao userDao ;


    public UserDaoProxyImpl(){
        this.userDao = new UserDaoImpl();
    }

    @Override
    public void del(Integer id) {
        System.out.println("日志之前...");
        userDao.del(id);
        System.out.println("日志之后....");
    }

    @Override
    public String getName() {
        System.out.println("日志之前...");

        String result = userDao.getName();

        System.out.println("日志之后....");
        return result;
    }
}
