package com.course.capstone.models;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
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
//
//    @GetMapping("api/v1/payment")
//    public List<Payment> getAll() {
//        return paymentService.getAllPayments();
//    }
//
//    @GetMapping("api/v1/payment/{id}")
//    public Payment getByID(@PathVariable("id") String id) {
//        return paymentService.getPayment(id);
//    }
//
//    @GetMapping("api/v1/payment/category/{category}")
//    public Payment getByCategory(@PathVariable("category") String category) {
//        return paymentService.getPaymentByCategory(category);
//    }
//
//    @GetMapping("api/v1/payment/shopname/{shopname}")
//    public Payment getByShopname(@PathVariable("shopname") String shopname) {
//        return paymentService.getPaymentByShopname(shopname);
//    }
//
//    @PostMapping("api/v1/payment/add")
//    @ResponseStatus(value=HttpStatus.OK)
//    public Payment addPayment(@RequestBody Payment payment){
//        return paymentService.addPayment(payment);
//    }
//
//    @DeleteMapping("api/v1/payment/delete/{id}")
//    public void removePayment(@PathVariable String id) {
//        paymentService.removePayment(id);
//    }
    @POST("api/v1/payment/create")
    Call<Payment> createPayment(@Body Payment payment);

    @GET("api/v1/payment")
    Call<List<Payment>> getPaymentList();

    @GET("api/v1/payment/{id}")
    Call<Payment> getPayment(@Path("id") String id);;


}
