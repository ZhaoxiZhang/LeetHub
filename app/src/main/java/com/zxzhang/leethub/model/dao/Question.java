package com.zxzhang.leethub.model.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Question {
    @Id(autoincrement = true)
    private Long id;
    private int question_id;
    private boolean article_live;
    private String article_slug;
    private String title;
    private String title_slug;
    private String description;
    private int difficylty;
    @Generated(hash = 1176142557)
    public Question(Long id, int question_id, boolean article_live,
            String article_slug, String title, String title_slug,
            String description, int difficylty) {
        this.id = id;
        this.question_id = question_id;
        this.article_live = article_live;
        this.article_slug = article_slug;
        this.title = title;
        this.title_slug = title_slug;
        this.description = description;
        this.difficylty = difficylty;
    }
    @Generated(hash = 1868476517)
    public Question() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public boolean getArticle_live() {
        return this.article_live;
    }
    public void setArticle_live(boolean article_live) {
        this.article_live = article_live;
    }
    public String getArticle_slug() {
        return this.article_slug;
    }
    public void setArticle_slug(String article_slug) {
        this.article_slug = article_slug;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle_slug() {
        return this.title_slug;
    }
    public void setTitle_slug(String title_slug) {
        this.title_slug = title_slug;
    }
    public int getDifficylty() {
        return this.difficylty;
    }
    public void setDifficylty(int difficylty) {
        this.difficylty = difficylty;
    }
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getQuestion_id() {
        return this.question_id;
    }
    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }
}
