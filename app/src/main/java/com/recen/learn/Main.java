package com.recen.learn;

import android.databinding.ObservableArrayList;

import com.recen.dotframe.impl.PageWrapper;
import com.recen.learn.common.PageName;

public class Main extends PageWrapper {
    public ObservableArrayList<ItemInfo> mItemInfo = new ObservableArrayList<>();

    @Override
    protected void initData() {
        mItemInfo.clear();
        mItemInfo.add(new ItemInfo(PageName.Practice1Fragment,"基础-DataBinding",page));
        mItemInfo.add(new ItemInfo(PageName.Practice2Fragment,"列表",page));
        mItemInfo.add(new ItemInfo(PageName.Practice3Fragment,"弹框",page));
    }
}
