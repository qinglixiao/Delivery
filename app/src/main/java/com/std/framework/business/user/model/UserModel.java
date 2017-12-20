package com.std.framework.business.user.model;

import com.std.framework.business.user.db.entity.User;
import com.std.framework.comm.net.AbstractModule;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Description:
 * Created by 李晓 ON 2017/12/20.
 * Job number:137289
 * Phone:18611867932
 * Email:lixiao3@syswin.com
 * Person in charge:李晓
 * Leader: 李晓
 */
public class UserModel<IUser> extends AbstractModule<IUser> {
    private static final String base = "";

    public interface IUser {
//        @GET("top250")
//        Call<MoveEntity> getTopMovie(@Query("start") int start, @Query("count") int count);

        @GET("top250")
        Observable<String> getTopMovieString(@Query("start") int start, @Query("count") int count);

//        @POST("/user/generateCypherTextForBJToon")
//        Observable<String> generate(@Body TNPSecretKeyForBJInput input);
    }

}
