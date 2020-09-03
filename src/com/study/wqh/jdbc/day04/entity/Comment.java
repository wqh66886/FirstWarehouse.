package com.study.wqh.jdbc.day04.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: 王其浩
 * @ClassName: Comment
 * @Description:
 * @Date 2020/9/3
 * @version:
 */
public class Comment implements Serializable {
    private Integer id;

    private String content;

    private Integer state;

    private Integer parseCount;

    private Date createtime;

    private Date updatetime;

//    private User user;

//    private Article article;

    private Integer userId;

    private Integer articleId;

    public Comment() {
    }

    public Comment(Integer id, String content, Integer state, Integer parseCount, Date createtime, Date updatetime, Integer userId, Integer articleId) {
        this.id = id;
        this.content = content;
        this.state = state;
        this.parseCount = parseCount;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.userId = userId;
        this.articleId = articleId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getParseCount() {
        return parseCount;
    }

    public void setParseCount(Integer parseCount) {
        this.parseCount = parseCount;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Comment{");
        sb.append("id=").append(id);
        sb.append(", content='").append(content).append('\'');
        sb.append(", state=").append(state);
        sb.append(", parseCount=").append(parseCount);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", userId=").append(userId);
        sb.append(", articleId=").append(articleId);
        sb.append('}');
        return sb.toString();
    }
}
