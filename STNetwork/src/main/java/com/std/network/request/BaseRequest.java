package com.std.network.request;

import com.std.network.NetworkConfig;

import java.io.IOException;
import java.util.Map;

import me.std.common.core.ThreadPool;
import me.std.common.utils.Logger;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Description:
 * Author: lixiao
 * Create on: 2019-12-30.
 */
public abstract class BaseRequest {
    public static final String TAG = "net";
    protected OkHttpClient okHttpClient;
    protected Request.Builder requestBuilder;
    protected String url;
    protected Class<?> clazz;

    public BaseRequest() {
        okHttpClient = NetworkConfig.httpClient.get();
        requestBuilder = new Request.Builder();
        addHeader(requestBuilder);
    }

    private void addHeader(Request.Builder requestBuilder) {
        requestBuilder
                .header("Accept", "application/json")
                .header("X-Toon-User-Token", NetworkConfig.getUserToken())
                .header("X-Toon-User-Agent", NetworkConfig.getUserAgent());

    }

    public String url() {
        return url;
    }

    public <T> Call get(NetCallBack<T> netCallBack, Class<?> clazz) {
        this.clazz = clazz;
        requestBuilder.get()
                .url(onBuildUrl());
        return execute(netCallBack);
    }

    public Call post(NetCallBack netCallBack, Class<?> clazz) {
        this.clazz = clazz;
        FormBody.Builder formBody = new FormBody.Builder();
        Map<String, String> data = getPostData();
        if (data != null) {
            for (Map.Entry<String, String> entry : data.entrySet()) {
                formBody.add(entry.getKey(), entry.getValue());
            }
        }
        requestBuilder.url(onBuildUrl())
                .post(formBody.build());
        return execute(netCallBack);
    }

    protected Call execute(NetCallBack netCallBack) {
        Request request = requestBuilder.build();
        Call c = okHttpClient.newCall(request);

        Logger.i(TAG, String.format("request【%s %s】", request.method(), url));
        c.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handleError(e, netCallBack);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                onParseResponse(response, netCallBack);
            }
        });
        return c;
    }

    protected String onBuildUrl() {
        return buildUrlQuery();
    }

    protected void onParseResponse(Response response, NetCallBack callBack) {
        try {
            String data = response.body().string();
            ThreadPool.post(new Runnable() {
                @Override
                public void run() {
                    if (response.isSuccessful()) {
                        Logger.i(TAG, String.format("【%s】【%s %s】%s", response.code(), response.request().method(), url(), data));
                        try {
                            parseResult(data, callBack);
                        } catch (Exception ex) {
                            handleError(ex, callBack);
                        }

                    } else {
                        Logger.e(TAG, String.format("【%s】【%s %s】%s", response.code(), response.request().method(), url(), data));
                        callBack.onResult(null, new Error(response.message()));
                    }
                }
            });
        } catch (Exception ex) {
            handleError(ex, callBack);
        }
    }

    protected void handleError(Exception e, NetCallBack callBack) {
        if (callBack != null) {
            Logger.e(TAG, String.format("【ERROR %s】%s", url, e.getMessage()));
            ThreadPool.post(new Runnable() {
                @Override
                public void run() {
                    callBack.onResult(null, new Error(e));
                }
            });
        }
    }

    abstract void parseResult(String response, NetCallBack callBack);

    abstract String buildUrlQuery();

    abstract Map<String, String> getPostData();
}
