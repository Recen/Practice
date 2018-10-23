package com.recen.dotutil;

import android.content.Context;

import java.lang.ref.WeakReference;

public class ContextReference {
    private static WeakReference<Context> context;

    public static Context getContext() {
        return context == null ? null : context.get();
    }

    public static void setContext(Context context) {
        ContextReference.context = new WeakReference<Context>(context);
    }
}
