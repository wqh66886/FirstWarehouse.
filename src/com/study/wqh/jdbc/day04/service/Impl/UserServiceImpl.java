package com.study.wqh.jdbc.day04.service.Impl;

import com.study.wqh.jdbc.day04.dao.IUserDao;
import com.study.wqh.jdbc.day04.dao.Impl.UserDaoImpl;
import com.study.wqh.jdbc.day04.dto.PageBean;
import com.study.wqh.jdbc.day04.entity.User;
import com.study.wqh.jdbc.day04.service.IUserService;

import java.util.List;

/**
 * @author: 王其浩
 * @ClassName: UserServiceImpl
 * @Description:
 * @Date 2020/9/3
 * @version:
 */
public class UserServiceImpl implements IUserService {

    private IUserDao userDao = new UserDaoImpl();

    @Override
    public PageBean<User> findPage(Integer pageNow, Integer pageSize, String username) {

        PageBean<User> pageBean = new PageBean<>();

        pageBean.setPageNow(pageNow);
        pageBean.setPageSize(pageSize);

        List<User> users = userDao.pageQuery(pageNow,pageSize,username);
        pageBean.setDatas(users);

        int rows = userDao.getRows(username);
        pageBean.setRows(rows);

        int pageNum = rows/pageSize;

        if (rows % pageSize != 0){
            pageNum++;
        }

        pageBean.setPageNum(pageNum);

        return pageBean;
    }
}
