package zhaoxizhang.github.io.leethub.model.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Article {
    @Id(autoincrement = true)
    private Long id;
    private int frontend_article_id;
    private String content;
    @Generated(hash = 894495802)
    public Article(Long id, int frontend_article_id, String content) {
        this.id = id;
        this.frontend_article_id = frontend_article_id;
        this.content = content;
    }
    @Generated(hash = 742516792)
    public Article() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getFrontend_article_id() {
        return this.frontend_article_id;
    }
    public void setFrontend_article_id(int frontend_article_id) {
        this.frontend_article_id = frontend_article_id;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    
}
