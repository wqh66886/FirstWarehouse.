package com.study.wqh.jdbc.day04.dto;

import com.study.wqh.jdbc.day04.entity.Article;
import com.study.wqh.jdbc.day04.entity.Comment;

import java.util.List;

/**
 * @author: 王其浩
 * @ClassName: CommentDTO
 * @Description:
 * @Date 2020/9/3
 * @version:
 */
public class CommentDTO {

    private Article article;

    private List<Comment> commentList;

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }



    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CommentDTO{");
        sb.append("article=").append(article);
        sb.append(", commentList=").append(commentList);
        sb.append('}');
        return sb.toString();
    }
}
