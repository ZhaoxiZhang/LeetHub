package com.zxzhang.leethub.model.bean;

public class StatBean {
    private int question_id;
    private boolean question__article__live;
    private String question__article__slug;
    private String question__title;
    private String question__title_slug;
    private boolean question__hide;
    private int total_acs;
    private int total_submitted;
    private int frontend_question_id;
    private boolean is_new_question;

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public boolean isQuestion__article__live() {
        return question__article__live;
    }

    public void setQuestion__article__live(boolean question__article__live) {
        this.question__article__live = question__article__live;
    }

    public String getQuestion__article__slug() {
        return question__article__slug;
    }

    public void setQuestion__article__slug(String question__article__slug) {
        this.question__article__slug = question__article__slug;
    }

    public String getQuestion__title() {
        return question__title;
    }

    public void setQuestion__title(String question__title) {
        this.question__title = question__title;
    }

    public String getQuestion__title_slug() {
        return question__title_slug;
    }

    public void setQuestion__title_slug(String question__title_slug) {
        this.question__title_slug = question__title_slug;
    }

    public boolean isQuestion__hide() {
        return question__hide;
    }

    public void setQuestion__hide(boolean question__hide) {
        this.question__hide = question__hide;
    }

    public int getTotal_acs() {
        return total_acs;
    }

    public void setTotal_acs(int total_acs) {
        this.total_acs = total_acs;
    }

    public int getTotal_submitted() {
        return total_submitted;
    }

    public void setTotal_submitted(int total_submitted) {
        this.total_submitted = total_submitted;
    }

    public int getFrontend_question_id() {
        return frontend_question_id;
    }

    public void setFrontend_question_id(int frontend_question_id) {
        this.frontend_question_id = frontend_question_id;
    }

    public boolean isIs_new_question() {
        return is_new_question;
    }

    public void setIs_new_question(boolean is_new_question) {
        this.is_new_question = is_new_question;
    }
}
