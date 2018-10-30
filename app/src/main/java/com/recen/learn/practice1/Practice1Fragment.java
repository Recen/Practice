package com.recen.learn.practice1;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.recen.dotframe.impl.AbstractPageImpl;
import com.recen.dotframe.interfaces.ICallback;
import com.recen.learn.R;
import com.recen.learn.base.BaseFragment;
import com.recen.learn.common.activity.GenericFragmentActivity;
import com.recen.learn.common.constants.BundleKey;
import com.recen.learn.databinding.FragmentPractice1Binding;

public class Practice1Fragment extends BaseFragment {
    public static void go(Activity from, String pageName){
        Bundle bundle = new Bundle();
        bundle.putString(BundleKey.BUNDLE_PAGENAME,pageName);
        GenericFragmentActivity.start(from,Practice1Fragment.class,bundle);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentPractice1Binding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_practice1,container,false);

        Practice1 practice1 = ViewModelProviders.of(this).get(Practice1.class);
        practice1.init(new AbstractPageImpl(this) {
            @Override
            protected void goPage(String pageName, Object params, ICallback callback) {

            }
        });
        binding.setPractice(practice1);
        return binding.getRoot();
    }


}
