package com.recen.learn;

import android.databinding.ObservableArrayList;
import android.util.Log;

import com.recen.dotframe.impl.PageWrapper;
import com.recen.learn.common.PageName;
import com.recen.learn.util.TimeRemainingUtil;

import java.util.HashMap;

public class Main extends PageWrapper {
    public ObservableArrayList<ItemInfo> mItemInfo = new ObservableArrayList<>();
    public HashMap<String,PageWrapper> map = new HashMap<>();

    @Override
    protected void initData() {
        mItemInfo.clear();
        mItemInfo.add(new ItemInfo(PageName.Practice1Fragment,"基础-DataBinding",page));
        mItemInfo.add(new ItemInfo(PageName.Practice2Fragment,"列表",page));
        mItemInfo.add(new ItemInfo(PageName.Practice3Fragment,"弹框",page));
        mItemInfo.add(new ItemInfo(PageName.Practice4Fragment,"前台Service",page));

        TimeRemainingUtil.map.put("Main",this);
        Log.d("MainPage",this.toString());

    }


}
