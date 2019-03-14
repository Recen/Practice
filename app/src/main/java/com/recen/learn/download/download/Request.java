package com.recen.learn.download.download;

import java.util.Map;

public class Request {
    private Object tag;
    private String url;
    private String method;
    private Map<String, String> headers;
    private String mediaType;
    private byte[] data;

    public Request(Object tag, String url, Map<String, String> headers) {
        this.tag = tag;
        this.url = url;
        this.headers = headers;
    }

    public Request(Object tag, String url, Map<String, String> headers, String mediaType, byte[] data) {
        this.tag = tag;
        this.url = url;
        this.headers = headers;
        this.mediaType = mediaType;
        this.data = data;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Object getTag() {
        return tag;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }
}
