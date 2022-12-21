package com.example.sqldemo3;

import java.text.DecimalFormat;

public class CommentModel {

    private int id;
    private String comment;

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    private double money;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public CommentModel(int id, String comment, double money) {
        this.id = id;
        this.comment = comment;
        this.money = money;
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", money=" + money ;
    }

    public CommentModel() {
    }
}
