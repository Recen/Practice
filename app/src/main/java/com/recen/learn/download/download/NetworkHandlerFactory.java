package com.recen.learn.download.download;

public class NetworkHandlerFactory {
    public static INetworkHandler createHandler(){
        return new OkHttpHandler();
    }
}
