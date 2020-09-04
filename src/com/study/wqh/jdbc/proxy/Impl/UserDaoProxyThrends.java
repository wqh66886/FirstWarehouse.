package com.study.wqh.jdbc.proxy.Impl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author: 王其浩
 * @ClassName: UserDaoProxyThrends
 * @Description:
 * @Date 2020/9/4
 * @version:
 */
public class UserDaoProxyThrends implements InvocationHandler {
    private Object obj;

    public UserDaoProxyThrends(Object obj){
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("日志之前...");

        //调用目标对象的方法
        Object result = method.invoke(obj,args);

        System.out.println("日志之后...");

        return result;
    }
}
