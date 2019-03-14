package com.recen.learn.base;

import android.app.Activity;
import android.support.test.rule.ActivityTestRule;

public class BaseActivityTestRule<T extends Activity> extends ActivityTestRule{
    public BaseActivityTestRule(Class<T> activityClass) {
        super(activityClass);
    }

    public BaseActivityTestRule(Class<T> activityClass, boolean initialTouchMode) {
        super(activityClass, initialTouchMode);
    }

    public BaseActivityTestRule(Class<T> activityClass, boolean initialTouchMode, boolean launchActivity) {
        super(activityClass, initialTouchMode, launchActivity);
    }

    @Override
    protected void beforeActivityLaunched() {
        super.beforeActivityLaunched();
    }
}
