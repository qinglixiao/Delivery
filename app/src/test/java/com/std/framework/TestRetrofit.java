package com.std.framework;

import android.os.Looper;

import com.std.framework.assist.JunitUtil;
import com.std.framework.comm.net.basic.ToStringConverterFactory;

import org.junit.Test;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
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

    interface Sample {
//        @GET("top250")
//        Call<String> getTopMovieString(@Query("start") int start, @Query("count") int count);

        @GET("top250")
        Call<MoveEntity> getTopMovie(@Query("start") int start, @Query("count") int count);

        @GET("top250")
        Observable<String> getTopMovieString(@Query("start") int start, @Query("count") int count);
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
//        getSample().getTopMovieString(0, 10).enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                JunitUtil.log(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//
//            }
//        });
//        Thread.sleep(3000);
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
            builder.addHeader("name", "value");


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
}
