package com.recen.dotutil;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

public class ThreadUtil {
    private static Handler handler = new Handler(Looper.getMainLooper());

    public static void runOnUiThread(Context context, Runnable runnable) {
        if (Thread.currentThread() != context.getMainLooper().getThread()) {
            handler.post(runnable);
        } else {
            runnable.run();
        }
    }

    public static void runOnUiThread(Runnable runnable) {
        handler.post(runnable);
    }

    public static void runOnUiThread(Runnable runnable, long delayMillis) {
        handler.postDelayed(runnable, delayMillis);
    }

    public static void removeCallbacks(Runnable runnable){
        handler.removeCallbacks(runnable);
    }
}
