package com.study.wqh.jdbc.day04.dao.Impl;

import com.study.wqh.jdbc.day04.dao.ICommentDao;
import com.study.wqh.jdbc.day04.dto.CommentDTO;
import com.study.wqh.jdbc.day04.entity.Article;
import com.study.wqh.jdbc.day04.entity.Comment;
import com.study.wqh.jdbc.util.JDBCUtils;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: 王其浩
 * @ClassName: CommentDaoImpl
 * @Description:
 * @Date 2020/9/3
 * @version:
 */
@SuppressWarnings("all")
public class CommentDaoImpl implements ICommentDao {
    @Override
    public CommentDTO findByArticle(Integer articleId) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        CommentDTO commentDTO = new CommentDTO();
        try {
            conn = JDBCUtils.getConnection();

            String sql = "select * from article where id = ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1,articleId);

            rs1 = pst.executeQuery();



            if (rs1.next()){
                Article article = new Article();
                article.setId(rs1.getInt(1));
                article.setTitle(rs1.getString(2));
                article.setSize(rs1.getString(3));
                article.setUrl(rs1.getString(4));

                Date createTime = new java.sql.Date(rs1.getTimestamp(5).getTime());
                article.setCreateTime(createTime);

                commentDTO.setArticle(article);
            }

            pst.close();

            String sQl = "select * from comment where article_id = ?";
            pst = conn.prepareStatement(sQl);
            pst.setInt(1,articleId);

            rs2 = pst.executeQuery();

            List<Comment> comments = new ArrayList<>();
            while (rs2.next()){
                Comment comment = new Comment();
                comment.setId(rs2.getInt(1));
                comment.setContent(rs2.getString(2));
                comment.setState(rs2.getInt(3));
                comment.setParseCount(rs2.getInt(4));
//                comment.setCreatetime(new Date(rs2.getTimestamp(5).getTime()));
//                comment.setUpdatetime(new Date(rs2.getTimestamp(6).getTime()));
                comment.setArticleId(rs2.getInt(7));
                comment.setUserId(rs2.getInt(8));

                comments.add(comment);
            }

            commentDTO.setCommentList(comments);

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.CloseResourse(conn,pst,rs1);
            JDBCUtils.CloseResourse(null,null,rs2);
        }
        return commentDTO;
    }

    @Override
    public List<CommentDTO> findByAllCascade() {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;

        List<CommentDTO> commentDTOS = new ArrayList<>();
        try {
            conn = JDBCUtils.getConnection();

            String sql = "select * from article";
            pst = conn.prepareStatement(sql);
            rs1 = pst.executeQuery();

//            pst.close();

            String sQl = "select * from comment where article_id = ?";
            pst = conn.prepareStatement(sQl);

            while (rs1.next()){

                List<Comment> comments = new ArrayList<>();

                CommentDTO commentDTO = new CommentDTO();

                Article article = new Article();
                article.setId(rs1.getInt(1));
                article.setTitle(rs1.getString(2));
                article.setSize(rs1.getString(3));
                article.setUrl(rs1.getString(4));

                Date createTime = new java.sql.Date(rs1.getTimestamp(5).getTime());
                article.setCreateTime(createTime);


                commentDTO.setArticle(article);

                pst.setInt(1,rs1.getInt(1));
                rs2 = pst.executeQuery();
                while (rs2.next()){
                    Comment comment = new Comment();
                    comment.setId(rs2.getInt(1));
                    comment.setContent(rs2.getString(2));
                    comment.setState(rs2.getInt(3));
                    comment.setParseCount(rs2.getInt(4));


                    comments.add(comment);
                }
                commentDTO.setCommentList(comments);
                commentDTOS.add(commentDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.CloseResourse(conn,pst,rs1);
            JDBCUtils.CloseResourse(null,null,rs2);
        }
        return commentDTOS;
    }
}
