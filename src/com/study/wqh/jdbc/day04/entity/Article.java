package com.study.wqh.jdbc.day04.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: 王其浩
 * @ClassName: Article
 * @Description:
 * @Date 2020/8/26
 * @version:
 */
public class Article implements Serializable {
    private Integer id;

    private String title;

    private String size;

    private String url;

    private Date createTime;

    public Article() {
    }

    public Article(String title, String size, String url, Date createTime) {
        this.title = title;
        this.size = size;
        this.url = url;
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Article{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", size='").append(size).append('\'');
        sb.append(", url='").append(url).append('\'');
        sb.append(", createTime=").append(createTime);
        sb.append('}');
        return sb.toString();
    }
}
