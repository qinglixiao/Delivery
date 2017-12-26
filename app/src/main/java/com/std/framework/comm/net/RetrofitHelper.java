package com.std.framework.comm.net;

import com.std.framework.comm.net.basic.ToStringConverterFactory;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Description:
 * Created by 李晓 ON 2017/12/20.
 * Job number:137289
 * Phone:18611867932
 * Email:lixiao3@syswin.com
 * Person in charge:李晓
 * Leader: 李晓
 */
public class RetrofitHelper {
    private int type = 0; // 0:Json  1:String
    private String baseUrl;

    private RetrofitHelper() {
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Retrofit get() {
        if (type == 0) {
            return new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        } else if (type == 1) {
            return new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(new ToStringConverterFactory())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
        else {
            throw new IllegalArgumentException("RetrofitHelper === not set return type");
        }
    }

    public static class Builder {
        private int type;
        private String baseUrl;

        public Builder withJson() {
            type = 0;
            return this;
        }

        public Builder withString() {
            type = 1;
            return this;
        }

        public Builder baseUrl(String url) {
            this.baseUrl = url;
            return this;
        }

        public RetrofitHelper build() {
            RetrofitHelper helper = new RetrofitHelper();
            helper.setType(type);
            helper.setBaseUrl(baseUrl);
            return helper;
        }
    }
}
