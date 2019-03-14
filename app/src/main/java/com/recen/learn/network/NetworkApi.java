package com.recen.learn.network;

import com.recen.learn.model.ReadData;
import com.recen.learn.model.TopSearchData;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;

public class NetworkApi {

    public static Observable<BaseResponse<List<ReadData>>> getXianDuData(int itemNum,int pageIndex) {
        return ServiceFactory.getAppService().getXianDuData(itemNum,pageIndex);
    }
}
