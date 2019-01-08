package com.mytaxi.android_demo;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.RootMatchers;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.GrantPermissionRule;
import android.support.test.runner.AndroidJUnit4;

import com.mytaxi.android_demo.activities.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.util.Map;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.collection.IsMapContaining.hasEntry;
import static org.hamcrest.core.AllOf.allOf;


@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestLogin {

    private String username;
    private String password;
    private String searchKeyword;
    private String driverName;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Rule
    public GrantPermissionRule permissionRule = GrantPermissionRule.grant(android.Manifest.permission.ACCESS_FINE_LOCATION);


    private MainActivity mActivity = null;

    @Before
    public void setUp() throws Exception {
        mActivity = mActivityRule.getActivity();
        username = "crazydog335";
        password = "venture";
        driverName = "Sarah Scott";
        searchKeyword = "sa";
        login(username,password);
    }


    private void login(String username , String Password) throws InterruptedException {
        //Type in username
        Espresso.onView(ViewMatchers.withId(R.id.edt_username)).perform(typeText(username));
        //Type in Password
        Espresso.onView(ViewMatchers.withId(R.id.edt_password)).perform(typeText(password));
        //Tap on login
        Espresso.onView(ViewMatchers.withId(R.id.btn_login)).perform(click());
        Thread.sleep(3000);
    }




    @Test
    public void testCallButton() throws Exception {
        //Enter search keyword
        Espresso.onView(ViewMatchers.withId(R.id.textSearch)).perform(typeText(searchKeyword));
//        Thread.sleep(5000);
        //Assert if driverName is visible in the list
        onView(withText(driverName)).inRoot(RootMatchers.withDecorView(not(is(mActivity.getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
        //Tap on the desired driver
        onView(withText(driverName))
                .inRoot(RootMatchers.withDecorView(not(is(mActivity.getWindow().getDecorView()))))
                .perform(scrollTo()).perform(click());
        //Assert the driver details
        onView(withId(R.id.textViewDriverName)).check(matches(withText(driverName)));
        //Tap on the call button
        onView(withId(R.id.fab)).perform(click());
    }

    @After
    public void tearDown() throws Exception {
        //Tap on burger menu
//        Espresso.onView(ViewMatchers.withContentDescription("Open navigation drawer")).perform(click());
        //Tap on logout
//        Espresso.onView(ViewMatchers.withId(R.id.design_menu_item_text)).perform(click());
    }
}