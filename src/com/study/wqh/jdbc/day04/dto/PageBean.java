package com.study.wqh.jdbc.day04.dto;

import java.util.List;

/**
 * @author: 王其浩
 * @ClassName: PageBean
 * @Description:
 * @Date 2020/9/3
 * @version:
 */
public class PageBean<T> {
    private Integer pageNow;

    private Integer pageSize;

    private int rows;

    private int pageNum;

    private List<T> datas;


    public Integer getPageNow() {
        return pageNow;
    }

    public void setPageNow(Integer pageNow) {
        this.pageNow = pageNow;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }
}
