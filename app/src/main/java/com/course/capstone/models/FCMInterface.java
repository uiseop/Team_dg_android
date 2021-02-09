package com.course.capstone.models;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface FCMInterface {
    @POST("api/v1/fcm/send")
    Call<GenericResponse> send(@Body HashMap<String, Object> hashMap);

    @POST("api/v1/fcm/daily")
    Call<GenericResponse> daily(@Body HashMap<String, Object> hashMap);

    @POST("api/v1/fcm/balance")
    Call<GenericResponse> balance (@Body HashMap<String, Object> hashMap);
}
