package com.study.wqh.jdbc.day04.service;

import com.study.wqh.jdbc.day04.dto.PageBean;
import com.study.wqh.jdbc.day04.entity.User;

/**
 * @author: 王其浩
 * @ClassName: IUserService
 * @Description:
 * @Date 2020/9/3
 * @version:
 */
public interface IUserService {

    PageBean<User> findPage(Integer pageNow,Integer pageSize,String username);
}
