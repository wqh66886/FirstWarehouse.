package com.study.wqh.jdbc.day04.dao;

import com.study.wqh.jdbc.day04.dto.CommentDTO;

import java.util.List;


/**
 * @author: 王其浩
 * @ClassName: ICommentDao
 * @Description:
 * @Date 2020/9/3
 * @version:
 */
public interface ICommentDao {

    CommentDTO findByArticle(Integer articleId);

    List<CommentDTO> findByAllCascade();
}
