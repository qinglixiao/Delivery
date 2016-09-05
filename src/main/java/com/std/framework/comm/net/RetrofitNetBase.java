package com.std.framework.comm.net;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/6/11.
 */
public abstract  class RetrofitNetBase {
    public static String baseUrl = "";

    protected <T> T wrap(Class<T> it){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

       return (T) retrofit.create(it.getClass());
    }
}
