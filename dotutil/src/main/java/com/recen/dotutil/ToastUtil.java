package com.recen.dotutil;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
    private static Toast toast;
    public static void showToast(CharSequence msg) {
        if (ContextReference.getContext() != null) {
            if (toast == null){
                toast = Toast.makeText(ContextReference.getContext(), msg, Toast.LENGTH_SHORT);
            }else {
                toast.setText(msg);
            }
            toast.show();
        } else if (BuildConfig.DEBUG) {
            throw new IllegalArgumentException("Application context was invalid, please check ContextReference.setContext has called.");
        }
    }

    public static void showToastLong(CharSequence msg) {
        if (ContextReference.getContext() != null) {
            Toast.makeText(ContextReference.getContext(), msg, Toast.LENGTH_LONG).show();
        } else if (BuildConfig.DEBUG) {
            throw new IllegalArgumentException("Application context was invalid, please check ContextReference.setContext has called.");
        }
    }

    public static void showToast(Context context, CharSequence msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void showToastLong(Context context, CharSequence msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
}
