package com.recen.learn.download.download;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

public class UpdateDialogManager {
    private UpdateDialog updateDialog;
    private static FragmentActivity activity;
    private static UpdateDialogManager updateDialogManager;


    public void setContext(Context context){
        if (context instanceof FragmentActivity) {
            UpdateDialogManager.activity = (FragmentActivity)context;
        } else {
            throw new IllegalArgumentException("Dialog's baseContext must be an Activity");
        }
    }

    public void showDialog(String url) {
            updateDialog = new UpdateDialog(url);
            updateDialog.fixedShow(activity);
    }

    public void changeProgress(final int progress){
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                updateDialog.changeProgress(progress);
            }
        });

    }

    public static UpdateDialogManager get(){
        if (updateDialogManager == null){
            updateDialogManager = new UpdateDialogManager();
        }

        return updateDialogManager;
    }
}
