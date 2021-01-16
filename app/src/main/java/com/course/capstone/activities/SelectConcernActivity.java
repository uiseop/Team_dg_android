package com.course.capstone.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.course.capstone.adapter.ConcernAdapter;
import com.course.capstone.R;

public class SelectConcernActivity extends AppCompatActivity {

    private ListView listView;
    private Button btn_next, btn_pre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_concern);

        btn_next = findViewById(R.id.button2);
        btn_pre = findViewById(R.id.button3);

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectConcernActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        btn_pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectConcernActivity.this, SelectBankActivity.class);
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