package com.course.capstone;

import android.util.Log;
import android.webkit.JavascriptInterface;

import com.course.capstone.models.Cross;
import com.course.capstone.models.DataManager;
import com.course.capstone.models.User;
import com.course.capstone.models.UserInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyJavascriptInterface {
    DataManager dataManager = DataManager.getInstance();
    User user = dataManager.getUser();
    public MyJavascriptInterface() {
    }

    @JavascriptInterface
    public void receiveString(String score, String category) {
        // String received from WebView
        Log.d("Cross", "Score : " + score);
        Log.d("Cross", "Category : " + category);

        List<Cross> crossList = new ArrayList<>();
        if(user.getCross() != null){
            crossList = user.getCross();
            int check = 0;
            for(int i=0; i<crossList.size(); i++){
                if(crossList.get(i).getCategory().equals(category)){
                    Log.d("Fail", category);
                    Log.d("Fail", crossList.get(i).getCategory());
                    Log.d("Fail", "이미 푼 적이 있는 문제입니다");
                    break;
                }
                else{
                    check++;
                }
            }
            if(check == crossList.size())
                crossList.add(new Cross(Integer.parseInt(score), category));
        }
        else{
            crossList.add(new Cross(Integer.parseInt(score), category));
        }

        User newuser = new User(user.getName(), user.getUserid(), user.getPassword(), user.getBirthdate(),
                user.getEmailaddress(), user.getToken(), crossList, user.getId());


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-3-139-15-252.us-east-2.compute.amazonaws.com:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserInterface userInterface = retrofit.create(UserInterface.class);
        Call<User> call = userInterface.updateUser(user.getId(), newuser);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                dataManager.setUser(user);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}
