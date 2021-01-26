package com.course.capstone.models;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ConcernInterface {
    @GET("api/v1/interest")
    Call<Concern> getAll();

    @GET("api/v1/interest/{id}")
    Call<Concern> getByID(@Path("id")String id);

    @GET("api/v1/interest/parentid/{parentid}")
    Call<Concern> getByParentID(@Path("parentid")String parentid);

    @GET("api/v1/interest/interestname/{interestname}")
    Call<Concern> getByInterestname(@Path("interestname")String interestname);

    @POST("api/v1/interest/add")
    Call<Concern> addInterest(@Body Concern concern);

    @DELETE("api/v1/interest/delete/{id}")
    Call<Concern> removeInterset(@Path("id")String id);
}
