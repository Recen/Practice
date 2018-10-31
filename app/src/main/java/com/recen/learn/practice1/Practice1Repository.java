package com.recen.learn.practice1;

import com.recen.learn.base.BaseRepository;
import com.recen.learn.model.TopSearchData;
import com.recen.learn.network.BaseResponse;
import com.recen.learn.network.NetworkApi;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Practice1Repository extends BaseRepository {

    public void getData() {
//        addSubscribe(NetworkApi.getTopSearchData()
//                .subscribeOn(Schedulers.io()) //2.请求在IO线程
//                .observeOn(AndroidSchedulers.mainThread())  //3观察处理数据在主线程
//                .subscribe(new Observer<BaseResponse<List<TopSearchData>>>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(BaseResponse<List<TopSearchData>> listBaseResponse) {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                }));
    }
}
