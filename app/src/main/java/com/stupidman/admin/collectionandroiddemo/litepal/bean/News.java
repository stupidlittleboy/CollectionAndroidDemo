package com.stupidman.admin.collectionandroiddemo.litepal.bean;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2015/5/26.
 */
public class News {

    private int id;
    private String title;
    private String content;
    private Date publishdate;
    private List<Comments> commentsList = new ArrayList<Comments>();
    private List<Category> categoryList = new ArrayList<Category>();

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public List<Comments> getCommentsList() {
        return commentsList;
    }

    public void setCommentsList(List<Comments> commentsList) {
        this.commentsList = commentsList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(Date publishdate) {
        this.publishdate = publishdate;
    }
}
