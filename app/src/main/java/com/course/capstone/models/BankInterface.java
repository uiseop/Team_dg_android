package com.course.capstone.models;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BankInterface {
    @GET("api/v1/bank")
    Call<Bank> getAllBank();

    @GET("api/v1/bankid/{parentid}")
    Call<Bank> getByParentId(@Path("parentid")String parentid);

    @POST("api/v1/bank/create")
    Call<Bank> addBank(@Body Bank bank);

    @DELETE("api/v1/bank/delete/{id}")
    Call<Bank> removeBank(@Path("id")String id);
}
