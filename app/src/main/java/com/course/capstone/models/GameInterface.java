package com.course.capstone.models;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GameInterface {
    @GET("product")    Call<String> getHtml();


    @GET("page1")
    Call<String> page1();

    @GET("page2")
    Call<String> page2();

    @GET("page3")
    Call<String> page3();

    @GET("page4")
    Call<String> page4();

    @GET("page5")
    Call<String> page5();

    @GET("page6")
    Call<String> page6();

    @GET("page7")
    Call<String> page7();

    @GET("page8")
    Call<String> page8();

    @GET("page9")
    Call<String> page9();

    @GET("page10")
    Call<String> page10();

    @GET("page11")
    Call<String> page11();

    @GET("page12")
    Call<String> page12();

    @GET("page13")
    Call<String> page13();

    @GET("page14")
    Call<String> page14();



}
