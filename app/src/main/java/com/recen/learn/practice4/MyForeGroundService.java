package com.recen.learn.practice4;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.recen.learn.MainActivity;
import com.recen.learn.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyForeGroundService extends Service {

    private static final String TAG = MyForeGroundService.class.getSimpleName();
    ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private boolean uploadRunning = false;
    private int curRouteType = 0;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onCreate()");
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onStartCommand()");
        // 在API11之后构建Notification的方式
        Notification.Builder builder = new Notification.Builder(this.getApplicationContext()); //获取一个Notification构造器
        Intent nfIntent = new Intent(this, MainActivity.class);
        builder.setContentIntent(PendingIntent.getActivity(this, 0, nfIntent, 0))
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher))
                .setContentTitle("下拉列表中的Title") // 设置下拉列表里的标题
                .setSmallIcon(R.mipmap.ic_launcher) // 设置状态栏内的小图标
                .setContentText("要显示的内容") // 设置上下文内容
                .setWhen(System.currentTimeMillis()); // 设置该通知发生的时间
        Notification notification = builder.build(); // 获取构建好的Notification
        notification.defaults = Notification.DEFAULT_SOUND; //设置为默认的声音
        startForeground(1, notification);
        Log.d(TAG, "onCreate()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int routeType = intent.getIntExtra("RouteType",0);
        if (routeType != curRouteType){
            if (!executorService.isShutdown()){
                executorService.shutdown();
            }
            curRouteType = routeType;
            uploadRunning = true;
            upLoadingLocation(routeType);
        }
        if (!uploadRunning){
            uploadRunning = true;
            upLoadingLocation(routeType);
        }

        return super.onStartCommand(intent, flags, startId);
    }

    private void upLoadingLocation(final int routeType){
        final int[] count = {0};
        if (executorService.isShutdown()){
            executorService = Executors.newSingleThreadScheduledExecutor();
        }
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                ++count[0];
                Log.d(TAG, "ScheduledExecutorService()" + count[0] + routeType);
            }
        },0,10000,TimeUnit.MILLISECONDS);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        executorService.shutdown();
    }
}
