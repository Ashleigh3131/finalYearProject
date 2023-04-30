package com.example.sqldemo3;

import androidx.test.annotation.UiThreadTest;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivity3Test {

    @Rule
    public ActivityTestRule<MainActivity3> mActivityRule = new ActivityTestRule<>(MainActivity3.class);

    @UiThreadTest
    @Test
    public void homeButton(){
        Assert.assertTrue(mActivityRule.getActivity().btn_Home.performClick());
    }

    @UiThreadTest
    @Test
    public void logout(){
        Assert.assertTrue(mActivityRule.getActivity().btn_Logout.performClick());
    }

    @UiThreadTest
    @Test
    public void browse(){
        Assert.assertTrue(mActivityRule.getActivity().btn_Browse.performClick());
    }

}
