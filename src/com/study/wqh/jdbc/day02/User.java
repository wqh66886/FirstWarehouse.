package com.study.wqh.jdbc.day02;

/**
 * @author: 王其浩
 * @ClassName: User
 * @Description:
 * @Date 2020/8/13
 * @version:
 */
public class User {
    private String user;
    private String password;

    public User(){

    }

    public User(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("user='").append(user).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
