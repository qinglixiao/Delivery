package com.std.framework.study.other.module;

import com.std.framework.comm.net.AbstractModule;
import com.std.framework.study.other.module.bean.TNPFeedInputForm;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Description:
 * Created by 李晓 ON 2018/1/3.
 * Job number:137289
 * Phone:18611867932
 * Email:lixiao3@syswin.com
 * Person in charge:李晓
 * Leader: 李晓
 */
public class ToonModule extends AbstractModule {
    public static final String BASE_URL = "http://p100.api.css.systoon.com/";
    private toonModule module;

    @Override
    public void init() {
        baseUrl = BASE_URL;
        type = 0;
        module = attach(toonModule.class);
    }

    interface toonModule {
        @GET("/user/getTrendsContentAndCommentList")
        Flowable<String> getCommonList(@Query("startId") int startId, @Query("line") int line, @Query("feedId") String feedId, @Query("endId") int endId, @Query("pageIndex") int pageIndex);

        @POST("http://api.feed.systoon.com/user/obtainFeedList")
        Flowable<String> obtainFeedList(@Body TNPFeedInputForm inputForm);

    }

    public Flowable<String> getCommonList() {
        return module.getCommonList(0, 20, "o_1472486901507314", 0, 0);
    }

    public Flowable<String> obtainFeedList() {
        TNPFeedInputForm input = new TNPFeedInputForm();
        input.setFeedIds(Arrays.asList("s_1511430679413833", "s_1511430679413833"));
        return module.obtainFeedList(input);
    }

}
