package com.study.wqh.jdbc.pools;

import com.study.wqh.jdbc.util.JDBCUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;


/**
 * @author: 王其浩
 * @ClassName: Pools
 * @Description:
 * @Date 2020/9/4
 * @version:
 */
public class Pools {

    private static Pools instance = null;

    public int init_count = 3;

    public int curr_count = 0;

    public int max_active = 6;


    LinkedList<Connection> connections = new LinkedList<>();
    
    private Pools(){
        for (int i = 0; i < init_count; i++) {
            connections.add(createConnection());
        }
    }

    public static Pools getInstance() {
        if(instance == null){
            synchronized (Pools.class){
                if(instance == null){
                    return instance = new Pools();
                }
            }
        }
        return instance;
    }

    public Connection getConnection(){

        synchronized (connections){
            if (connections.size() > 0){
                curr_count++;
                return connections.removeLast();
            }else if(curr_count < max_active){
                curr_count++;
                return createConnection();
            }else{
                throw new RuntimeException("sorry!");
            }
        }
    }

    private Connection createConnection(){

        //使用动态代理
        Connection proxy = null;


        try {
            //目标对象
            Connection connection = JDBCUtils.getConnection();



            proxy = (Connection) Proxy.newProxyInstance(connection.getClass().getClassLoader(), connection.getClass().getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                    Object obj = null;

                    if("close".equals(method.getName()) && curr_count <= init_count){
                        curr_count--;
                        connections.addLast(connection);
                    }else if("close".equals(method.getName()) && curr_count > init_count){
                        curr_count--;
                        if(null != connection){
                            connection.close();
                        }
                    }else if("getConnection".equals(method.getName())){
                        obj = method.invoke(connection,args);
                    }

                    return obj;
                }
            });

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return proxy;
    }
}
