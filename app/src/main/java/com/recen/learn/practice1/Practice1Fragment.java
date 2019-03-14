package com.recen.learn.practice1;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.recen.dotframe.impl.AbstractPageImpl;
import com.recen.dotframe.interfaces.ICallback;
import com.recen.learn.BR;
import com.recen.learn.R;
import com.recen.learn.base.BaseFragment;
import com.recen.learn.common.activity.GenericFragmentActivity;
import com.recen.learn.common.constants.BundleKey;
import com.recen.learn.databinding.FragmentPractice1Binding;
import com.recen.learn.download.update.Update;
import com.recen.learn.util.TimeRemainingUtil;

import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.OnItemBind;

public class Practice1Fragment extends BaseFragment {
    public static void go(Activity from, String pageName){
        Bundle bundle = new Bundle();
        bundle.putString(BundleKey.BUNDLE_PAGENAME,pageName);
        GenericFragmentActivity.start(from,Practice1Fragment.class,bundle);
    }

    public final OnItemBind<PracticeItem> itemViewSelector = new OnItemBind<PracticeItem>() {
        @Override
        public void onItemBind(ItemBinding itemBinding, int position, PracticeItem item) {
            itemBinding.set(BR.item,R.layout.item_practice);
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentPractice1Binding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_practice1,container,false);
        Practice1 practice1= ViewModelProviders.of(this).get(Practice1.class);
        practice1.init(new AbstractPageImpl(this) {
            @Override
            protected void goPage(String pageName, Object params, ICallback callback) {

            }

            @Override
            public void showDialog(int type, Object params, ICallback callback) {
                if (type == 1){
                    DetailDialog.show(wrActivity.get(),(String)params);
                }
            }
        });
        Log.d("MainPage",TimeRemainingUtil.map.get("Main").toString());
        binding.setPractice(practice1);
        binding.setItemView(itemViewSelector);
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Update.update(getActivity(),"更新下载","https://qd.myapp.com/myapp/qqteam/AndroidQQ/mobileqq_android.apk",R.mipmap.ic_launcher_round);
            }
        });

        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        return binding.getRoot();
    }
}
