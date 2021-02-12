package com.course.capstone.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;

import com.course.capstone.Frag1;
import com.course.capstone.MainActivity;
import com.course.capstone.R;
import com.course.capstone.models.DataManager;

public class TestActivity extends AppCompatActivity {

    Button btn1,btn2;
    RadioButton c1_1,c1_2,c1_3,c1_4,c1_5;
    RadioButton c2_1,c2_2,c2_3,c2_4,c2_5;
    CheckBox c3_1,c3_2,c3_3,c3_4,c3_5;
    RadioButton c4_1,c4_2,c4_3,c4_4;
    RadioButton c5_1,c5_2,c5_3,c5_4,c5_5;
    RadioButton c6_1,c6_2,c6_3;
    RadioButton c7_1,c7_2,c7_3,c7_4;
    double score=0;
    DataManager dataManager=DataManager.getInstance();
    String name=dataManager.getUser().getName();
    String s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        btn1=findViewById(R.id.test_clear);
        btn2=findViewById(R.id.test_pre);

        c1_1 = findViewById(R.id.checkBox1_1);
        c1_2 = findViewById(R.id.checkBox1_2);
        c1_3 = findViewById(R.id.checkBox1_3);
        c1_4 = findViewById(R.id.checkBox1_4);
        c1_5 = findViewById(R.id.checkBox1_5);

        c2_1 = findViewById(R.id.checkBox2_1);
        c2_2 = findViewById(R.id.checkBox2_2);
        c2_3 = findViewById(R.id.checkBox2_3);
        c2_4 = findViewById(R.id.checkBox2_4);
        c2_5 = findViewById(R.id.checkBox2_5);

        c3_1 = findViewById(R.id.checkBox3_1);
        c3_2 = findViewById(R.id.checkBox3_2);
        c3_3 = findViewById(R.id.checkBox3_3);
        c3_4 = findViewById(R.id.checkBox3_4);
        c3_5 = findViewById(R.id.checkBox3_5);

        c4_1 = findViewById(R.id.checkBox4_1);
        c4_2 = findViewById(R.id.checkBox4_2);
        c4_3 = findViewById(R.id.checkBox4_3);
        c4_4 = findViewById(R.id.checkBox4_4);

        c5_1 = findViewById(R.id.checkBox5_1);
        c5_2 = findViewById(R.id.checkBox5_2);
        c5_3 = findViewById(R.id.checkBox5_3);
        c5_4 = findViewById(R.id.checkBox5_4);
        c5_5 = findViewById(R.id.checkBox5_5);

        c6_1 = findViewById(R.id.checkBox6_1);
        c6_2 = findViewById(R.id.checkBox6_2);
        c6_3 = findViewById(R.id.checkBox6_3);

        c7_1 = findViewById(R.id.checkBox7_1);
        c7_2 = findViewById(R.id.checkBox7_2);
        c7_3 = findViewById(R.id.checkBox7_3);
        c7_4 = findViewById(R.id.checkBox7_4);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(c1_1.isChecked() || c1_2.isChecked()){
                    score+=4;
                }
                else if(c1_3.isChecked()){
                    score+=3;
                }
                else if(c1_4.isChecked()){
                    score+=2;
                }
                else if(c1_5.isChecked()){
                    score+=1;
                }

                if(c2_1.isChecked()){
                    score+=1;
                }
                else if(c2_2.isChecked()){
                    score+=2;
                }
                else if(c2_3.isChecked()){
                    score+=3;
                }
                else if(c2_4.isChecked()){
                    score+=4;
                }
                else if(c2_5.isChecked()){
                    score+=5;
                }

                if(c3_5.isChecked()){
                    score+=5;
                }
                else if(c3_4.isChecked()){
                    score+=4;
                }
                else if(c3_3.isChecked()){
                    score+=3;
                }
                else if(c3_2.isChecked()){
                    score+=2;
                }
                else if(c3_1.isChecked()){
                    score+=1;
                }

                if(c4_1.isChecked()){
                    score+=1;
                }
                else if(c4_2.isChecked()){
                    score+=2;
                }
                else if(c4_3.isChecked()){
                    score+=3;
                }
                else if(c4_4.isChecked()){
                    score+=4;
                }

                if(c5_1.isChecked()){
                    score+=5;
                }
                else if(c5_2.isChecked()){
                    score+=4;
                }
                else if(c5_3.isChecked()){
                    score+=3;
                }
                else if(c5_4.isChecked()){
                    score+=2;
                }
                else if(c5_5.isChecked()){
                    score+=1;
                }

                if(c6_1.isChecked()){
                    score+=3;
                }
                else if(c6_2.isChecked()){
                    score+=2;
                }
                else if(c6_3.isChecked()){
                    score+=1;
                }

                if(c7_1.isChecked()){
                    score-=2;
                }
                else if(c7_2.isChecked()){
                    score+=2;
                }
                else if(c7_3.isChecked()){
                    score+=4;
                }
                else if(c7_4.isChecked()){
                    score+=6;
                }

                score = score / 32 * 100;
                if(score>80){
                    s="공격투자형";
                }
                else if(score>60){
                    s="적극투자형";
                }
                else if(score>40){
                    s="위험중립형";
                }
                else if(score>20){
                    s="안정추구형";
                }
                else{
                    s="안정형";
                }


                Context context= v.getContext();
                new AlertDialog.Builder(context)
                        .setTitle(name+"님의 투자성향").setMessage(score+"점 : "+s+"입니다")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent=new Intent(context, TestResultActivity.class);
                                intent.putExtra("score",score);
                                startActivity(intent);
                            }
                        }).show();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });

    }
}