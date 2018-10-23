package com.recen.dotframe.interfaces;

import java.util.Map;

/**
 * Created by zhangrenchen on 2018/6/6.
 */

public interface IPage extends IMessage,IMessageBox{
    /**
     * 关闭当前页面
     */
    void finish();

    /**
     * 设置result后，回退后可回调onActivityResult传递数据
     *
     * @param result
     */
    void finish(Map<String, Object> result);

    /**
     * 页面跳转
     *
     * @param pageName
     */
    void go(String pageName);

    void go(String pageName, Object params, ICallback callback);

    /**
     * 跳转到Web页面
     *
     * @param url
     */
    void goUrl(String url);
}
