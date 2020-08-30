package com.study.wqh.jdbc.day04.dao.Impl;

import com.study.wqh.jdbc.day04.dao.IArticleDao;
import com.study.wqh.jdbc.day04.entity.Article;
import com.study.wqh.jdbc.util.JDBCUtils;
import com.study.wqh.jdbc.util.template.IPreparedStatementCallback;
import com.study.wqh.jdbc.util.template.JdbcTamplate;


import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * @author: 王其浩
 * @ClassName: ArticleDao
 * @Description:
 * @Date 2020/8/26
 * @version:
 */
@SuppressWarnings("all")
public class ArticleDaoImpl implements IArticleDao {
    @Override
    public List<Article> findAll() {
        //1.注册驱动
        InputStream inputStream = ArticleDaoImpl.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties pro = new Properties();

        Connection conn = null;

        Statement st = null;

        ResultSet rs = null;

        List<Article> articleList = new ArrayList<>();

        try {
            pro.load(inputStream);
            //加载驱动
            Class.forName(pro.getProperty("driver"));
            //驱动连接
            conn = DriverManager.getConnection(pro.getProperty("url"),pro.getProperty("user"),pro.getProperty("password"));

            String sql = "select * from article";
            //创建语句对象
            st = conn.createStatement();
            //发送SQL语句
            rs = st.executeQuery(sql);

            while (rs.next()){

                Article article = new Article();

                int id = rs.getInt(1);
                String title = rs.getString(2);
                String size = rs.getString(3);
                String url = rs.getString(4);

                //java.sql.Date extends java.util.Date
                Date createTime = rs.getDate(5);

                article.setId(id);
                article.setTitle(title);
                article.setUrl(url);
                article.setSize(size);
                article.setCreateTime(createTime);

                articleList.add(article);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(null != rs){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(null != st){
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(null != conn){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return articleList;
    }

    @Override
    public void Save(Article article) {
        InputStream inputStream = ArticleDaoImpl.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties pro = new Properties();

        Connection conn = null;

        PreparedStatement pst = null;

        try {
            pro.load(inputStream);

            Class.forName(pro.getProperty("driver"));

            conn = DriverManager.getConnection(pro.getProperty("url"),pro.getProperty("user"),pro.getProperty("password"));

            String sql = "insert into article(title,size,url,create_time) values(?,?,?,?)";

            pst = conn.prepareStatement(sql);
            pst.setString(1,article.getTitle());
            pst.setString(2,article.getSize());
            pst.setString(3,article.getUrl());
            pst.setTimestamp(4, new java.sql.Timestamp(article.getCreateTime().getTime()));

            pst.executeUpdate();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.CloseResource(conn,pst);
        }
    }

    @Override
    public Article getById(Integer id) {
        Article article = new Article();

        Connection conn = null;

        PreparedStatement pst = null;

        ResultSet rs = null;



        String sql = "select * from article where id = ?";

        try {
            conn = JDBCUtils.getConnection();

            pst = conn.prepareStatement(sql);

            pst.setInt(1,id);

            rs = pst.executeQuery();

            while (rs.next()) {
                article.setId(rs.getInt(1));
                article.setTitle(rs.getString(2));
                article.setSize(rs.getString(3));
                article.setUrl(rs.getString(4));
                article.setCreateTime(rs.getDate(5));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return article;
    }

    @Override
    public int delById(Integer id) {
        Connection conn = null;

        PreparedStatement pst = null;

        String sql = "delete from article where id = ?";

        int row = 0;

        try {
            conn = JDBCUtils.getConnection();

            pst = conn.prepareStatement(sql);

            pst.setInt(1,id);

            row = pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.CloseResource(conn,pst);
        }
        return row;
    }

    @Override
    public void removeById(Integer id) {
        //匿名内部类
//        JdbcTamplate.executeDML(new IPreparedStatementCallback() {
//            @Override
//            public PreparedStatement executeSQL(Connection conn) throws SQLException {
//                String sql = "delete from article where id = ?";
//                PreparedStatement pst = conn.prepareStatement(sql);
//                pst.setInt(1,id);
//                return pst;
//            }
//        });
        //lambta表达式
//        JdbcTamplate.executeDML(conn -> {
//            String sql = "delete from article where id = ?";
//            PreparedStatement pst = conn.prepareStatement(sql);
//            pst.setInt(1,id);
//            return pst;
//        });

        //利用反射进行删除
        JdbcTamplate.delete(Article.class,"id",7);
    }
}
