package com.course.capstone.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.course.capstone.adapter.ListViewAdapter;
import com.course.capstone.R;

public class SelectBankActivity extends AppCompatActivity {

    private ListView listView;
    private Button btn1,btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_bank);

        btn1 = (Button)findViewById(R.id.button);
        btn2 = (Button)findViewById(R.id.select_pre);

        ListViewAdapter adapter;

        adapter = new ListViewAdapter();

        listView = (ListView)findViewById(R.id.bank_lv);
        listView.setAdapter(adapter);

        adapter.addItem(ContextCompat.getDrawable(this,R.drawable.kakao),"카카오뱅크");
        adapter.addItem(ContextCompat.getDrawable(this,R.drawable.nh),"농협은행");
        adapter.addItem(ContextCompat.getDrawable(this,R.drawable.shinhan),"신한은행");
        adapter.addItem(ContextCompat.getDrawable(this,R.drawable.woori),"우리은행");
        adapter.addItem(ContextCompat.getDrawable(this,R.drawable.ibk),"기업은행");

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectBankActivity.this,SelectConcernActivity.class);
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectBankActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}