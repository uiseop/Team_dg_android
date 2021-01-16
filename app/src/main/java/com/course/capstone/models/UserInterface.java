package com.course.capstone.models;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserInterface {
    @POST("api/v1/user/create")
    Call<User> createUser(@Body User user);

    @GET("api/v1/user")
    Call<List<User>> getUserList();

    @GET("api/v1/user/{id}")
    Call<User> getUser(@Path("id") String id);

    @DELETE("api/v1/user/delete/{id}")
    Call<Void> deleteUser(@Path("id") String id);

    @GET("api/v1/userid/{userid}")
    Call<User> getUserByUserid(@Path("userid") String userid);

    @POST("api/v1/user/login")
    Call<User> loginUser(@Body HashMap<String, Object> parameters);

}
