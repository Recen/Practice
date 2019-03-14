package com.recen.learn.network;

import com.recen.learn.model.ReadData;
import com.recen.learn.model.TopSearchData;

import java.util.List;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface AppService {

    /**
     * 热搜
     * http://www.wanandroid.com//hotkey/json
     *
     * @return 热门搜索数据
     */

    @GET("xiandu/data/id/appinn/count/{pageNum}/page/{pageIndex}")
    @Headers("Cache-Control: public, max-age=36000")
    Observable<BaseResponse<List<ReadData>>> getXianDuData(@Path("pageNum") int pageNum,@Path("pageIndex") int pageIndex);

}
