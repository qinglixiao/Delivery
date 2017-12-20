package com.std.framework.comm.net;

import android.text.TextUtils;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/6/11.
 */
public abstract class RetrofitNetBase<T> {
    public static String baseUrl = "";
    private NetHelper helper;
    private T type;
    public T request;

    protected void wrap() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        request = (T) retrofit.create(type.getClass());
    }

    private void makeRetrofit(){
        if(TextUtils.isEmpty(getBaseUrl())){

        }
    }

    protected String getBaseUrl() {
        return "";
    }
}
