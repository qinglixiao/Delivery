package com.std.framework.net;

import retrofit2.http.GET;

/**
 * Created by gfy on 2016/4/7.
 */
public class UserService extends BaseRequest{

    public interface User{
        @GET("/login")
        void login();
    }


}
