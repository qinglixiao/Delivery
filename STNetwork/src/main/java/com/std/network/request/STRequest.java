package com.std.network.request;

import android.text.TextUtils;

import com.std.network.NetworkConfig;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import me.std.common.core.DataConvert;
import me.std.common.core.ThreadPool;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Description:
 * Author: lixiao
 * Job number: 1800838R
 * Create on: 2019-12-29.
 */
public class STRequest extends BaseRequest {
    Map<String, String> parameters;

    STRequest(Map<String, String> parameters, String url, Map<String, String> headers, STMethod method) {
        super();
        this.parameters = parameters;
        this.url = url;
        this.method = method;
        for (Map.Entry<String, String> header : headers.entrySet()) {
            requestBuilder.addHeader(header.getKey(), header.getValue());
        }
    }

    @Override
    public String buildUrlQuery() {
        String buildUrl = url();
        if (parameters != null && parameters.size() > 0) {
            buildUrl += "?";
            for (Map.Entry<String, String> entry : parameters.entrySet()) {
                String key_value = String.format("%s=%s", entry.getKey(), entry.getValue());
                buildUrl += key_value + "&";
            }
        }

        if (!TextUtils.isEmpty(buildUrl)) {
            if (!buildUrl.startsWith("http")) {
                buildUrl = NetworkConfig.getDomain() + buildUrl;
            }
            if (buildUrl.endsWith("&")) {
                buildUrl = buildUrl.substring(0, buildUrl.lastIndexOf("&"));
            }
        }
        return buildUrl;
    }

    public Call upload(File file, ProgressRequestBody.OnProgressListener onProgressListener) {
        requestBuilder.url(onBuildUrl())
                .post(new ProgressRequestBody(RequestBody.create(MediaType.parse("application/octet-stream"), file), onProgressListener));
        return execute(null);
    }

    public Call down(File file, ProgressRequestBody.OnProgressListener onProgressListener) throws FileNotFoundException {
        requestBuilder.get()
                .url(onBuildUrl());
        Request request = requestBuilder.build();
        Call c = okHttpClient.newCall(request);

        c.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handleError(e, null);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is = response.body().byteStream();
                long sum = 0L;
                //文件总大小
                final long total = response.body().contentLength();
                int len = 0;
                FileOutputStream fos = new FileOutputStream(file);
                byte[] buf = new byte[128];

                while ((len = is.read(buf)) != -1) {
                    fos.write(buf, 0, len);
                    //每次递增
                    sum += len;

                    final long finalSum = sum;
                    ThreadPool.post(new Runnable() {
                        @Override
                        public void run() {
                            //将进度设置到TextView中
                            if (onProgressListener != null) {
                                onProgressListener.onProgress(finalSum, total);
                            }
                        }
                    });
                }
                fos.flush();
                fos.close();
                is.close();
            }
        });
        return c;
    }

    @Override
    Map<String, String> getPostData() {
        return parameters;
    }

    protected void parseResult(String response, NetCallBack callBack) {
        Result result = new Result();
        if (clazz == String.class) {
            result.data = response;
        } else {
            result.data = DataConvert.fromJson(response, clazz);
        }
        callBack.onResult(result, null);
    }

    public static class Builder {
        Map<String, String> parameters = new HashMap<>();
        Map<String, String> headers = new HashMap<>();
        String url;
        STMethod method;

        public Builder() {
        }

        public Builder(String url) {
            url(url);
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder addHeader(String name, String value) {
            headers.put(name, value);
            return this;
        }

        public Builder addParameter(String name, String value) {
            parameters.put(name, value);
            return this;
        }

        public Builder setParameters(Map<String, String> parameters) {
            this.parameters = parameters;
            return this;
        }

        public Builder method(STMethod method) {
            this.method = method;
            return this;
        }

        public STRequest build() {
            return new STRequest(parameters, url, headers, method);
        }
    }
}
