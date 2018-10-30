package com.recen.learn.common.bindingadapters;

import android.databinding.BindingAdapter;
import android.view.View;

import com.recen.dotui.interfaces.OnClickCallback;
import com.recen.dotui.util.FastClickChecker;
import com.recen.learn.common.interfaces.IOnClickWithString;
import com.recen.learn.common.widget.TitleBar;

public class TitleBarBingdingAdapter {
    @BindingAdapter("leftButtonClickListener")
    public static void setOnLeftButtonClickListener(TitleBar titleBar, final OnClickCallback callback) {
        titleBar.setLeftButtonClickListener(new View.OnClickListener() {
            private FastClickChecker checker = new FastClickChecker();

            @Override
            public void onClick(View v) {
                if (!checker.isFastClick()) {
                    callback.onClick();
                }
            }
        });
    }

    @BindingAdapter("title")
    public static void setTitle(TitleBar titleBar, final String title) {
        titleBar.setTitle(title);
    }

    @BindingAdapter("rightButtonClickListener")
    public static void setOnRightButtonClickListener(TitleBar titleBar, final IOnClickWithString callback) {
        titleBar.setRightButtonClickListener(new View.OnClickListener() {
            private FastClickChecker checker = new FastClickChecker();

            @Override
            public void onClick(View v) {
                if (!checker.isFastClick()) {
                    String tag = null;
                    if (v.getTag() != null) {
                        tag = v.getTag().toString();
                    }
                    callback.click(tag);
                }
            }
        });
    }
}
