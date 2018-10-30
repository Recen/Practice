package com.recen.dotui.util;

/**
 * Created by liuxiaofeng02 on 2016/8/4.
 */
public class FastClickChecker {
    //time unit is ms
    private static final long FAST_CLICK_INTERVAL = 1000;
    private long lastClickTime;

    public  boolean isFastClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (timeD >= 0 && timeD <= FAST_CLICK_INTERVAL) {
            return true;
        } else {
            lastClickTime = time;
            return false;
        }
    }
}
