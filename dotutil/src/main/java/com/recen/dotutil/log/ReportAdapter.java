package com.recen.dotutil.log;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class ReportAdapter implements LogAdapter{

    private int level;
    public ReportAdapter(int level) {
        this.level = level;
    }
    @Override
    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public void v(String tag, String msg, Object... obj) {
        if (obj.length > 0) {
            msg = String.format(msg, obj);
        }
        onReport(LogLevel.ALL,msg);
    }

    @Override
    public void f(String tag, String msg, Object... obj) {
        if (obj.length > 0) {
            msg = String.format(msg, obj);
        }
        onReport(LogLevel.Fault,msg);
    }

    @Override
    public void e(String tag, String msg, Object... obj) {
        if (obj.length > 0) {
            msg = String.format(msg, obj);
        }
        onReport(LogLevel.Error,msg);
    }

    @Override
    public void w(String tag, String msg, Object... obj) {
        if (obj.length > 0) {
            msg = String.format(msg, obj);
        }
        onReport(LogLevel.Warn,msg);
    }

    @Override
    public void i(String tag, String msg, Object... obj) {
        if (obj.length > 0) {
            msg = String.format(msg, obj);
        }
        onReport(LogLevel.Info,msg);
    }

    @Override
    public void d(String tag, String msg, Object... obj) {
        if (obj.length > 0) {
            msg = String.format(msg, obj);
        }
        onReport(LogLevel.Debug,msg);
    }

    private void onReport(int level,String msg){
        Map<String, String> map = new HashMap<String, String>();
        map.put("data",  msg);
        map.put("level", String.valueOf(level));
    }
}
