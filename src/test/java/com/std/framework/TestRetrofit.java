package com.std.framework;

import com.std.framework.assist.JunitUtil;

import org.junit.Test;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/6/11.
 */
public class TestRetrofit {
    String url = "https://api.douban.com/v2/movie/";

    interface Sample {
        @GET("/")
        Call<String> getTopMovieString(@Query("start") int start, @Query("count") int count);

        @GET("top250")
        Call<MoveEntity> getTopMovie(@Query("start") int start, @Query("count") int count);
    }

    private Sample getSample() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return retrofit.create(Sample.class);
    }

    @Test
    public void testInit() throws IOException, InterruptedException {
        getSample().getTopMovieString(0, 10).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                JunitUtil.log(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
        Thread.sleep(3000);
    }

    @Test
    public void testJson() throws InterruptedException {
        getSample().getTopMovie(0, 10).enqueue(new Callback<MoveEntity>() {
            @Override
            public void onResponse(Call<MoveEntity> call, Response<MoveEntity> response) {
                JunitUtil.log(response.body() == null ? "null" : response.body().title);
            }

            @Override
            public void onFailure(Call<MoveEntity> call, Throwable t) {

            }
        });
        Thread.sleep(3000);
    }

    class MoveEntity {
        String count;
        String start;
        String total;
        String title;
    }

    class Sub {
        String rating;

    }
}
