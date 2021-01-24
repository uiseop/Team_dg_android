package com.course.capstone.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.course.capstone.R;
import com.course.capstone.models.DataManager;
import com.course.capstone.models.User;

public class MyInfoActivity extends AppCompatActivity {

    TextView correct,exit;
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
    }
}