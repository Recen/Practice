package com.recen.learn.practice2;

import android.databinding.ObservableArrayList;

import com.recen.dotframe.impl.PageWrapper;
import com.recen.dotframe.interfaces.RepoCallback;
import com.recen.learn.common.util.PageManager;
import com.recen.learn.common.util.PageManagerBase;
import com.recen.learn.model.ReadData;
import com.recen.learn.network.ServiceException;
import com.recen.learn.practice1.PracticeItem;

import java.util.ArrayList;
import java.util.List;

public class Practice2 extends PageWrapper<Practice2Repository> {
    public ObservableArrayList<PracticeItem> dataList = new ObservableArrayList<>();

    @Override
    protected void initData() {
    }

    public PageManager.PageLoadListener pageLoadListener = new PageManager.PageLoadListener<List<ReadData>>() {
        @Override
        public void pageLoad(boolean isRefresh, int pageNo, int itemNum, final PageManagerBase.PageLoadCallback callback) {
            mRepository.getData(itemNum,pageNo,new RepoCallback<List<ReadData>>() {
                @Override
                public void onSuccess(List<ReadData> data) {
                    ArrayList<PracticeItem> list = new ArrayList<>();
                    for (ReadData item : data){
                        list.add(new PracticeItem(item,page));
                    }
                    callback.onSuccess(list);
                }

                @Override
                public void onFailure() {
                    callback.onFailed(new ServiceException());
                }
            });
        }
    };
}
