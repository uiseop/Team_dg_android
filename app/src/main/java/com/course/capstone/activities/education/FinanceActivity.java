package com.course.capstone.activities.education;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.course.capstone.R;

public class FinanceActivity extends AppCompatActivity {

    Button button1;
    Button button2;
    Button button3;
    Button button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finance);
        button1 = (Button) findViewById(R.id.button_finance1);
        button2 = (Button) findViewById(R.id.button_finance2);
        button3 = (Button) findViewById(R.id.button_finance3);
        button4 = (Button) findViewById(R.id.button_finance4);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, YoutubeListActivity.class);
                intent.putExtra("category1", "금융교육");
                intent.putExtra("category2", "은행");
                context.startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, YoutubeListActivity.class);
                intent.putExtra("category1", "금융교육");
                intent.putExtra("category2", "보험");
                context.startActivity(intent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, YoutubeListActivity.class);
                intent.putExtra("category1", "금융교육");
                intent.putExtra("category2", "증권");
                context.startActivity(intent);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, YoutubeListActivity.class);
                intent.putExtra("category1", "금융교육");
                intent.putExtra("category2", "전체");
                context.startActivity(intent);
            }
        });
    }
}