package com.std.framework.comm.net;

import org.greenrobot.greendao.annotation.NotNull;

/**
 * Created by Administrator on 2016/6/11.
 */
public abstract class NetBase {
    public static String baseUrl = "";
    public int type = 0;// 0:Json  1:String

    public <T> T attach(@NotNull Class<T> it) {
        if (type == 0) {
            return new RetrofitHelper.Builder()
                    .baseUrl(baseUrl)
                    .withJson()
                    .build()
                    .get()
                    .create(it);
        } else {
            return new RetrofitHelper.Builder()
                    .baseUrl(baseUrl)
                    .withString()
                    .build()
                    .get()
                    .create(it);
        }
    }
}
