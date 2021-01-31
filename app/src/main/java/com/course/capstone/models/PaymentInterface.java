package com.course.capstone.models;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PaymentInterface {
//    @Autowired
//    private final PaymentService paymentService;
//
//    public PaymentController(PaymentService paymentService) {
//        this.paymentService = paymentService;
//    }


    @POST("api/v1/payment/create")
    Call<Payment> createPayment(@Body Payment payment);

    @GET("api/v1/payment")
    Call<List<Payment>> getAll();

    @GET("api/v1/payment/{id}")
    Call<Payment> getByID(@Path("id") String id);;

    @GET("api/v1/payment/category/{category}")
    Call<Payment> getByCategory(@Path("category")String category);

    @GET("api/v1/payment/shopname/{shopname}")
    Call<Payment> getByShopname(@Path("shopname")String shopname);

    @DELETE("api/v1/payment/delete/{id}")
    Call<Payment> removePayment(@Path("id")String id);

    @GET("api/v1/payment/parentaccount/{parentaccount}")
    Call<List<Payment>> getByParentaccount(@Path("parentaccount") int parentaccount);
}
