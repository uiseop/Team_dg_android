package com.course.capstone.firebase;

import android.content.Intent;
import android.util.Log;

import com.course.capstone.MypageActivity;
import com.course.capstone.models.DataManager;
import com.course.capstone.models.User;
import com.course.capstone.models.UserInterface;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService{
        @Override
        public void onTokenRefresh() {
            String refreshedToken = FirebaseInstanceId.getInstance().getToken();
            sendRegistrationToServer(refreshedToken);
        }

        private void sendRegistrationToServer(String token) {
            //FCM 토큰 갱신 (서버로 토큰값 전송)
            //가장 마지막으로 로그인한 기기의 토큰을 User.token에 저장
            Log.d("server send Token: ", token);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://ec2-3-139-15-252.us-east-2.compute.amazonaws.com:8080/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            UserInterface userInterface = retrofit.create(UserInterface.class);

            DataManager dataManager = DataManager.getInstance();
            User user = dataManager.getUser();
            user.setToken(token);
            String ID = dataManager.getUser().getId();
            Call<User> call = userInterface.updateUser(ID, user);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    dataManager.setUser(user);
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {

                }
            });

        }
}
