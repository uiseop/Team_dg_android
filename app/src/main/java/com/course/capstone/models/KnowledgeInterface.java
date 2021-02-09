package com.course.capstone.models;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface KnowledgeInterface {
    @GET("api/v1/knowledge")
    Call<List<Knowledge>> getAll();

    @GET("api/v1/knowledge/{id}")
    Call<Knowledge> getKnowledge(@Path("id") String id);

    @POST("api/v1/knowledge/create")
    Call<Knowledge> addKnowledge(@Body Knowledge knowledge);

    @PUT("api/v1/knowledge/update/{id}")
    Call<Knowledge> updateKnowledge(@Path("id") String id, @Body Knowledge knowledge);

    @DELETE("api/v1/knowledge/delete/{id}")
    Call<Void> removeKnowledge(@Path("id") String id);
}
