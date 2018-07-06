package com.zxzhang.leethub.model.bean;


import java.util.List;

public class LeetProblem {
    private String user_name;
    private int num_solved;
    private int num_total;
    private int ac_easy;
    private int ac_medium;
    private int ac_hard;
    private double frequency_high;
    private int frequency_mid;
    private String category_slug;
    private List<StatStatusPairs> stat_status_pairs;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getNum_solved() {
        return num_solved;
    }

    public void setNum_solved(int num_solved) {
        this.num_solved = num_solved;
    }

    public int getNum_total() {
        return num_total;
    }

    public void setNum_total(int num_total) {
        this.num_total = num_total;
    }

    public int getAc_easy() {
        return ac_easy;
    }

    public void setAc_easy(int ac_easy) {
        this.ac_easy = ac_easy;
    }

    public int getAc_medium() {
        return ac_medium;
    }

    public void setAc_medium(int ac_medium) {
        this.ac_medium = ac_medium;
    }

    public int getAc_hard() {
        return ac_hard;
    }

    public void setAc_hard(int ac_hard) {
        this.ac_hard = ac_hard;
    }

    public double getFrequency_high() {
        return frequency_high;
    }

    public void setFrequency_high(double frequency_high) {
        this.frequency_high = frequency_high;
    }

    public int getFrequency_mid() {
        return frequency_mid;
    }

    public void setFrequency_mid(int frequency_mid) {
        this.frequency_mid = frequency_mid;
    }

    public String getCategory_slug() {
        return category_slug;
    }

    public void setCategory_slug(String category_slug) {
        this.category_slug = category_slug;
    }

    public List<StatStatusPairs> getStat_status_pairs() {
        return stat_status_pairs;
    }

    public void setStat_status_pairs(List<StatStatusPairs> stat_status_pairs) {
        this.stat_status_pairs = stat_status_pairs;
    }
}
