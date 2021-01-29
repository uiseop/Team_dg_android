package com.course.capstone.models;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CardInterface {
    @GET("api/v1/cardinfo")
    Call <List<Card>> getAllCardinfo();
    //    @GetMapping("api/v1/cardinfo")
//    public List<Cardinfo> getAll() {
//        return cardinfoService.getAllCardinfo();
//    }
    @GET("api/v1/cardinfo/{id}")
    Call <Card> getCardinfo(@Path("id") String id);
    //id에 따라 불러오는
//    @GetMapping("api/v1/cardinfo/{id}")
//    public Cardinfo getByID(@PathVariable("id") String id) {
//        return cardinfoService.getCardinfo(id);
//    }

    @GET("api/v1/cardinfo/company/{companyname}")
    Call <List<Card>> getCardinfoByCompanyname(@Path("companyname")String companyname);
//    카드사별로 불러오는
//    @GetMapping("api/v1/cardinfo/company/{companyname}")
//    public List<Cardinfo> getByCompanyname(@PathVariable("companyname") String companyname) {
//        return cardinfoService.getCardinfoByCompanyname(companyname);
//    }

    @GET("api/v1/cardinfo/rewards/{rewards}")
    Call <List<Card>> getCardinfoByRewardsIn(@Path("rewards") List<String> rewards);
//    @GetMapping("api/v1/cardinfo/rewards/{rewards}")
//    public List<Cardinfo> getByRewards(@PathVariable("rewards") List<String> rewards) {
//        return cardinfoService.getCardinfoByRewardsIn(rewards);
//    } 카드 혜택

    @POST("api/v1/cardinfo/add")
    Call<Card>addCardinfo(@Body Card card);
//    @PostMapping("api/v1/cardinfo/add")
//    @ResponseStatus(value=HttpStatus.OK)
//    public Cardinfo addCardinfo(@RequestBody Cardinfo cardinfo){
//        return cardinfoService.addCardinfo(cardinfo);
//    }
//    @DELETE("api/v1/cardinfo/delete/{id}")
//    Call<Card>deleteCardinfo(@Path('id') String id);
//    @DeleteMapping("api/v1/cardinfo/delete/{id}")
//    public void removeCardinfo(@PathVariable String id) {
//        cardinfoService.removeCardinfo(id);
//    }


}
