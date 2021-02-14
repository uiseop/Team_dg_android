package com.course.capstone.models;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EduInterface {

    @GET("api/v1/edu/educategory/{educategory}")
    Call<List<Edu>> getEduByEducategory(@Path("educategory")String educategory);

}
