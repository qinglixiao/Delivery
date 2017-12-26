package com.std.framework.business.home.model;

import com.std.framework.comm.net.NetBase;

import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2016/6/11.
 */
public class MainModel extends NetBase {

    interface User {
        @POST
        Call<String> login();
    }

    private User user;

    public MainModel() {
    }


}
