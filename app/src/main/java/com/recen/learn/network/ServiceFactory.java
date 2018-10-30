package com.recen.learn.network;

import com.recen.dotnetwork.NetworkManager;

public class ServiceFactory {
    private static AppService sAppService;
    public static AppService getAppService() {
        if (sAppService != null) {
            return sAppService;
        }
        sAppService = NetworkManager.getInstance().getRetrofit().create(AppService.class);
        return sAppService;
    }
}
