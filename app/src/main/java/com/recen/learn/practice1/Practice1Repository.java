package com.recen.learn.practice1;

import android.util.Log;

import com.recen.learn.base.BaseRepository;
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
    private List<TopSearchData> topSearchDataList = new ArrayList<>();
    public void getData() {
        addSubscribe(NetworkApi.getTopSearchData()
                .subscribeOn(Schedulers.io()) //2.请求在IO线程
                .observeOn(AndroidSchedulers.mainThread())  //3观察处理数据在主线程
                .subscribe(new Consumer<BaseResponse<List<TopSearchData>>>() {
                    @Override
                    public void accept(BaseResponse<List<TopSearchData>> response) throws Exception {
                        topSearchDataList = response.getData();
                        Log.d("Practice","size =  " +topSearchDataList.size());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("Practice","size =  " +topSearchDataList.size());
                    }
                }));
    }
}
