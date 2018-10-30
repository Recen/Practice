package com.recen.learn.base;

import com.recen.dotframe.base.CommonBaseActivity;

public class BaseActivity extends CommonBaseActivity {
    protected String getPageName() {
        return getClass().getSimpleName();
    }
}
