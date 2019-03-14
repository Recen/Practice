package com.recen.learn.practice1;

import android.util.Log;

import com.recen.dotframe.interfaces.RepoCallback;
import com.recen.learn.base.BaseRepository;
import com.recen.learn.model.ReadData;
import com.recen.learn.model.TopSearchData;
import com.recen.learn.network.BaseResponse;
import com.recen.learn.network.NetworkApi;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class Practice1Repository extends BaseRepository {
    private List<ReadData> dataList = new ArrayList<>();
    public void getData(final RepoCallback<List<ReadData>> callback) {
        addSubscribe(NetworkApi.getXianDuData(10,1)
                .subscribeOn(Schedulers.io()) //2.请求在IO线程
                .observeOn(AndroidSchedulers.mainThread())  //3观察处理数据在主线程
                .subscribe(new Consumer<BaseResponse<List<ReadData>>>() {
                    @Override
                    public void accept(BaseResponse<List<ReadData>> response) throws Exception {
                        dataList = response.getResults();
                        callback.onSuccess(dataList);
                        if (dataList != null)
                        Log.d("Practice","size =  " +dataList.size());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (dataList != null)
                        Log.d("Practice","size =  " +dataList.size());
                    }
                }));
    }
}
