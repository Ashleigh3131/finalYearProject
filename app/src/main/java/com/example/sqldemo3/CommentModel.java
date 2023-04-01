package com.example.sqldemo3;

import java.text.DecimalFormat;
import java.util.Map;

public class CommentModel {


    //getting and setters of id, comment and money
    //constructor as well to create a comment model

    //private int id;
    private String id;
    private String comment;
    private String userID;




    public String getUserID() {return userID;}
    public void setUserID(String userID) {this.userID = userID;}
    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    private double money;

    public CommentModel(Map<String, ?> map) {
        this.id = (String) map.get("id");
        this.comment = (String) map.get("comment");
        this.userID = (String) map.get("userID");
        this.money = Double.parseDouble(String.valueOf(map.get("money")));
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
//    public int getId() {
//        return id;
//    }

//    public void setId(int id) {
//        this.id = id;
//    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

//    public CommentModel(int id, String comment, double money) {
//        this.id = id;
//        this.comment = comment;
//        this.money = money;
//    }
    public CommentModel(String id, String comment, double money, String userID) {
        this.id = id;
        this.comment = comment;
        this.money = money;
        this.userID = userID;
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", money=" + money +
                ", userID=" +userID ;
    }

    public CommentModel() {
    }
}
