package com.recen.learn.download.download;

public interface INetworkHandler {
    Response synRequest(Request request);
    void asyncRequest(Request request, ICallback callback);
    void cancel(Object tag);
}
