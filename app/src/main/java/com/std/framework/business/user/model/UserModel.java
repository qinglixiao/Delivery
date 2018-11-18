package com.std.framework.business.user.model;

import com.google.gson.Gson;
import com.std.framework.comm.net.AbstractModule;

import org.reactivestreams.Publisher;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import rx.functions.Func1;

/**
 * Description:
 * Created by 李晓 ON 2017/12/20.
 * Job number:137289
 * Phone:18611867932
 * Email:lixiao3@syswin.com
 * Person in charge:李晓
 * Leader: 李晓
 */
public class UserModel extends AbstractModule {
    private static final String base = "https://api.douban.com/v2/movie/";
    private UserModel.IUser user;

    @Override
    public void init() {
        baseUrl = base;
//        type = 1;
        user = attach(UserModel.IUser.class);
    }

    public class MoveEntity {
        String count;
        String start;
        String total;
        String title;
    }

    public interface IUser {
        @GET("top250")
        Flowable<MoveEntity> getTopMovie(@Query("start") int start, @Query("count") int count);

        @GET("top250")
        Flowable<String> getTopMovieString(@Query("start") int start, @Query("count") int count);

//        @POST("/user/generateCypherTextForBJToon")
//        Observable<String> generate(@Body TNPSecretKeyForBJInput input);
    }

    public Flowable<MoveEntity> getTopMovieString(int start, int count) {
        return user.getTopMovieString(start, count)
                .flatMap(new Function<String, Publisher<MoveEntity>>() {
                    @Override
                    public Publisher<MoveEntity> apply(String s) throws Exception {
                        return Flowable.just(new Gson().fromJson(s, MoveEntity.class));
                    }
                });
    }

    public Flowable<MoveEntity> getTopMovie(int start, int count) {
        return user.getTopMovie(start, count);
    }


}
