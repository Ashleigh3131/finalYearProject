package com.example.sqldemo3;



import androidx.test.InstrumentationRegistry;
import androidx.test.annotation.UiThreadTest;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivity2Test {

    @Rule
    public ActivityTestRule<MainActivity2> mActivityRule = new ActivityTestRule<>(MainActivity2.class);

    @Before
    public void setup(){
        InstrumentationRegistry.getTargetContext();
    }

    @Test
    public void getMoney(){
        Assert.assertNotNull(mActivityRule.getActivity().getMoney("2.0"));
    }

    @UiThreadTest
    @Test
    public void logOut(){
        Assert.assertTrue(mActivityRule.getActivity().btn_Logout.performClick());
    }

    @UiThreadTest
    @Test
    public void homeButton(){
        Assert.assertTrue(mActivityRule.getActivity().btn_Home.performClick());
        Assert.assertNotNull(mActivityRule.getActivity().arrayList);
    }

    @UiThreadTest
    @Test
    public void loginButton(){
        Assert.assertTrue(mActivityRule.getActivity().btn_toLogin.performClick());
        Assert.assertNotNull(mActivityRule.getActivity().toString());
    }
}
