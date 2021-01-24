package com.course.capstone.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.course.capstone.MypageActivity;
import com.course.capstone.adapter.BankAdapter;
import com.course.capstone.R;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SelectBankActivity extends AppCompatActivity {

    private ListView listView;
    private Button btn;
    private EditText acc;


    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://ec2-13-59-15-254.us-east-2.compute.amazonaws.com:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_bank);

        btn = (Button)findViewById(R.id.bank_complete);
        acc = (EditText) findViewById(R.id.account);

        BankAdapter adapter;
        adapter = new BankAdapter();

        listView = (ListView)findViewById(R.id.bank_lv);
        listView.setAdapter(adapter);

        adapter.addItem(ContextCompat.getDrawable(this,R.drawable.kakao),"카카오뱅크","123");
        adapter.addItem(ContextCompat.getDrawable(this,R.drawable.nh),"농협은행","456");
        adapter.addItem(ContextCompat.getDrawable(this,R.drawable.shinhan),"신한은행","234");
        adapter.addItem(ContextCompat.getDrawable(this,R.drawable.woori),"우리은행","567");
        adapter.addItem(ContextCompat.getDrawable(this,R.drawable.ibk),"기업은행","678");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = acc.getText().toString();
                //이 account를 db에 저장해주기
                Intent intent = new Intent(SelectBankActivity.this, MypageActivity.class);
                startActivity(intent);
            }
        });
    }
}