package com.study.wqh.jdbc.day04.dao.Impl;

import com.study.wqh.jdbc.day04.dao.IUserDao;
import com.study.wqh.jdbc.day04.entity.StatusEnum;
import com.study.wqh.jdbc.day04.entity.User;
import com.study.wqh.jdbc.util.template.JdbcTamplate;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: 王其浩
 * @ClassName: UserDaoImpl
 * @Description:
 * @Date 2020/8/30
 * @version:
 */
@SuppressWarnings("all")
public class UserDaoImpl implements IUserDao {
    @Override
    public User getById(Integer id) {
//        Connection conn = null;
//        PreparedStatement pst = null;
//        ResultSet rs = null;
//
//        try {
//            conn = JDBCUtils.getConnection();
//            String sql = "select * from user where id = ?";
//            pst = conn.prepareStatement(sql);
//
//            pst.setInt(1,id);
//
//            rs = pst.executeQuery();
//
//            while (rs.next()){
//                user.setId(rs.getInt(1));
//                user.setUsername(rs.getString(2));
//                user.setPassword(rs.getString(3));
//                String status = rs.getString(4);
//
//                StatusEnum statusEnum = Enum.valueOf(StatusEnum.class,status);
//                user.setStatus(statusEnum);
//                user.setRegisterTime(new Date(rs.getTimestamp(5).getTime()));
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }finally {
//            JDBCUtils.CloseResourse(conn,pst,rs);
//        }
        return JdbcTamplate.executeDQL(conn -> {
            String sql = "select * from user where id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,id);

            return pst;
        },rs -> {
            User user = new User();
            if (rs.next()){
                user.setId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                String status = rs.getString(4);

                StatusEnum statusEnum = Enum.valueOf(StatusEnum.class,status);
                user.setStatus(statusEnum);
                user.setRegisterTime(new Date(rs.getTimestamp(5).getTime()));

            }
            return user;
        });
    }

    @Override
    public List<User> findAll() {
        return JdbcTamplate.executeMore(conn -> {

            String sql = "select * from user";
            PreparedStatement pst = conn.prepareStatement(sql);

            return pst;
        },rs -> {
            List<User> users = new ArrayList<>();
            while (rs.next()){
                User user = new User();
                user.setId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                String status = rs.getString(4);

                StatusEnum statusEnum = Enum.valueOf(StatusEnum.class,status);
                user.setStatus(statusEnum);
                user.setRegisterTime(new Date(rs.getTimestamp(5).getTime()));

                users.add(user);
            }
            return users;
        });
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void updata(User user) {
        JdbcTamplate.executeDML(conn -> {
            String sql = "update user set username = ?,password = ?,status = ? where id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1,user.getUsername());
            pst.setString(2,user.getPassword());
            pst.setString(3,user.getStatus().toString());
            pst.setInt(4,user.getId());

            return pst;
        });
    }

    @Override
    public void save(User user) {

    }

    @Override
    public List<User> pageQuery(Integer pageNow, Integer pageSize, String username) {
        return JdbcTamplate.executeMore(conn -> {
            String sql = "select * from user where username like ? limit ?,?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,"%"+username+"%");
            pst.setInt(2,(pageNow-1)*pageSize);
            pst.setInt(3,pageSize);
            return pst;
        },rs -> {
            List<User> users = new ArrayList<>();
            while (rs.next()){
                User user = new User();
                user.setId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                String status = rs.getString(4);

                StatusEnum statusEnum = Enum.valueOf(StatusEnum.class,status);
                user.setStatus(statusEnum);
                user.setRegisterTime(new Date(rs.getTimestamp(5).getTime()));

                users.add(user);
            }
            return users;
        });
    }

    @Override
    public int getRows(String username) {
        return JdbcTamplate.executeDQL(conn -> {
            String sql = "select count(*) from user where username like ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,"%"+username+"%");
            return pst;
        },rs -> {
            int count = 0;
            if(rs.next()){
                count = rs.getInt(1);
            }
            return count;
        });
    }
}
