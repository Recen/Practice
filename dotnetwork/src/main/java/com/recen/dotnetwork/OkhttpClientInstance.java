package com.recen.dotnetwork;

import okhttp3.OkHttpClient;

public class OkhttpClientInstance {

    private static final class Generator{
        private static final OkHttpClient INSTANCE = createOkHttpClient();
    }

    public static OkHttpClient getInstance(){
        return Generator.INSTANCE;
    }

    private static OkHttpClient createOkHttpClient(){
        OkHttpClient okHttpClient = new OkHttpClient();
        return okHttpClient;
    }
}
