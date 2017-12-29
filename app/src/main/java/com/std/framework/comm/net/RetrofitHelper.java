package com.std.framework.comm.net;

import com.std.framework.comm.net.basic.ToStringConverterFactory;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Description:
 * Created by 李晓 ON 2017/12/20.
 * Job number:137289
 * Phone:18611867932
 * Email:lixiao3@syswin.com
 * Person in charge:李晓
 * Leader: 李晓
 */
public class RetrofitHelper {
    private int type = 0; // 0:Json  1:String
    private String baseUrl;

    private RetrofitHelper() {
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Retrofit get() {
        if (type == 0) {
            return new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        } else if (type == 1) {
            return new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(new ToStringConverterFactory())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        } else {
            throw new IllegalArgumentException("RetrofitHelper === not set return type");
        }
    }

    //为请求添加header
    class HeaderInterceptor implements Interceptor {

        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Request.Builder builder = request.newBuilder();
            builder.headers(buildHeader());
//            Map<String, String> headers = buildHeader();
//            for (final String name : headers.keySet()) {
//                if (headers.get(name) == null) {
//                    continue;
//                }
//                builder.addHeader(name, headers.get(name));
//            }
            return chain.proceed(request);
        }

//        public Map<String, String> buildHeader() {
//            Map<String, String> header = new HashMap<>();
//            header.put("Accept-Encoding", "gzip");
//            header.put("Accept", "application/json");
//            header.put("X-Toon-User-ID", "");
//            header.put("X-Toon-User-Token", "aebf1421-3191-4b3b-b7b9-8fa61e2def8f");
//            StringBuilder userAgent = new StringBuilder("platform:");
//            userAgent.append("android,").append("deviceId:")
//                    .append(",").append("platformVersion:").append(android.os.Build.VERSION.SDK_INT)
//                    .append(",").append("toonType:").append("102");
//            header.put("X-Toon-User-Agent", "platform:android,deviceId:FFK0217705003490,appVersion:1.8.0,platformVersion:24,toonType:102");
//            return header;
//        }

        public Headers buildHeader() {
            return Headers.of(
                    "Accept-Encoding", "gzip",
                    "Accept", "application/json",
                    "X-Toon-User-ID", "",
                    "X-Toon-User-Token", "aebf1421-3191-4b3b-b7b9-8fa61e2def8f",
                    "X-Toon-User-Agent", "platform:android,deviceId:FFK0217705003490,appVersion:1.8.0,platformVersion:24,toonType:102"
            );
        }

    }

    //动态改变baseUrl
    class HostSelectionInterceptor implements Interceptor {
        private volatile String host;

        public void setHost(String host) {
            this.host = host;
        }

        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            String host = this.host;
            if (host != null) {
                HttpUrl newUrl = request.url().newBuilder()
                        .host(host)
                        .build();
                request = request.newBuilder()
                        .url(newUrl)
                        .build();
            }
            return chain.proceed(request);
        }
    }

    public static class Builder {
        private int type;
        private String baseUrl;

        public Builder withJson() {
            type = 0;
            return this;
        }

        public Builder withString() {
            type = 1;
            return this;
        }

        public Builder baseUrl(String url) {
            this.baseUrl = url;
            return this;
        }

        public RetrofitHelper build() {
            RetrofitHelper helper = new RetrofitHelper();
            helper.setType(type);
            helper.setBaseUrl(baseUrl);
            return helper;
        }
    }
}
