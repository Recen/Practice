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
import com.recen.learn.practice2.Practice2Fragment;
import com.recen.learn.practice3.Practice3Fragment;
import com.recen.learn.practice4.Practice4Fragment;

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
                if (PageName.Practice1Fragment.equals(pageName)){
                    Practice1Fragment.go(wrActivity.get(),(String)params);
                }else if (PageName.Practice2Fragment.equals(pageName)){
                    Practice2Fragment.go(wrActivity.get(),(String)params);
                }else if (PageName.Practice3Fragment.equals(pageName)){
                    Practice3Fragment.go(wrActivity.get(),(String)params);
                }else if (PageName.Practice4Fragment.equals(pageName)){
                    Practice4Fragment.go(wrActivity.get(),(String)params);
                }
            }
        });
        binding.setMain(main);
        binding.setItemview(multipleItems);
    }
}
