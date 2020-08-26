package com.study.wqh.jdbc.day04.dao;

import com.study.wqh.jdbc.day04.entity.Article;

import java.util.List;

/**
 * @author: 王其浩
 * @ClassName: IArticleDao
 * @Description:
 * @Date 2020/8/26
 * @version:
 */
public interface IArticleDao {
    /**
     * 查找所有文章
     * @return
     */
    List<Article> findAll();

    /**
     * 插入一条数据
     * @param article
     */
    void Save (Article article);

    /**
     * 根据id来查询数据
     * @param id
     * @return
     */
    Article getById(Integer id);
}
