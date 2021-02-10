package com.course.capstone.models;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GameInterface {
    @GET("product")
    Call<String> getHtml();
}
