package com.course.capstone;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.course.capstone.adapter.CardAdapter;
import com.course.capstone.models.Card;
import com.course.capstone.models.CardInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class CardActivity extends AppCompatActivity {
    private RecyclerView RV;
    private RecyclerView.LayoutManager LM;
    private CardAdapter myAdapter;
    ArrayList<Card> allList;
    ArrayList<Card> list;
    ArrayList<Card> beneallList;
    ArrayList<Card> benelist;
    List<Card> cards;
    boolean isLoading = false;
    int itemPosition;
    int benePosition;

    String[] items = {"전체",
            "신한카드",
            "현대카드",
            "삼성카드",
            "KB국민카드",
            "롯데카드",
            "하나카드",
            "우리카드",
            "씨티카드",
            "NH농협카드",
            "IBK기업은행"};
    String[] benefits = {"전체","주유","쇼핑","대형마트","편의점","외식","카페/베이커리","영화","대중교통",
    "관리비","통신","교육","육아","문화","레저","항공마일리지","Priority Pass","프리미엄","하이패스","오토",
    "오토","의료","뷰티","금융","공공","체크카드겸용","포인트/캐시백","바우처","언제나할인","간편결제",
    "렌탈","경차유류환급","연회비지원","아이행복카드","국민행복카드","그린카드","THE CJ 카드"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_spinner);

        Spinner spinner = findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item,items
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        spinner.setAdapter(adapter);


        Spinner spinner2 = findViewById(R.id.spinner2);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item,benefits
        );

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);

        spinner2.setAdapter(adapter2);




        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-13-59-15-254.us-east-2.compute.amazonaws.com:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // 리싸이클러 뷰에 레이아웃을 설정해 준다. 이 레이아웃은xml 로 만들어 준 내용을 사용한다.
        RV = findViewById(R.id.cardRecomand);
        RV.setHasFixedSize(true);
        LM = new LinearLayoutManager(this);
        RV.setLayoutManager(LM);
        // 아이템들을 목록으로 만들어 준다. 목록은 ArrayList 객체를사용한다.


        CardInterface cardInterface = retrofit.create(CardInterface.class);




        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                itemPosition = position;
                if (items[position] == "전체"){
                    allList = new ArrayList<>();
                    list = new ArrayList<>();
                    Call<List<Card>> call = cardInterface.getAllCardinfo();

                    call.enqueue(new Callback<List<Card>>() {
                        @Override
                        public void onResponse(Call<List<Card>> call, Response<List<Card>> response) {
                            if(response.isSuccessful()){
                                cards = response.body();
                                // 목록을 어댑터에 연결해 준다.
                                firstData();
                                initAdapter();
                            }else {
                                Log.d(TAG, "onResponse1: Something Wrong");
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Card>> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "목록을 불러올 수 없습니다.", Toast.LENGTH_LONG).show();
                            ;
                            Log.d(TAG, "onFailure2: 게시물 목록 왜안나와");
                        }
                    });
                    initScrollListener();
                }
                else{
                    allList = new ArrayList<>();
                    list = new ArrayList<>();
                    Call<List<Card>> call = cardInterface.getCardinfoByCompanyname(items[position]);
                    call.enqueue(new Callback<List<Card>>() {
                    @Override
                    public void onResponse(Call<List<Card>> call, Response<List<Card>> response) {
                        if(response.isSuccessful()){
                            cards = response.body();
                            // 목록을 어댑터에 연결해 준다.
                            firstData();
                            initAdapter();
                        }else {
                            Log.d(TAG, "onResponse1: Something Wrong");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Card>> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "목록을 불러올 수 없습니다.", Toast.LENGTH_LONG).show();
                        ;
                        Log.d(TAG, "onFailure2: 게시물 목록 왜안나와");
                    }
                });
                    initScrollListener();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(benefits[position] == "전체"){

                }
                else{
                    beneallList = new ArrayList<>();
                    list = new ArrayList<>();
                    Call<List<Card>> call = cardInterface.getCardinfoByRewardsIn(benefits[position]);
                    call.enqueue(new Callback<List<Card>>() {
                        @Override
                        public void onResponse(Call<List<Card>> call, Response<List<Card>> response) {
                            if(response.isSuccessful()){
                                cards = response.body();
                                // 목록을 어댑터에 연결해 준다.
                                benefirstData();
                                initAdapter();
                            }else {
                                Log.d(TAG, "onResponse1: Something Wrong");
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Card>> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "목록을 불러올 수 없습니다.", Toast.LENGTH_LONG).show();
                            ;
                            Log.d(TAG, "onFailure2: 게시물 목록 왜안나와");
                        }
                    });
                    initScrollListener();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//        firstData();
//        initAdapter();
//        initScrollListener();
    }

    private void firstData() {
        for (Card card : cards) {
            if(benefits[benePosition] == "전체"){
                allList.add(card);
            }
            else if(card.getReward().contains(benefits[benePosition])){
                allList.add(card);
            }
        }
        for (int i=0; i<10; i++){
            list.add(allList.get(i));
        }
    }
    private void benefirstData() {
        for (Card card : cards) {
            if(card.getCompanyname().equals(items[itemPosition])){beneallList.add(card);
            Log.d("cardname",card.getCompanyname());}
            else if(items[itemPosition] == "전체"){
                beneallList.add(card);
            }
        }
        if(beneallList.size()>0){
        for (int i=0; i<beneallList.size(); i++){
            list.add(beneallList.get(i));
        }}
    }

    private void dataMore() {
        Log.d(TAG, "dataMore: ");
        list.add(null);
        myAdapter.notifyItemInserted(list.size() -1 );

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                list.remove(list.size() -1 );
                int scrollPosition = list.size();
                myAdapter.notifyItemRemoved(scrollPosition);
                int currentSize = scrollPosition;
                int nextLimit = currentSize + 10;

                for (int i=currentSize; i<nextLimit; i++) {
                    if (i == allList.size()) {
                        return;
                    }
                    list.add(allList.get(i));
                }

                myAdapter.notifyDataSetChanged();
                isLoading = false;
            }
        }, 1500);

    }

    private void initAdapter() {
        // 목록을 어댑터에 연결해 준다.
        myAdapter = new CardAdapter(list);
        // 어댑터를 뷰에 연결해 준다.
        RV.setAdapter(myAdapter);
    }

    // 리싸이클러뷰 이벤트시
    private void initScrollListener() {
        RV.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.d(TAG, "onScrollStateChanged: ");
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.d(TAG, "onScrolled: ");

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == list.size() - 1) {
                        dataMore();
                        isLoading = true;
                        Toast.makeText(CardActivity.this, "스크롤감지", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}

