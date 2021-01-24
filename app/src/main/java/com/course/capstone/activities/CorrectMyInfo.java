package com.course.capstone.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.course.capstone.MypageActivity;
import com.course.capstone.R;
import com.course.capstone.models.DataManager;
import com.course.capstone.models.RetrofitInterface;
import com.course.capstone.models.User;
import com.course.capstone.models.UserInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CorrectMyInfo extends AppCompatActivity {

    private TextView id;
    private EditText correct_name, correct_email, correct_birth, correct_pw, correct_pwck;
    private Button btn;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://ec2-13-59-15-254.us-east-2.compute.amazonaws.com:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    UserInterface userInterface = retrofit.create(UserInterface.class);

    DataManager dataManager = DataManager.getInstance();
    User user = dataManager.getUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correctmyinfo);

        id = findViewById(R.id.textView_correctid);
        correct_birth = findViewById(R.id.editText_correctbirth);
        correct_email = findViewById(R.id.editText_correctemail);
        correct_name = findViewById(R.id.editText_correctname);
        correct_pw = findViewById(R.id.editText_correctpw);
        correct_pwck = findViewById(R.id.editText_correctpwck);
        btn = findViewById(R.id.button_correct);

        id.setText(user.getUserid());
        correct_birth.setText(user.getBirthdate());
        correct_email.setText(user.getEmailaddress());
        correct_name.setText(user.getName());

        //비밀번호 확인
        correct_pwck.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String password = correct_pw.getText().toString();
                String confirm = correct_pwck.getText().toString();

                if (password.equals(confirm)) {
                    correct_pw.setBackgroundColor(Color.GREEN);
                    correct_pwck.setBackgroundColor(Color.GREEN);
                } else {
                    correct_pw.setBackgroundColor(Color.RED);
                    correct_pwck.setBackgroundColor(Color.RED);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //수정
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ID = id.getText().toString();
                String name = correct_name.getText().toString();
                String pw = correct_pwck.getText().toString();
                String birth = correct_birth.getText().toString();
                String email=  correct_email.getText().toString();

                user.setUserid(ID);
                user.setName(name);
                user.setPassword(pw);
                user.setBirthdate(birth);
                user.setEmailaddress(email);

                Call<User> call = userInterface.updateUser(ID,user);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        dataManager.setUser(user);
                        Intent intent = new Intent(getApplicationContext(), MypageActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });
            }
        });
    }
}