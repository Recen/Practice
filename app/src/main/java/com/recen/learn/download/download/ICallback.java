package com.recen.learn.download.download;

public interface ICallback {
    void onResponse(Request request, Response response);
    void onFailure(Request request, Exception e);
}
