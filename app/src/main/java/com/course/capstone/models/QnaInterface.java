package com.course.capstone.models;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface QnaInterface {
    //게시판 목록
    @GET( "api/v1/qna")
    Call<List<Qna>> getAll();

   @GET("api/v1/qna/{qnatitle}")
   Call<Qna>  getByQnatitle(@Path("qnatitle") String qnatitle);

    @POST("api/v1/qna/create")
    Call<Qna> addqna(@Body Qna qna);


    @DELETE("api/v1/qna/delete/{id}")
    Call<Void> removeQna(@Path("id") String id);


}
