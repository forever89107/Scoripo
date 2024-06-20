package com.my.common.http;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Map;

@Slf4j
@Component
public class OkHttpUtil {

    @Resource
    private OkHttpClient okHttpClient;

    public String post(final String url, final Map<String, String> params, final Map<String, String> headers) {
        FormBody.Builder bodyBuilder = new FormBody.Builder();
        if (!CollectionUtils.isEmpty(params)) {
            params.forEach(bodyBuilder::add);
        }
        Headers.Builder headerBuilder = new Headers.Builder();
        if (!CollectionUtils.isEmpty(headers)) {
            headers.forEach(headerBuilder::add);
        }
        return post(url, bodyBuilder.build(), headerBuilder.build());
    }

    public String post(final String url, final Map<String, String> params) {
        FormBody.Builder bodyBuilder = new FormBody.Builder();
        if (!CollectionUtils.isEmpty(params)) {
            params.forEach(bodyBuilder::add);
        }
        Headers.Builder headerBuilder = new Headers.Builder();
        return post(url, bodyBuilder.build(), headerBuilder.build());
    }

    public String postJSON(final String url, final String jsonParams, final Map<String, String> headers) {
        Headers.Builder headerBuilder = new Headers.Builder();
        if (!CollectionUtils.isEmpty(headers)) {
            headers.forEach(headerBuilder::add);
        }
        RequestBody body = RequestBody.create(jsonParams, MediaType.parse("application/json; charset=utf-8"));
        Request request = new Request.Builder().url(url).headers(headerBuilder.build()).post(body).build();
        return executeCall(request);
    }

    private String post(final String url, FormBody params, Headers headers) {
        Request request = new Request.Builder().url(url).post(params).headers(headers).build();
        return executeCall(request);
    }

    private String executeCall(Request request) {
        Response response = null;
        String result = null;
        try {
            response = okHttpClient.newCall(request).execute();
            assert response.body() != null;
            result = response.body().string();
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("OKHttpUtil.executeCall()", ex);
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return result;
    }
}
