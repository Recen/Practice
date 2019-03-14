package com.recen.learn.practice4;


import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.recen.dotutil.FileUtil;
import com.recen.learn.R;
import com.recen.learn.base.BaseFragment;
import com.recen.learn.common.activity.GenericFragmentActivity;
import com.recen.learn.common.constants.BundleKey;
import com.recen.learn.practice3.Practice3Fragment;

import java.io.File;
import java.util.concurrent.ExecutionException;
//import com.tencent.bugly.crashreport.CrashReport;

/**
 * A simple {@link Fragment} subclass.
 */
public class Practice4Fragment extends BaseFragment {

    public static void go(Activity from, String pageName){
        Bundle bundle = new Bundle();
        bundle.putString(BundleKey.BUNDLE_PAGENAME,pageName);
        GenericFragmentActivity.start(from, Practice4Fragment.class,bundle);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_practice4, container, false);
        initView(view);
        return view;
    }

    private void initView(View view){
        Button start = view.findViewById(R.id.startService);
        Button start2 = view.findViewById(R.id.startService2);
        Button java = view.findViewById(R.id.java);
        Button nativeCrash = view.findViewById(R.id.nativeCrash);
        Button anr = view.findViewById(R.id.anr);
        Button stop = view.findViewById(R.id.stopService);


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        File file = null;
                        try {
                            file = Glide.with(getActivity())
                                    .load("http://seopic.699pic.com/photo/50035/0520.jpg_wh1200.jpg")
                                    .downloadOnly(240, 320)
                                    .get();
                            File target = new File(Environment.getExternalStorageDirectory().getPath()+File.separator+"pic.jpg");
                            FileUtil.copy(file, target);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
        start2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestOptions requestOptions = new RequestOptions();
                requestOptions.decode(byte[].class);
                Glide.with(getActivity())
                        .as(byte[].class)
                        .load("http://seopic.699pic.com/photo/50035/0520.jpg_wh1200.jpg")
                        .into(new SimpleTarget<byte[]>() {
                            @Override
                            public void onResourceReady(byte[] resource, Transition<? super byte[]> transition) {
                                File target = new File(Environment.getExternalStorageDirectory().getPath()+File.separator+"pic1.jpg");
                                FileUtil.copy(Environment.getExternalStorageDirectory().getPath()+File.separator+"pic1.jpg", resource);
                            }
                        });
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),MyForeGroundService.class);
                getActivity().stopService(intent);
            }
        });

    }

}
