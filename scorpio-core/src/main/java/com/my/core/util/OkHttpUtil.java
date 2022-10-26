package com.my.core.util;

import okhttp3.*;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("unused")
public class OkHttpUtil {
    private static final String serverIp = "http://localhost";
    private static final String serverPort = "6666";
    /**
     * 請求的timeout時間
     */
    private static final int REQUEST_TIME_OUT_TIME = 5;

    /**
     * MediaType
     */
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    /**
     * Content_Type
     */
    public static final String Content_Type = "application/json;charset=utf-8";

    public Response request_Post(String urn, String token, String jsonBody) throws IOException {
        OkHttpClient client = clientBuilder();
        //Url
        String url = pathBuilder();
        url += urn;
        HttpUrl httpUrl = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder().build();
        //組header
        Headers headers = headerBuilder(token);
        //body
        RequestBody body = RequestBody.Companion.create(jsonBody, JSON);

        Request request = new Request.Builder().url(httpUrl).headers(headers).post(body).build();
        return client.newCall(request).execute();
    }

    public Response request_Get(String urn, String token) throws IOException {
        OkHttpClient client = clientBuilder();
        //Url
        String url = pathBuilder();
        url += urn;
        HttpUrl httpUrl = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder().build();
        //header
        Headers headers = headerBuilder(token);

        Request request = new Request.Builder().url(httpUrl).headers(headers).get().build();

        return client.newCall(request).execute();
    }

    public Response request_Put(String urn, String token, String jsonBody) throws IOException {
        OkHttpClient client = clientBuilder();
        //Url
        String url = pathBuilder();
        url += urn;
        HttpUrl httpUrl = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder().build();
        //組header
        Headers headers = headerBuilder(token);
        //body
        RequestBody body = RequestBody.Companion.create(jsonBody, JSON);

        Request request = new Request.Builder().url(httpUrl).headers(headers).put(body).build();
        return client.newCall(request).execute();
    }

    private OkHttpClient clientBuilder() {
        return new OkHttpClient.Builder().
                readTimeout(REQUEST_TIME_OUT_TIME, TimeUnit.SECONDS)
                .connectTimeout(REQUEST_TIME_OUT_TIME, TimeUnit.SECONDS)
                .writeTimeout(REQUEST_TIME_OUT_TIME, TimeUnit.SECONDS)
                .build();
    }


    private Headers headerBuilder(String token) {
        return (EmptyUtil.stringIsNotEmpty(token))
                ? new Headers.Builder()
                .add("Content-Type", Content_Type)
                .add("Authorization", "Bearer " + token)
                .build()

                : new Headers.Builder()
                .add("Content-Type", Content_Type)
                .build()
                ;

    }

    private String pathBuilder() {
        return serverIp + ":" + serverPort;
    }
}
