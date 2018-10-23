package com.recen.dotutil.log;

public interface LogAdapter {
    void setLevel(int level);
    void v(final String tag, final String msg, final Object ... obj);
    void f(final String tag, final String msg, final Object ... obj);
    void e(final String tag, final String msg, final Object ... obj);
    void w(final String tag, final String msg, final Object ... obj);
    void i(final String tag, final String msg, final Object ... obj);
    void d(final String tag, final String msg, final Object ... obj);
}
