package com.recen.learn.practice2;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.recen.learn.common.util.PageManager;
import com.recen.learn.databinding.FragmentPractice1Binding;
import com.recen.learn.databinding.FragmentPractice2Binding;
import com.recen.learn.practice1.DetailDialog;
import com.recen.learn.practice1.Practice1;
import com.recen.learn.practice1.PracticeItem;

import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.OnItemBind;

public class Practice2Fragment extends BaseFragment {
    public static void go(Activity from, String pageName){
        Bundle bundle = new Bundle();
        bundle.putString(BundleKey.BUNDLE_PAGENAME,pageName);
        GenericFragmentActivity.start(from, Practice2Fragment.class,bundle);
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
        FragmentPractice2Binding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_practice2,container,false);
        Practice2 practice2= ViewModelProviders.of(this).get(Practice2.class);
        practice2.init(new AbstractPageImpl(this) {
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
        binding.setPractice(practice2);
        binding.setItemView(itemViewSelector);
        PageManager manager = new PageManager<>(1, 10, practice2.dataList);
        manager.setPageLoadListener(practice2.pageLoadListener);
        binding.setPageManager(manager);
        manager.loadFirstPage();

        return binding.getRoot();
    }
}
