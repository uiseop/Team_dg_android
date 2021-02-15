package com.course.capstone.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.course.capstone.R;
import com.course.capstone.models.User;
import com.course.capstone.models.UserInterface;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class RegisterActivity extends AppCompatActivity {

    private EditText join_email, join_password, join_name, join_pwck, join_id, join_birth;
    private RelativeLayout join_button, check_button, pre_button;
    private AlertDialog dialog;
    private boolean validate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_join );

        //아이디값 찾아주기
        join_email = findViewById(R.id.editText_joinemail );
        join_password = findViewById( R.id.editText_joinpw );
        join_name = findViewById( R.id.editText_joinname );
        join_pwck = findViewById(R.id.editText_joinpwck);
        join_birth = findViewById(R.id.editText_joinbirth);
        join_id = findViewById(R.id.editText_joinid);

        join_button = (RelativeLayout) findViewById(R.id.layout7);
        check_button = (RelativeLayout) findViewById(R.id.check_button);
        pre_button = (RelativeLayout)findViewById(R.id.layout8);


        pre_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        //
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-3-139-15-252.us-east-2.compute.amazonaws.com:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserInterface userInterface = retrofit.create(UserInterface.class);


        //아이디 중복 체크
        check_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkUserid();
            }
        });

        //비밀번호 확인
        join_pwck.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String password = join_password.getText().toString();
                String confirm = join_pwck.getText().toString();

                if(password.equals(confirm)){
                    join_password.setBackgroundColor(Color.GREEN);
                    join_pwck.setBackgroundColor(Color.GREEN);
                } else{
                    join_password.setBackgroundColor(Color.RED);
                    join_pwck.setBackgroundColor(Color.RED);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //가입
        join_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(join_id.getText().toString().length()==0){
                    Toast.makeText(RegisterActivity.this, "ID를 입력하세요.", Toast.LENGTH_SHORT).show();
                    join_id.requestFocus();
                    return;
                }
                if( join_password.getText().toString().length() == 0 ) {
                    Toast.makeText(RegisterActivity.this, "비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show();
                    join_password.requestFocus();
                    return;
                }
                if( join_pwck.getText().toString().length() == 0 ) {
                    Toast.makeText(RegisterActivity.this, "비밀번호 확인을 입력하세요.", Toast.LENGTH_SHORT).show();
                    join_pwck.requestFocus();
                    return;
                }
                if( join_birth.getText().toString().length() == 0 ) {
                    Toast.makeText(RegisterActivity.this, "생년월일을 입력하세요.", Toast.LENGTH_SHORT).show();
                    join_birth.requestFocus();
                    return;
                }
                if( join_email.getText().toString().length() == 0 ) {
                    Toast.makeText(RegisterActivity.this, "이메일을 입력하세요.", Toast.LENGTH_SHORT).show();
                    join_email.requestFocus();
                    return;
                }

                String useremail = join_email.getText().toString();
                String userpw = join_password.getText().toString();
                String name = join_name.getText().toString();
                String userid = join_id.getText().toString();
                String birthdate = join_birth.getText().toString();

                HashMap<String,Object> hashMap = new HashMap<>();
                hashMap.put("name",name);
                hashMap.put("userid",userid);
                hashMap.put("password",userpw);
                hashMap.put("birthdate",birthdate);
                hashMap.put("emailaddress",useremail);

                register(name, userid, userpw, birthdate, useremail);
            }
        });
    }
    public void register(String name, String userid, String userpw, String birthdate, String useremail){

        /*String name = parameters.get("name").toString();
        String userid = parameters.get("userid").toString();
        String password = parameters.get("password").toString();
        String birthdate = parameters.get("birthdate").toString();
        String emailaddress = parameters.get("emailaddress").toString();*/

        User newuser = new User(name, userid, userpw, birthdate, useremail);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-3-139-15-252.us-east-2.compute.amazonaws.com:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserInterface userInterface = retrofit.create(UserInterface.class);
        Call<User> call = userInterface.createUser(newuser);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();
                    Log.d(TAG, "onResponse1: Something success");

                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                }
                else{
                    Object object = response.errorBody();
                    Log.d(TAG,object.toString());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
        pre_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void checkUserid() {
        Log.d(TAG,"ada");
        String userid = join_id.getText().toString();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-3-139-15-252.us-east-2.compute.amazonaws.com:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserInterface userInterface = retrofit.create(UserInterface.class);

        Call<User> call = userInterface.getUserByUserid(userid);

        call.enqueue(new Callback<User>() { //리스폰 시, 대응할 구현체
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) { //check for Response status
                    Log.d(TAG,"if들어옴");
                    User user = response.body(); //리스폰의 바디를 Result객체로 담아쥼.
                    //Todo : 성공시 할일
                    Toast toast = Toast.makeText(getApplicationContext(), "ID가 중복되었습니다.", Toast.LENGTH_LONG);
                    toast.show();
                    join_id.setText("");
                } else {
                    Log.d(TAG,"else들어옴");
                    // Todo: 실패시 할일
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(), "ID가 사용 가능합니다", Toast.LENGTH_LONG);
                toast.show();

            }

        });

    }
}