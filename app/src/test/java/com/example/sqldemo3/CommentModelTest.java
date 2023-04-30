package com.example.sqldemo3;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class CommentModelTest {

    CommentModel commentModel;


    @Before
    public void setup(){
        commentModel = new CommentModel();

    }

    @Test
    public void getId(){
        commentModel.setId("12345");
        Assert.assertEquals("12345", commentModel.getId());
    }

    @Test
    public void getComment(){
        commentModel.setComment("this is a test comment");
        Assert.assertEquals("this is a test comment", commentModel.getComment());
    }

    @Test
    public void getMoney(){
        commentModel.setMoney(0.0);
        Assert.assertNotNull(commentModel.getMoney());
    }

    @Test
    public void getUserID(){
        commentModel.setUserID("userId");
        Assert.assertEquals("userId", commentModel.getUserID());
    }

    @Test
    public void constructor(){
        Map<String, String> t = new HashMap();
        t.put("comment", "comment");
        t.put("money", "0.0");
        t.put("userId", "userid");
        t.put("id", "id");
        Assert.assertNotNull(new CommentModel(t));

    }

    @Test
    public void constructorString(){
        Assert.assertNotNull(new CommentModel("id", "comment", 0.0d, "userid"));
    }

    @Test
    public void toStringMethod(){
        Assert.assertNotNull(new CommentModel("id", "comment", 0.0d, "userid").toString());
    }

}
