package com.recen.dotutil.log;

import java.util.ArrayList;

public class DLog {

    private static ArrayList<LogAdapter> listAdapter = new ArrayList<LogAdapter>();

    public static void addAdapter(LogAdapter adapter){
        if(null == adapter){
            return;
        }
        for(int i = 0;i < listAdapter.size();++i){
            if(adapter.getClass() == listAdapter.get(i).getClass()){
                listAdapter.remove(i);
                break;
            }
        }
        listAdapter.add(adapter);
    }

    public static void removeAdapter(LogAdapter adapter){
        for(int i = 0;i < listAdapter.size();++i){
            if(adapter == listAdapter.get(i)){
                listAdapter.remove(i);
                return;
            }
        }
    }

    public static void d(String tag, String msg, Object... obj) {
        for(int i = 0;i < listAdapter.size();++i){
            listAdapter.get(i).d(tag,msg,obj);
        }
    }

    public static void e(String tag, String msg, Object... obj) {
        for(int i = 0;i < listAdapter.size();++i){
            listAdapter.get(i).e(tag,msg,obj);
        }
    }

    public static void f(String tag, String msg, Object... obj) {
        for(int i = 0;i < listAdapter.size();++i){
            listAdapter.get(i).f(tag,msg,obj);
        }
    }

    public static void i(String tag, String msg, Object... obj) {
        for(int i = 0;i < listAdapter.size();++i){
            listAdapter.get(i).i(tag,msg,obj);
        }
    }

    public static void w(String tag, String msg, Object... obj) {
        for(int i = 0;i < listAdapter.size();++i){
            listAdapter.get(i).w(tag,msg,obj);
        }
    }

    public static void v(String tag, String msg, Object... obj) {
        for(int i = 0;i < listAdapter.size();++i){
            listAdapter.get(i).v(tag,msg,obj);
        }
    }
}
