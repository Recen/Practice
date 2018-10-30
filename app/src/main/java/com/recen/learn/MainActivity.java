package com.recen.learn;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.recen.dotframe.impl.AbstractPageImpl;
import com.recen.dotframe.interfaces.ICallback;
import com.recen.learn.base.BaseActivity;
import com.recen.learn.common.PageName;
import com.recen.learn.databinding.ActivityMainBinding;
import com.recen.learn.practice1.Practice1Fragment;

import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.OnItemBind;

public class MainActivity extends BaseActivity {

    public final OnItemBind<ItemInfo> multipleItems = new OnItemBind<ItemInfo>() {
        @Override
        public void onItemBind(ItemBinding itemBinding, int position, ItemInfo item) {
            itemBinding.set(BR.item,R.layout.item_main_list);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        Main main = ViewModelProviders.of(this).get(Main.class);
        main.init(new AbstractPageImpl(this) {
            @Override
            protected void goPage(String pageName, Object params, ICallback callback) {
                switch (pageName){
                    case PageName.Practice1Fragment:
                        Practice1Fragment.go(wrActivity.get(),(String)params);
                        break;
                }
            }
        });
        binding.setMain(main);
        binding.setItemview(multipleItems);
    }
}
