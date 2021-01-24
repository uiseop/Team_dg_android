package com.course.capstone.models;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface LikeInterface {
    @GET( "api/v1/Like")
    Call<List<Like>> getAll();

    @GET("api/v1/like/{parentqnaid}")
    Call<Like>  getByPatentid(@Path("parentqnaid") String parentqnaid);

    @POST("api/v1/like/create")
    Call<Like> addLike(@Body Like like);


    @DELETE( "api/v1/Like/delete/{id}")
    Call<Void> removeLike(@Path("id") String id);


}
