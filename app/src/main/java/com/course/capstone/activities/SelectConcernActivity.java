package com.course.capstone.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.course.capstone.MypageActivity;
import com.course.capstone.adapter.ConcernAdapter;
import com.course.capstone.R;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SelectConcernActivity extends AppCompatActivity {

    private ListView listView;
    private Button btn;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://ec2-13-59-15-254.us-east-2.compute.amazonaws.com:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_concern);

        btn = findViewById(R.id.concern_complete);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectConcernActivity.this, MypageActivity.class);
                startActivity(intent);
            }
        });


        ConcernAdapter adapter;
        adapter = new ConcernAdapter();
        listView = (ListView)findViewById(R.id.lv_concern);
        listView.setAdapter(adapter);

        adapter.addItem("쇼핑");
        adapter.addItem("주유");
        adapter.addItem("편의점");
        adapter.addItem("외식");
        adapter.addItem("카페/베이커리");
        adapter.addItem("영화");
        adapter.addItem("교육");
        adapter.addItem("문화");
        adapter.addItem("대중교통");
        adapter.addItem("의료");
        adapter.addItem("뷰티");
        
    }
}