package com.recen.dotframe.impl;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import com.recen.dotframe.base.CommonApp;
import com.recen.dotframe.base.CommonBaseActivity;
import com.recen.dotframe.interfaces.ICallback;
import com.recen.dotframe.interfaces.IPage;
import com.recen.dotutil.ThreadUtil;
import com.recen.dotutil.ToastUtil;

import java.lang.ref.WeakReference;
import java.util.Map;

public abstract class AbstractPageImpl implements IPage {
    protected WeakReference<CommonBaseActivity> wrActivity;
    protected WeakReference<Fragment> wrFragment;

    public AbstractPageImpl(CommonBaseActivity activity) {
        if (null == activity) {
            throw new IllegalArgumentException("activity can not be null.");
        }

        wrActivity = new WeakReference<>(activity);
    }

    public AbstractPageImpl(Fragment fragment) {
        this((CommonBaseActivity) fragment.getActivity());
        wrFragment = new WeakReference<Fragment>(fragment);
    }

    @Override
    public void finish() {
        Activity activity = wrActivity.get();
        if (activity != null && !activity.isFinishing()) {
            activity.finish();
        }
    }

    @Override
    public void finish(Map<String, Object> result) {

    }

    @Override
    public void go(String pageName) {
        go(pageName,null,null);
    }

    @Override
    public void go(String pageName, Object params, ICallback callback) {
        goPage(pageName,params,callback);
    }

    @Override
    public void goUrl(String url) {

    }

    @Override
    public void showMessage(final String message) {
        if (getApplicationContext() != null) {
            ThreadUtil.runOnUiThread(getApplicationContext(), new Runnable() {
                @Override
                public void run() {
                    ToastUtil.showToast(message);
                }
            });
        }
    }

    private Application getApplicationContext(){
        return CommonApp.getInstance();
    }

    protected abstract void goPage(String pageName, Object params, ICallback callback);


    @Override
    public void showDialog(int type, Object params, ICallback callback) {

    }
}
