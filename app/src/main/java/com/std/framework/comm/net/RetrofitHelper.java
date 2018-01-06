package com.std.framework.comm.net;

import com.std.framework.comm.net.basic.ToStringConverterFactory;
import com.std.framework.core.Logger;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
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
    private static final int TIMEOUT = 6;//second
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
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(createClient())
                .addConverterFactory(new ToStringConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

//        if (type == 0) {
//            return new Retrofit.Builder()
//                    .baseUrl(baseUrl)
//                    .client(createClient())
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                    .build();
//        } else if (type == 1) {
//            return new Retrofit.Builder()
//                    .baseUrl(baseUrl)
//                    .client(createClient())
//                    .addConverterFactory(new ToStringConverterFactory())
//                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                    .build();
//        } else {
//            throw new IllegalArgumentException("RetrofitHelper === not set return type");
//        }
    }

    private OkHttpClient createClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(new HeaderInterceptor())
                .addInterceptor(new HostSelectionInterceptor())
                .addInterceptor(logging)
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);

        return builder.build();
    }

    //为请求添加header
    class HeaderInterceptor implements Interceptor {

        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request request = chain.request().newBuilder()
                    .addHeader("Accept-Encoding", "gzip")
                    .addHeader("Accept", "application/json")
                    .addHeader("X-Toon-User-ID", "305212")
                    .addHeader("X-Toon-User-Token", "5f2d37cd-33c4-4b43-9a19-1c36b7d7348b")
                    .addHeader("X-Toon-User-Agent", "platform:android,deviceId:FFK0217705003490,appVersion:2.0.0,platformVersion:24,toonType:102")
                    .build();
            return chain.proceed(request);
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
            String header = request.headers().toString();
            String url = request.url().toString();

            Logger.i("request",
                    "=========network========="
                            + "\n" +

                            "header " + header + "\n" +
                            "url " + url + "\n" +

                            "==========network========="
            );

            String host = this.host;
            if (host != null) {
                HttpUrl newUrl = request.url().newBuilder()
                        .host(host)
                        .build();
                request = request.newBuilder()
                        .url(newUrl)
                        .build();
            }
            okhttp3.Response response =  chain.proceed(request);

            return response;
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
