package com.recen.dotui.bindingadapters;

import android.view.View;

import com.recen.dotui.interfaces.OnClickCallback;

public class BindingAdapter {
    @android.databinding.BindingAdapter("android:onClick")
    public static void setOnClick(View view, final OnClickCallback callback) {
        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                callback.onClick();
            }
        });
    }
}
