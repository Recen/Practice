package com.recen.learn.download.update;

import android.content.Context;

import com.recen.learn.download.download.Download;
import com.recen.learn.download.download.UpdateDialogManager;

public class Update {

    /**正常更新，通知栏显示下载进度
     * @param context 上下文必须为Activity，且为FragmentActivity的子类
     * @param title 通知栏显示的标题
     * @param url  要下载文件的url
     * @param iconId 通知栏左侧显示的图标
     */
    public static void update(final Context context, String title, String url, int iconId){
        Download.start(context,title,url,null,iconId);
    }

    /**强制更新，App当前页面显示下载进度且不可取消
     * @param context 上下文必须为Activity，且为FragmentActivity的子类
     * @param title 无意义可传空
     * @param url 要下载文件的url
     */
    public static void updateForce(final Context context, String title, String url){
        UpdateDialogManager.get().setContext(context);
        Download.startForce(context,title,url);
    }
}
