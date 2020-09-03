package com.study.wqh.jdbc.day04.dao;

import com.study.wqh.jdbc.day04.entity.User;

import java.util.List;

/**
 * @author: 王其浩
 * @ClassName: IUserDao
 * @Description:
 * @Date 2020/8/30
 * @version:
 */
public interface IUserDao {

    /**
     * 根据主键列进行查询
     * @param id
     * @return
     */
    User getById(Integer id);

    /**
     * 查询所有结果
     * @return
     */
    List<User> findAll();


    /**
     * 根据主键列进行删除
     * @param id
     */
    void deleteById (Integer id);

    /**
     * 更新用户
     * @param user
     */
    void updata(User user);


    /**
     * 新增新用户
     * @param user
     */
    void save(User user);

    List<User> pageQuery(Integer pageNow,Integer pageSize,String username);


    int getRows(String username);
}

