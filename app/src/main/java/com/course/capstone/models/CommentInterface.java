package com.course.capstone.models;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CommentInterface {
    @GET( "api/v1/comment")
    Call<List<Comment>> getAll();

    @GET( "api/v1/comment/{id}")
    Call <Comment> getById(@Path("id") String id);


    @GET( "api/v1/comment/parentid/{parentid}")
    Call <List<Comment>>  getByQid(@Path("parentid") String qid);


    @POST("api/v1/comment/create")
    Call<Comment> addComment(@Body Comment comment);



    @DELETE("api/v1/comment/delete/{id}")
    Call<Void> removeComment(@Path("id") String id);


    @PUT("api/v1/comment/update/{id}")
    Call<Comment> updateComment(@Path("id") String id,@Body Comment comment);
}
