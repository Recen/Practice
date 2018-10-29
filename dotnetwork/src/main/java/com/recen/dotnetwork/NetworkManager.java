package com.recen.dotnetwork;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkManager {
    private String baseUrl = "https://api.douban.com/v2/movie/";
    private Retrofit retrofit;
    private static final class Generator {
        private static final NetworkManager INSTANCE = new NetworkManager();
    }
    public static NetworkManager getInstance() {
        return Generator.INSTANCE;
    }


    public NetworkManager() {
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(OkhttpClientInstance.getInstance())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public Retrofit getRetrofit(){
        return retrofit;
    }


}
