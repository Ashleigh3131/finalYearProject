package com.example.sqldemo3;

import androidx.test.annotation.UiThreadTest;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @UiThreadTest
    @Test
    public void delete(){
        Assert.assertTrue(mActivityRule.getActivity().btn_delete.performClick());
        Assert.assertNull(mActivityRule.getActivity().commentModel);
    }

    @UiThreadTest
    @Test
    public void edit(){
        Assert.assertTrue(mActivityRule.getActivity().btn_edit.performClick());
        Assert.assertNull(mActivityRule.getActivity().commentModel);
    }

    @UiThreadTest
    @Test
    public void logout(){
        Assert.assertTrue(mActivityRule.getActivity().btn_Logout.performClick());
    }

    @UiThreadTest
    @Test
    public void login(){
        Assert.assertTrue(mActivityRule.getActivity().btn_toLogin.performClick());
    }

    @UiThreadTest
    @Test
    public void add(){
        Assert.assertTrue(mActivityRule.getActivity().btn_add.performClick());
        Assert.assertNull(mActivityRule.getActivity().commentModel);
    }

    @UiThreadTest
    @Test
    public void web(){
        Assert.assertTrue(mActivityRule.getActivity().btn_toWebActivity.performClick());
    }

}
