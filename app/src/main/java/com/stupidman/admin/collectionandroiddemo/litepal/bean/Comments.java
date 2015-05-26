package com.stupidman.admin.collectionandroiddemo.litepal.bean;

import java.sql.Date;

/**
 * Created by admin on 2015/5/26.
 */
public class Comments {

    private String comment;
    private Date commentdate;
    private News news;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCommentdate() {
        return commentdate;
    }

    public void setCommentdate(Date commentdate) {
        this.commentdate = commentdate;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }
}
