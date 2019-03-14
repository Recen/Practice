package com.recen.learn.download.download;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.recen.learn.R;


public class McDialog extends DialogFragment {
    private static final String TAG = "JcDialog";
    private Object instance;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.ThemeDialog);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (instance == null) {
            return null;
        }
        return onSafeCreateView(inflater, container, savedInstanceState);
    }

    public View onSafeCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (instance == null) {
            dismissAllowingStateLoss();
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new Dialog(getActivity(), getTheme()) {
            @Override
            public boolean dispatchTouchEvent(@NonNull MotionEvent ev) {

                return super.dispatchTouchEvent(ev);
            }
        };
    }

    public void fixedShow(FragmentActivity activity) {
        fixedShow(activity, this.toString());
    }

    public void fixedShow(FragmentActivity activity, String tag) {
        if (activity == null || activity.isFinishing()) {
            Log.d(TAG, String.format("activity [%s] is null or is finishing!", activity));
            return;
        }
        instance = new Object();
        FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
        ft.add(this, tag);
        ft.commitAllowingStateLoss();
    }

    /**
     * 此方法使用commit会出现异常 java.lang.IllegalStateException: Can not perform this
     * action after onSaveInstanceState
     */
    @Deprecated
    @Override
    public void show(FragmentManager manager, String tag) {
        super.show(manager, tag);
    }

    /**
     * 同 {@link #show(FragmentManager, String)}
     */
    @Deprecated
    @Override
    public int show(FragmentTransaction transaction, String tag) {
        return super.show(transaction, tag);
    }

    @Override
    public void dismiss() {
        super.dismiss();

    }
}
