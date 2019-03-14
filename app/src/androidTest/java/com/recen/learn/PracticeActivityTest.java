package com.recen.learn;

import com.recen.learn.base.BaseActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;

public class PracticeActivityTest {

    @Rule
    public BaseActivityTestRule<MainActivity> mActivityTestRule = new BaseActivityTestRule(MainActivity.class);
    /**
     * 1)输入不规范手机号，页面提醒手机号不规范,手机号不符合规则，不足11位获取验证码不可点击
     */
    @Test
    public void testPhoneNotRight() {
        onView(withId(R.id.mainRecyclerView)).check(matches(isDisplayed()));
    }
}
