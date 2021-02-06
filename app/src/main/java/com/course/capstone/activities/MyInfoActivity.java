package com.course.capstone.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.course.capstone.R;
import com.course.capstone.models.DataManager;
import com.course.capstone.models.User;

public class MyInfoActivity extends AppCompatActivity {

    TextView correct,exit,logout;
    TextView id,name,email,birth;
    DataManager dataManager = DataManager.getInstance();
    User user = dataManager.getUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myinfo);

        correct = findViewById(R.id.textView_correct);
        exit = findViewById(R.id.textView_exit);
        id = findViewById(R.id.textView_myid);
        name = findViewById(R.id.textView_myname);
        email = findViewById(R.id.textView_myemail);
        birth = findViewById(R.id.textView_mybirth);
        logout = findViewById(R.id.textView12);

        id.setText(user.getUserid());
        name.setText(user.getName());
        email.setText(user.getEmailaddress());
        birth.setText(user.getBirthdate());

        correct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyInfoActivity.this, CorrectMyInfo.class);
                startActivity(intent);
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                new AlertDialog.Builder(context)
                        .setTitle("회원탈퇴 ").setMessage("정말로 탈퇴 하겠습니가?")
                        .setPositiveButton("예", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                new android.app.AlertDialog.Builder(context)
                        .setTitle("로그아웃").setMessage("로그아웃 하시겠습니까?")
                        .setPositiveButton("로그아웃", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                Intent i = new Intent(context, LoginActivity.class);
                                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                User user1 = new User();
                                dataManager.setUser(user1);
                                context.startActivity(i);
                                Log.d("정보",dataManager.getUser().toString());
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                            }
                        })
                        .show();
            }
        });
    }
}