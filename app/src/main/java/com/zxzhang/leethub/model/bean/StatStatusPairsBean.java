package com.zxzhang.leethub.model.bean;

import java.util.List;

public class StatStatusPairsBean {
    private StatBean stat;
    private Object status;
    private DifficultyBean difficulty;
    private boolean paid_only;
    private boolean is_favor;
    private int frequency;
    private int progress;

    public StatBean getStat() {
        return stat;
    }

    public void setStat(StatBean stat) {
        this.stat = stat;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    public DifficultyBean getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(DifficultyBean difficulty) {
        this.difficulty = difficulty;
    }

    public boolean isPaid_only() {
        return paid_only;
    }

    public void setPaid_only(boolean paid_only) {
        this.paid_only = paid_only;
    }

    public boolean isIs_favor() {
        return is_favor;
    }

    public void setIs_favor(boolean is_favor) {
        this.is_favor = is_favor;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}
