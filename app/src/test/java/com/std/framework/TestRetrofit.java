package com.std.framework;

import android.os.Looper;

import com.std.framework.assist.JunitUtil;
import com.std.framework.business.user.model.UserModel;
import com.std.framework.comm.net.basic.ToStringConverterFactory;
import com.std.framework.util.SharedPreferencesUtil;

import org.junit.Test;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;
import rx.Scheduler;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Description:
 * Created by 李晓 ON 2017/11/20.
 * Job number:137289
 * Phone:18611867932
 * Email:lixiao3@syswin.com
 * Person in charge:李晓
 * Leader: 李晓
 */
public class TestRetrofit {
    String url = "https://api.douban.com/v2/movie/";
    String toon = "api.app.systoon.com";

    interface Sample {
//        @GET("top250")
//        Call<String> getTopMovieString(@Query("start") int start, @Query("count") int count);

        @GET("top250")
        Call<MoveEntity> getTopMovie(@Query("start") int start, @Query("count") int count);

        @GET("top250")
        Observable<String> getTopMovieString(@Query("start") int start, @Query("count") int count);

        @POST("/user/generateCypherTextForBJToon")
        Observable<String> generate(@Body TNPSecretKeyForBJInput input);
    }

    private Sample getSample() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(createClient())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(new ToStringConverterFactory())
                .build();

        return retrofit.create(Sample.class);
    }


    @Test
    public void testInit() throws IOException, InterruptedException {
        getSample().getTopMovieString(0, 10)
                .subscribe(new Action1<String>() {
                               @Override
                               public void call(String s) {
                                   JunitUtil.log(s);
                               }
                           }
                );
        Thread.sleep(3000);
    }

    @Test
    public void testRxJavaRequest() {
        getSample().getTopMovieString(0, 10)
                .subscribeOn(Schedulers.io())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        JunitUtil.log(s);
                        JunitUtil.log("main:" + (Looper.myLooper() == Looper.getMainLooper() ? "true" : "false"));
                    }
                });
        JunitUtil.sleep(3000);
    }

    @Test
    public void testJson() throws InterruptedException {
//        getSample().getTopMovie(0, 10).enqueue(new Callback<MoveEntity>() {
//            @Override
//            public void onResponse(Call<MoveEntity> call, Response<MoveEntity> response) {
//                JunitUtil.log(response.body() == null ? "null" : response.body().title);
//            }
//
//            @Override
//            public void onFailure(Call<MoveEntity> call, Throwable t) {
//
//            }
//        });
//        Thread.sleep(3000);
    }

    @Test
    public void testUserModel() {
    }

    class MoveEntity {
        String count;
        String start;
        String total;
        String title;
    }

    //为请求添加header
    class HeaderInterceptor implements Interceptor {

        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Request.Builder builder = request.newBuilder();
            Map<String, String> headers = buildHeader();
            for (final String name : headers.keySet()) {
                if (headers.get(name) == null) {
                    continue;
                }
                builder.addHeader(name, headers.get(name));
            }
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

    private OkHttpClient createClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(new HeaderInterceptor())
                .addInterceptor(new HostSelectionInterceptor());
        return builder.build();
    }

    public static Map<String, String> buildHeader() {
        Map<String, String> header = new HashMap<>();
        header.put("Accept-Encoding", "gzip");
        header.put("Accept", "application/json");
        header.put("X-Toon-User-ID", "");
        header.put("X-Toon-User-Token", "aebf1421-3191-4b3b-b7b9-8fa61e2def8f");
        StringBuilder userAgent = new StringBuilder("platform:");
        userAgent.append("android,").append("deviceId:")
                .append(",").append("platformVersion:").append(android.os.Build.VERSION.SDK_INT)
                .append(",").append("toonType:").append("102");
        header.put("X-Toon-User-Agent", "platform:android,deviceId:FFK0217705003490,appVersion:1.8.0,platformVersion:24,toonType:102");
        return header;
    }

    class TNPSecretKeyForBJInput implements Serializable {
        /**
         * 应用id
         */
        private String appId;

        /**
         * 要加密的字符串
         */
        private String toonNo;

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getToonNo() {
            return toonNo;
        }

        public void setToonNo(String toonNo) {
            this.toonNo = toonNo;
        }
    }
}
