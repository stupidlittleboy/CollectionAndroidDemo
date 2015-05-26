package com.stupidman.admin.collectionandroiddemo.litepal.bean;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2015/5/26.
 */
public class Users extends DataSupport {

    private int id;
    private String username;//用户名
    private String password;//密码
    private List<Comments> commentsList = new ArrayList<Comments>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Comments> getCommentsList() {
        //return commentsList;
        return DataSupport.where("id = ?", String.valueOf(id)).find(Comments.class);
    }

    public void setCommentsList(List<Comments> commentsList) {
        this.commentsList = commentsList;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
