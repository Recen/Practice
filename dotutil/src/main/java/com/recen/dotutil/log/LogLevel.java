package com.recen.dotutil.log;


public class LogLevel {
    public static final int ALL = 0;
    public static final int Debug = 10;
    public static final int Info = 20;
    public static final int Warn = 30;
    public static final int Error = 40;
    public static final int Fault = 50;
    public static final int OFF = 100;

    public static int getLogLevel(final String level){
        if("all".equals(level)){
            return ALL;
        }
        if("debug".equals(level)){
            return Debug;
        }
        if("info".equals(level)){
            return Info;
        }
        if("warn".equals(level)){
            return Warn;
        }
        if("error".equals(level)){
            return Error;
        }
        if("fault".equals(level)){
            return Fault;
        }
        if("off".equals(level)){
            return OFF;
        }
        return OFF;
    }
}
