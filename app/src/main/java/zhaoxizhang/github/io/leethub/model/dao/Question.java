package zhaoxizhang.github.io.leethub.model.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

@Entity
public class Question implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id(autoincrement = true)
    private Long id;
    private int question_id;
    private int frontend_question_id;
    private boolean article_live;
    private String article_slug;
    private String title;
    private String title_slug;
    private String description;
    private String content;
    private String tags;
    private int difficulty;

    @Generated(hash = 2056310146)
    public Question(Long id, int question_id, int frontend_question_id,
            boolean article_live, String article_slug, String title,
            String title_slug, String description, String content, String tags,
            int difficulty) {
        this.id = id;
        this.question_id = question_id;
        this.frontend_question_id = frontend_question_id;
        this.article_live = article_live;
        this.article_slug = article_slug;
        this.title = title;
        this.title_slug = title_slug;
        this.description = description;
        this.content = content;
        this.tags = tags;
        this.difficulty = difficulty;
    }
    @Generated(hash = 1868476517)
    public Question() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getFrontend_question_id() {
        return this.frontend_question_id;
    }
    public void setFrontend_question_id(int frontend_question_id) {
        this.frontend_question_id = frontend_question_id;
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
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public int getDifficulty() {
        return this.difficulty;
    }
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
    public String getTags() {
        return this.tags;
    }
    public void setTags(String tags) {
        this.tags = tags;
    }
    public int getQuestion_id() {
        return this.question_id;
    }
    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }
}
