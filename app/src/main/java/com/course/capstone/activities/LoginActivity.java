package com.course.capstone.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.course.capstone.MainActivity;
import com.course.capstone.R;
import com.course.capstone.models.DataManager;
import com.course.capstone.models.User;
import com.course.capstone.models.UserInterface;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class LoginActivity extends AppCompatActivity {

    private EditText login_email, login_password;
    private ImageButton join_button,login_button;

    String username, pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );

        login_email = findViewById( R.id.login_id );
        login_password = findViewById( R.id.login_password );

        join_button = findViewById( R.id.register );
        join_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        login_button = findViewById( R.id.login_btn );
        login_button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String UserID = login_email.getText().toString();
                String UserPw = login_password.getText().toString();

                HashMap<String,Object> hashMap = new HashMap<>();
                hashMap.put("userid",UserID);
                hashMap.put("password",UserPw);

                login(hashMap);
                }
        });
    }
    public void login(HashMap<String, Object> parameters){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-3-139-15-252.us-east-2.compute.amazonaws.com:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserInterface userinterface = retrofit.create(UserInterface.class);
        Call<User> call = userinterface.loginUser(parameters);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()){
                    User user = response.body();
                    DataManager dataManager=DataManager.getInstance();
                    dataManager.setUser(user);

                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }
                else{
                    Log.d(TAG, "onResponse1: Something Wrong");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getBaseContext(), "존재하지 않는 ID이거나 비밀번호가 틀렸습니다.",Toast.LENGTH_LONG).show();;
                Log.d(TAG, "onFailure2: Something Wrong");
            }
        });
    }
}