package com.std.framework.net;

import retrofit2.Retrofit;

/**
 * Created by gfy on 2016/4/7.
 */
public abstract class BaseRequest {
    protected static final String base_url = "";

    protected Retrofit retrofit;

    public BaseRequest(){
        retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .build();
    }

}
