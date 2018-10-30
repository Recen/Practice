package com.recen.learn.common.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;
import android.view.WindowManager;

import com.recen.dotui.util.Safeguard;
import com.recen.learn.base.BaseActivity;
import com.recen.learn.base.BaseFragment;

public class GenericFragmentActivity extends BaseActivity {
    private static final String KEY_FRAGMENT_CLASS = "KEY_FRAGMENT_CLASS";
    private static final String KEY_FRAGMENT_ARGS = "KEY_FRAGMENT_ARGS";
    private static final String KEY_IS_FULL_SCREEN = "KEY_IS_FULL_SCREEN";
    private static final String KEY_SCREEN_ORIENTATION = "KEY_SCREEN_ORIENTATION";

    private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle bundle) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        Bundle args = getIntent().getExtras();
        if (args.getBoolean(KEY_IS_FULL_SCREEN)) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        if (args.getInt(KEY_SCREEN_ORIENTATION, ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED) != ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED) {
            //noinspection WrongConstant
            setRequestedOrientation(args.getInt(KEY_SCREEN_ORIENTATION, ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED));
        }

        super.onCreate(bundle);
        if(isFinishing()){
            return;
        }

        String fragmentClassName = args.getString(KEY_FRAGMENT_CLASS);
        try {
            Fragment fragment = (Fragment) Class.forName(fragmentClassName).newInstance();
            Bundle argument = args.getBundle(KEY_FRAGMENT_ARGS);
            fragment.setArguments(argument);

            attachFragment(getSupportFragmentManager(), fragment);
        } catch (Exception e) {
            throw new IllegalStateException("Has error in new instance of fragment");
        }
    }

    private void attachFragment(FragmentManager supportFragmentManager, Fragment fragment) {
        currentFragment = fragment;
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        transaction.add(android.R.id.content, fragment);
        transaction.commitAllowingStateLoss();
    }

    public static void start(Activity from, Class<?> fragmentClass, Bundle args) {
        if (Safeguard.ignorable(from))
            return;

        Intent intent = new Intent(from, GenericFragmentActivity.class);
        intent.putExtra(KEY_FRAGMENT_CLASS, fragmentClass.getCanonicalName());
        intent.putExtra(KEY_FRAGMENT_ARGS, args);
        from.startActivity(intent);
    }

    public static void startNewActivity(Context context, Class<?> fragmentClass, Bundle args) {
        if (Safeguard.ignorable(context))
            return;

        Intent intent = new Intent(context, GenericFragmentActivity.class);
        intent.putExtra(KEY_FRAGMENT_CLASS, fragmentClass.getCanonicalName());
        intent.putExtra(KEY_FRAGMENT_ARGS, args);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void start(Activity from, Class<?> fragmentClass, Bundle args, int screenOrientation, boolean isFullScreen) {
        if (Safeguard.ignorable(from))
            return;

        Intent intent = new Intent(from, GenericFragmentActivity.class);
        intent.putExtra(KEY_FRAGMENT_CLASS, fragmentClass.getCanonicalName());
        intent.putExtra(KEY_FRAGMENT_ARGS, args);
        intent.putExtra(KEY_SCREEN_ORIENTATION, screenOrientation);
        intent.putExtra(KEY_IS_FULL_SCREEN, isFullScreen);
        from.startActivity(intent);
    }

    public static void start(Fragment from, Class<?> fragmentClass, Bundle args) {
        if (Safeguard.ignorable(from))
            return;

        Intent intent = new Intent(from.getActivity(), GenericFragmentActivity.class);
        intent.putExtra(KEY_FRAGMENT_CLASS, fragmentClass.getCanonicalName());
        intent.putExtra(KEY_FRAGMENT_ARGS, args);
        from.startActivity(intent);
    }

    public static void start(Fragment from, Class<?> fragmentClass, Bundle args, int screenOrientation, boolean isFullScreen) {
        if (Safeguard.ignorable(from))
            return;

        Intent intent = new Intent(from.getActivity(), GenericFragmentActivity.class);
        intent.putExtra(KEY_FRAGMENT_CLASS, fragmentClass.getCanonicalName());
        intent.putExtra(KEY_FRAGMENT_ARGS, args);
        intent.putExtra(KEY_SCREEN_ORIENTATION, screenOrientation);
        intent.putExtra(KEY_IS_FULL_SCREEN, isFullScreen);
        from.startActivity(intent);
    }

    public static void startForResult(Activity from, int reqCode, Class<?> fragmentClass, Bundle args) {
        if (Safeguard.ignorable(from))
            return;

        Intent intent = new Intent(from, GenericFragmentActivity.class);
        intent.putExtra(KEY_FRAGMENT_CLASS, fragmentClass.getCanonicalName());
        intent.putExtra(KEY_FRAGMENT_ARGS, args);
        from.startActivityForResult(intent, reqCode);
    }

    public static void startForResult(Activity from, int reqCode, Class<?> fragmentClass, Bundle args, int screenOrientation, boolean isFullScreen) {
        if (Safeguard.ignorable(from))
            return;

        Intent intent = new Intent(from, GenericFragmentActivity.class);
        intent.putExtra(KEY_FRAGMENT_CLASS, fragmentClass.getCanonicalName());
        intent.putExtra(KEY_FRAGMENT_ARGS, args);
        intent.putExtra(KEY_SCREEN_ORIENTATION, screenOrientation);
        intent.putExtra(KEY_IS_FULL_SCREEN, isFullScreen);
        from.startActivityForResult(intent, reqCode);
    }

    public static void startForResult(Fragment frag, int reqCode, Class<?> fragmentClass, Bundle args) {
        if (Safeguard.ignorable(frag))
            return;

        Intent intent = new Intent(frag.getActivity(), GenericFragmentActivity.class);
        intent.putExtra(KEY_FRAGMENT_CLASS, fragmentClass.getCanonicalName());
        intent.putExtra(KEY_FRAGMENT_ARGS, args);
        frag.startActivityForResult(intent, reqCode);
    }

    public static void startForResult(Fragment frag, int reqCode, Class<?> fragmentClass, Bundle args, int screenOrientation, boolean isFullScreen) {
        if (Safeguard.ignorable(frag))
            return;

        Intent intent = new Intent(frag.getActivity(), GenericFragmentActivity.class);
        intent.putExtra(KEY_FRAGMENT_CLASS, fragmentClass.getCanonicalName());
        intent.putExtra(KEY_FRAGMENT_ARGS, args);
        intent.putExtra(KEY_SCREEN_ORIENTATION, screenOrientation);
        intent.putExtra(KEY_IS_FULL_SCREEN, isFullScreen);
        frag.startActivityForResult(intent, reqCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public Fragment getCurrentFragment() {
        return currentFragment;
    }

    @Override
    public void onBackPressed() {
        if(currentFragment != null && currentFragment instanceof BaseFragment){
            if(((BaseFragment) currentFragment).onBackPressed()){
                return;
            }
        }

        super.onBackPressed();
    }

    @Override
    protected String getPageName() {
        return null;
    }
}
