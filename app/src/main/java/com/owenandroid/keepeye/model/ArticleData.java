package com.owenandroid.keepeye.model;

/**
 * Created by Administrator on 2017/4/14.
 * 精选文章的实体类
 */

public class ArticleData {
    private String title;//文章标题
    private String label;//文章标签
    private String userName;//文章的来源媒体
    private String imageUrl;//文章的配图url
    private String articleUrl;//文章详情url

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }
}
