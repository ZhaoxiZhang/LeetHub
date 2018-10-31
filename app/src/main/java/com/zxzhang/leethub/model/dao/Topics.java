package com.zxzhang.leethub.model.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Topics {
    @Id(autoincrement = true)
    private Long id;
    private String  topicsName;
    private int questionId;
    @Generated(hash = 214221094)
    public Topics(Long id, String topicsName, int questionId) {
        this.id = id;
        this.topicsName = topicsName;
        this.questionId = questionId;
    }
    @Generated(hash = 1829886218)
    public Topics() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTopicsName() {
        return this.topicsName;
    }
    public void setTopicsName(String topicsName) {
        this.topicsName = topicsName;
    }
    public int getQuestionId() {
        return this.questionId;
    }
    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }
}
