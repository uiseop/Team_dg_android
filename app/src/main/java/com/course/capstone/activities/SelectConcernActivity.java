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
import com.course.capstone.models.DataManager;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SelectConcernActivity extends AppCompatActivity {

    private ListView listView;
    private Button btn;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://ec2-3-139-15-252.us-east-2.compute.amazonaws.com:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    DataManager dataManager = DataManager.getInstance();
    String parentid = dataManager.getUser().getId();

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

        adapter.addItem("쇼핑",parentid);
        adapter.addItem("주유",parentid);
        adapter.addItem("편의점",parentid);
        adapter.addItem("외식",parentid);
        adapter.addItem("카페/베이커리",parentid);
        adapter.addItem("영화",parentid);
        adapter.addItem("교육",parentid);
        adapter.addItem("문화",parentid);
        adapter.addItem("대중교통",parentid);
        adapter.addItem("의료",parentid);
        adapter.addItem("뷰티",parentid);
        adapter.addItem("대형마트",parentid);
        adapter.addItem("관리비",parentid);
        adapter.addItem("통신",parentid);
        adapter.addItem("육아",parentid);
        adapter.addItem("레저",parentid);
        adapter.addItem("항공마일리지",parentid);
        adapter.addItem("금융",parentid);
        adapter.addItem("Priority Pass",parentid);
        adapter.addItem("프리미엄",parentid);
        adapter.addItem("하이패스",parentid);
        adapter.addItem("오토",parentid);
        adapter.addItem("공공",parentid);
        adapter.addItem("체크카드겸용",parentid);
        adapter.addItem("포인트/캐시백",parentid);
        adapter.addItem("바우처",parentid);
        adapter.addItem("언제나할인",parentid);
        adapter.addItem("간편결제",parentid);
        adapter.addItem("렌탈",parentid);
        adapter.addItem("경차유류환급",parentid);
        adapter.addItem("연회비지원",parentid);
        adapter.addItem("아이행복카드",parentid);
        adapter.addItem("국민행복카드",parentid);
        adapter.addItem("그린카드",parentid);
        adapter.addItem("THE CJ 카드",parentid);
    }
}