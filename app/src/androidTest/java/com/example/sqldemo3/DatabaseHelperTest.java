package com.example.sqldemo3;

import androidx.test.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class DatabaseHelperTest {

    DatabaseHelper databaseHelper;

    @Before
    public void setUp(){
        databaseHelper = new DatabaseHelper(InstrumentationRegistry.getTargetContext());

    }

    @Test
    public void addOne(){
        CommentModel commentModel = new CommentModel("id", "test", 0.0d, "userIdTest");
        Assert.assertTrue(databaseHelper.addOne(commentModel));
    }

    @Test
    public void getAllComments(){
        CommentModel commentModel = new CommentModel("id", "test", 0.0d, "userIdTest");
        databaseHelper.addOne(commentModel);
        Assert.assertNotNull(databaseHelper.getAllComments());
    }

    @Test
    public void deleteOne(){
        CommentModel commentModel = new CommentModel("id", "test", 0.0d, "userIdTest");
        Assert.assertFalse(databaseHelper.deleteOne(commentModel));
    }

    @Test
    public void getResultsOfOneId(){
        CommentModel commentModel = new CommentModel("id", "test", 0.0d, "userIdTest");
        databaseHelper.addOne(commentModel);
        Assert.assertNotNull(databaseHelper.getResultsOfOneId(commentModel));
    }

    @Test
    public void editOneDo(){
        CommentModel commentModel = new CommentModel("id", "test", 0.0d, "userIdTest");
        Assert.assertFalse(databaseHelper.editOneDo(commentModel));
    }
}

