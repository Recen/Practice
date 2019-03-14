package com.recen.learn.download.download;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.ResponseBody;

public class OkHttpInputStream extends WrapperInputStream {
    private ResponseBody responseBody;

    public OkHttpInputStream(InputStream inputStream, ResponseBody responseBody) {
        super(inputStream);
        this.responseBody = responseBody;
    }

    @Override
    public void close() throws IOException {
        super.close();
        responseBody.close();
    }
}

