package com.course.capstone.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.course.capstone.MainActivity;
import com.course.capstone.R;
import com.course.capstone.activities.test.test1;
import com.course.capstone.activities.test.test2;
import com.course.capstone.activities.test.test3;
import com.course.capstone.activities.test.test4;
import com.course.capstone.activities.test.test5;

public class TestResultActivity extends AppCompatActivity {

    Button result;
    TextView a,b,c,d,e;
    TextView a1,b1,c1,d1,e1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_result);

        result = findViewById(R.id.button_testresult);
        a = findViewById(R.id.textView_dkswjdgud);
        b = findViewById(R.id.textView_dkswjdcnrngud);
        c = findViewById(R.id.textView_dnlgjawndflqgud);
        d = findViewById(R.id.textView_wjrrmrxnwkgud);
        e = findViewById(R.id.textView_rhdrurxnwkgud);
        a1 = findViewById(R.id.textView20);
        b1 = findViewById(R.id.textView2040);
        c1 = findViewById(R.id.textView4060);
        d1 = findViewById(R.id.textView6080);
        e1 = findViewById(R.id.textView80);

        double score = getIntent().getDoubleExtra("score",0);
        System.out.println(score);

        if(score <= 20){
            a.setTextColor(Color.parseColor("#FF03DAC5"));
            a1.setTextColor(Color.parseColor("#FF03DAC5"));
        }
        else if (score <= 40){
            b.setTextColor(Color.parseColor("#FF03DAC5"));
            b1.setTextColor(Color.parseColor("#FF03DAC5"));
        }
        else if (score <= 60){
            c.setTextColor(Color.parseColor("#FF03DAC5"));
            c1.setTextColor(Color.parseColor("#FF03DAC5"));
        }
        else if (score <= 80){
            d.setTextColor(Color.parseColor("#FF03DAC5"));
            d1.setTextColor(Color.parseColor("#FF03DAC5"));
        }
        else {
            e.setTextColor(Color.parseColor("#FF03DAC5"));
            e1.setTextColor(Color.parseColor("#FF03DAC5"));
        }

        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (score <= 20){
                    Intent i = new Intent(TestResultActivity.this, test1.class);
                    startActivity(i);
                }
                else if (score <= 40){
                    Intent i = new Intent(TestResultActivity.this, test2.class);
                    startActivity(i);
                }
                else if (score <= 60){
                    Intent i = new Intent(TestResultActivity.this, test3.class);
                    startActivity(i);
                }
                else if (score <= 80){
                    Intent i = new Intent(TestResultActivity.this, test4.class);
                    startActivity(i);
                }
                else{
                    Intent i = new Intent(TestResultActivity.this, test5.class);
                    startActivity(i);
                }
            }
        });
    }
}