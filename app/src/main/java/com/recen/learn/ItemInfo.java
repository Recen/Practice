package com.recen.learn;

import android.databinding.ObservableField;

import com.recen.dotframe.interfaces.IPage;

public class ItemInfo {
    private IPage page;
    private String mFragment;
    public ObservableField<String> name = new ObservableField<>();

    public ItemInfo(String mFragment,String mFragmentName,IPage page) {
        this.name.set(mFragmentName);
        this.page = page;
        this.mFragment = mFragment;
    }

    public void itemClick(){
        page.go(mFragment,name.get(),null);
    }
}
