package com.recen.learn.download.download;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;


import com.recen.learn.R;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class DownloadService extends Service {

    private static final String TAG = DownloadService.class.getSimpleName();

    public static final String KEY_URL = "KEY_URL";
    public static final String KEY_TITLE = "KEY_TITLE";
    public static final String KEY_LOGO = "KEY_LOGO";
    public static final String KEY_SAVE_NAME = "KEY_SAVE_NAME";
    public static final String KEY_FORCE = "KEY_FORCE";
    public static final String KEY_DOWNLOAD_ACTION = "KEY_DOWNLOAD_ACTION";
    private static int notificationId = 200;

    private Context context;

    private ExecutorService executorService = Executors.newCachedThreadPool();
    static Map<String, DownloadThread> taskMap = new HashMap<String, DownloadThread>(1);

    private DownloadBean createDownloadBean(String title, String url, String saveName) {
        String savePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();

        if (savePath != null && !savePath.endsWith(File.separator)) {
            savePath += File.separator;
        }
        if (TextUtils.isEmpty(saveName)) {
            int index = url.lastIndexOf("/");
            if (index != -1) {
                savePath += url.substring(index + 1);
            }
        } else {
            savePath += saveName;
        }
        Log.d("Download savePath=", savePath);
        DownloadBean bean = new DownloadBean(title, url, savePath);
        bean.size = bean.loadedSize = 0l;
        bean.enable = true;
        return bean;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    @Override
    public void onDestroy() {
        taskMap = null;
        stopSelf();
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (null == intent) {
            return super.onStartCommand(intent, flags, startId);
        }
        String url = intent.getStringExtra(KEY_URL);
        if (null == url) {
            return super.onStartCommand(intent, flags, startId);
        }
        boolean forceUpdate = intent.getBooleanExtra(KEY_FORCE, false);
        int action = intent.getIntExtra(KEY_DOWNLOAD_ACTION, 0);
        System.out.println("KEY_DOWNLOAD_ACTION=" + action);
        if (action != 0) {
            DownloadThread downloadThread = taskMap.get(url);
            if (downloadThread != null) {
                if (action == DownloadAction.PAUSE) {
                    downloadThread.pause();
                } else if (action == DownloadAction.CONTINUE) {
                    downloadThread.resume();
                    Future<?> result = executorService.submit(downloadThread);
                    downloadThread.setResult(result);
                } else if (action == DownloadAction.CANCEL) {
                    downloadThread.cancel();
                    taskMap.remove(url);
                } else if (action == DownloadAction.SUCCESS) {
                    taskMap.remove(url);
                }
            }

            return super.onStartCommand(intent, flags, startId);
        }

        //如果已添加任务，不再重复添加
        if (taskMap.containsKey(url)) {
            DownloadThread downloadThread = taskMap.get(url);
            downloadThread.resetStatus();
            if (downloadThread.isFinished()) {
                downloadThread.downloadSuccess();
            } else {
                intent.putExtra(KEY_DOWNLOAD_ACTION, DownloadAction.CONTINUE);
                return onStartCommand(intent, flags, startId);
            }
            return super.onStartCommand(intent, flags, startId);
        }
        String title = intent.getStringExtra(KEY_TITLE);
        String saveName = intent.getStringExtra(KEY_SAVE_NAME);
        DownloadBean bean = createDownloadBean(title, url, saveName);

        DownloadNotification notification = null;
        int logoResId = intent.getIntExtra(KEY_LOGO, 0);
        if (logoResId != -1) {
            if (logoResId == 0) {
                logoResId = R.drawable.mc_download_logo;
            }
            PendingIntent updatePendingIntent = PendingIntent.getActivity(context, 0, new Intent(), 0);
            notification = new DownloadNotification(context, updatePendingIntent, notificationId, url);
            notificationId += DownloadNotification.ACTION_EVENT_COUNT;
            notification.showCustomizeNotification(logoResId, title);
        }
        DownloadThread downloadThread = null;
        if (forceUpdate) {
            UpdateDialogManager.get().showDialog(url);
            downloadThread = new DownloadThread(bean, context, UpdateDialogManager.get());
        } else {
            downloadThread = new DownloadThread(bean, context, notification);
        }

        Future<?> result = executorService.submit(downloadThread);
        downloadThread.setResult(result);
        taskMap.put(url, downloadThread);
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    /**
     * Check if the primary "external" storage device is available.
     *
     * @return
     */
    public static boolean hasSDCardMounted() {
        String state = Environment.getExternalStorageState();
        if (state != null && state.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    public static void startDownload(Context context, String title, int iconResId, String url) {
        startDownload(context, title, iconResId, url, UrlUtil.getFileName(url));
    }

    public static void startDownload(Context context, String title, int iconResId, String url, String savePath) {
        Intent intent = new Intent(context, DownloadService.class);
        intent.putExtra(DownloadService.KEY_TITLE, title);
        intent.putExtra(DownloadService.KEY_URL, url);
        intent.putExtra(DownloadService.KEY_SAVE_NAME, savePath);
        intent.putExtra(DownloadService.KEY_LOGO, iconResId);
        context.startService(intent);
    }
}
