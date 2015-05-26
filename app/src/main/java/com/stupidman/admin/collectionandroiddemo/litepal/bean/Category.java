package com.stupidman.admin.collectionandroiddemo.litepal.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2015/5/26.
 */
public class Category {

    private int id;
    private String name;
    private List<News> newsList = new ArrayList<News>();

    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
