package com.course.capstone.models;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserInterface {
    @POST("api/v1/user/create")
    Call<User> createUser(@Body User user);

//    @RequestMapping(value = "api/v1/user/create", method = {RequestMethod.POST})
//    @ResponseStatus(value=HttpStatus.OK)
//    public User addUser(@RequestBody User user){
//        return userService.addUser(user);
//    }
//
    @GET("api/v1/user")
    Call<List<User>> getUserList();

    @GET("api/v1/user/{id}")
    Call<User> getUser(@Path("id") String id);

    @DELETE("api/v1/user/delete/{id}")
    Call<Void> deleteUser(@Path("id") String id);

    @GET("api/v1/userid/{userid}")
    Call<User> getUserByUserid(@Path("userid") String userid);

    @POST("api/v1/user/login")
    Call<User> loginUser(@Body HashMap<String, Object> parameters);

    @PUT("api/v1/user/update/{id}")
    Call<User> updateUser(@Path("id")String id,@Body User user);
//
//    @RequestMapping(value = "api/v1/user/update/{id}", method = {RequestMethod.PUT})
//    @ResponseStatus(value=HttpStatus.OK)
//    public User updateUser(@PathVariable(value = "id") String id, @RequestBody User user){
//        return userService.updateUser(user);
//    }
}
