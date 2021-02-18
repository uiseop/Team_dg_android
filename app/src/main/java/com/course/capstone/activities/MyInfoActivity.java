package com.course.capstone.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.course.capstone.CardActivity;
import com.course.capstone.R;
import com.course.capstone.models.DataManager;
import com.course.capstone.models.User;

public class MyInfoActivity extends AppCompatActivity {

    TextView correct,exit,logout;
    TextView id,name,email,birth,uname,card;
    ImageView pattern;
    DataManager dataManager = DataManager.getInstance();
    User user = dataManager.getUser();
    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myinfo);
        imageButton = findViewById(R.id.back);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        correct = findViewById(R.id.textView_correct);
        correct.setPaintFlags(correct.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        exit = findViewById(R.id.textView_exit);
        exit.setPaintFlags(exit.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        id = findViewById(R.id.textView_myid);
        name = findViewById(R.id.textView_myname);
        email = findViewById(R.id.textView_myemail);
        birth = findViewById(R.id.textView_mybirth);
        logout = findViewById(R.id.textView12);
        exit.setPaintFlags(exit.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        id.setText(user.getUserid());
        name.setText(user.getName());
        email.setText(user.getEmailaddress());
        birth.setText(user.getBirthdate());
        uname =findViewById(R.id.textView13);
        uname.setText(user.getName()+"님 환영합니다!");
        card = findViewById(R.id.textView_card);
        pattern = findViewById(R.id.imageView_payment);

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
        pattern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyInfoActivity.this, PaymentPattern.class);
                startActivity(intent);
            }
        });
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyInfoActivity.this, CardActivity.class);
                startActivity(intent);
            }
        });

    }
}