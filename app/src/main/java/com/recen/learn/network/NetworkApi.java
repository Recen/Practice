package com.recen.learn.network;

import com.recen.learn.model.TopSearchData;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;

public class NetworkApi {
    public Observable<BaseResponse<List<TopSearchData>>> getTopSearchData() {
        return ServiceFactory.getAppService().getTopSearchData();
    }
}
