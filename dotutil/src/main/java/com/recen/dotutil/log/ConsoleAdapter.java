package com.recen.dotutil.log;

import android.util.Log;

public class ConsoleAdapter implements LogAdapter{

    private int level;
    public ConsoleAdapter(int level) {
        this.level = level;
    }
    public void setLevel(int level){
        this.level = level;
    }

    @Override
    public void v(String tag, String msg, Object... obj) {
        if (level > LogLevel.ALL){
            return;
        }
        if (obj.length > 0) {
            msg = String.format(msg, obj);
        }
        Log.v(tag, msg);
    }

    @Override
    public void f(String tag, String msg, Object... obj) {
        if (level > LogLevel.Fault){
            return;
        }
        if (obj.length > 0) {
            msg = String.format(msg, obj);
        }
        Log.wtf(tag, msg);
    }

    @Override
    public void e(String tag, String msg, Object... obj) {
        if (level > LogLevel.Error){
            return;
        }
        if (obj.length > 0) {
            msg = String.format(msg, obj);
        }
        Log.e(tag, msg);
    }

    @Override
    public void w(String tag, String msg, Object... obj) {
        if (level > LogLevel.Warn){
            return;
        }
        if (obj.length > 0) {
            msg = String.format(msg, obj);
        }
        Log.w(tag, msg);
    }

    @Override
    public void i(String tag, String msg, Object... obj) {
        if (level > LogLevel.Info){
            return;
        }
        if (obj.length > 0) {
            msg = String.format(msg, obj);
        }
        Log.i(tag, msg);
    }

    @Override
    public void d(String tag, String msg, Object... obj) {
        if (level > LogLevel.Debug){
            return;
        }
        if (obj.length > 0) {
            msg = String.format(msg, obj);
        }
        Log.d(tag, msg);
    }
}
