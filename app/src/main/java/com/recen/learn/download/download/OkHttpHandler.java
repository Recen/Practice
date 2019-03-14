package com.recen.learn.download.download;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Created by liuxiaofeng02 on 2016/9/15.
 */
public class OkHttpHandler implements INetworkHandler {
    private OkHttpClient client;

    public OkHttpHandler() {
        this.client = OkHttpClientInstance.getInstance();
    }

    @Override
    public void asyncRequest(final Request request, final ICallback callback) {
        okhttp3.Request.Builder builder = createOkHttpBuilder(request);

        okhttp3.Request okHttpRequest = builder.build();
        client.newCall(okHttpRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure(request, e);
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                ResponseBody responseBody = response.body();
                Response result = new Response(response.code(), responseBody.contentLength(), responseBody.byteStream());
                callback.onResponse(request, result);
            }
        });
    }

    @Override
    public Response synRequest(Request request) {
        try{
            okhttp3.Request.Builder builder = createOkHttpBuilder(request);

            okhttp3.Request okHttpRequest = builder.build();
            okhttp3.Response response = client.newCall(okHttpRequest).execute();
            boolean fromCache = response.cacheResponse() != null;

            ResponseBody responseBody = response.body();
            Response result = new Response(response.code(), responseBody.contentLength(), new OkHttpInputStream(responseBody.byteStream(), responseBody));
            result.setCached(fromCache);
            return result;
        }catch (IOException e){
            e.printStackTrace();
        }

        return null;
    }

    private okhttp3.Request.Builder createOkHttpBuilder(Request request){
        okhttp3.Request.Builder builder = new okhttp3.Request.Builder().url(request.getUrl());
        if(request.getTag() != null){
            builder.tag(request.getTag());
        }

        //add headers
        Map<String, String> headers = request.getHeaders();
        if (headers != null) {
            Set<Map.Entry<String, String>> entrySet = headers.entrySet();
            for (Iterator<Map.Entry<String, String>> iterator = entrySet.iterator(); iterator.hasNext(); ) {
                Map.Entry<String, String> it = iterator.next();
                builder.header(it.getKey(), it.getValue());
            }
        }

        if(request.getMediaType() != null && request.getData() != null){
            MediaType mediaType = MediaType.parse(request.getMediaType());
            if(request.getMediaType().startsWith("image")){

//                MultipartBody body = new MultipartBody.Builder("AaB03x")
//                        .setType(MultipartBody.FORM)
//                        .addFormDataPart("files", null, new MultipartBody.Builder("BbC04y")
//                                .addPart(Headers.of("Content-Disposition", "form-data; filename=\"img.png\""),
//                                        RequestBody.create(MediaType.parse("image/png"), new File(path)))
//                                .build())
//                        .build();
//                //upload image
//                MultipartBuilder multipartBuilder = new MultipartBuilder().type(MultipartBuilder.FORM);
//                multipartBuilder.addPart(Headers.of("Content-Disposition", "form-data; name=\"file\"; filename=\"a.jpg\""),
//                        RequestBody.create(mediaType, request.getData()));
//                RequestBody requestBody = multipartBuilder.build();
//                builder.post(requestBody);
            } else {
                RequestBody requestBody = RequestBody.create(mediaType, request.getData());
                builder.post(requestBody);
            }
        }

        return builder;
    }

    @Override
    public void cancel(Object tag) {

    }
}

