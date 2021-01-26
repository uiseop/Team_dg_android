package com.course.capstone.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
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
import com.course.capstone.models.DataManager;

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

    DataManager dataManager = DataManager.getInstance();
    String parentid = dataManager.getUser().getId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_bank);

        btn = (Button)findViewById(R.id.bank_complete);

        BankAdapter adapter;
        adapter = new BankAdapter();

        listView = (ListView)findViewById(R.id.bank_lv);
        listView.setAdapter(adapter);


        adapter.addItem(ContextCompat.getDrawable(this,R.drawable.kakao),"카카오뱅크",0,parentid);
        adapter.addItem(ContextCompat.getDrawable(this,R.drawable.nh),"농협은행",0,parentid);
        adapter.addItem(ContextCompat.getDrawable(this,R.drawable.shinhan),"신한은행",0,parentid);
        adapter.addItem(ContextCompat.getDrawable(this,R.drawable.woori),"우리은행",0,parentid);
        adapter.addItem(ContextCompat.getDrawable(this,R.drawable.ibk),"기업은행",0,parentid);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                SparseBooleanArray checkeditems =listView.getCheckedItemPositions();
//                Log.d("check",checkeditems.toString());
                Intent intent = new Intent(SelectBankActivity.this, MypageActivity.class);
                startActivity(intent);
            }
        });
    }
}