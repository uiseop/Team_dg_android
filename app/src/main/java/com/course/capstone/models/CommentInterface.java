package com.course.capstone.models;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CommentInterface {
    @GET( "api/v1/comment")
    Call<List<Comment>> getAll();

    @GET( "api/v1/comment/{id}")
    Call <Comment> getById(@Path("id") String id);

    @POST("api/v1/comment/create")
    Call<Comment> addComment(@Body Comment comment);


    @DELETE("api/v1/comment/delete/{id}")
    Call<Void> removeComment(@Path("id") String id);
}
