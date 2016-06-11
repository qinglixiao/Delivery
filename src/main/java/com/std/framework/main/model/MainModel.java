package com.std.framework.main.model;

import com.std.framework.net.RetrofitNetBase;

import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2016/6/11.
 */
public class MainModel extends RetrofitNetBase {

    interface User {
        @POST
        Call<String> login();
    }

    private User user;

    public MainModel() {
        user = wrap(User.class);
    }


}
