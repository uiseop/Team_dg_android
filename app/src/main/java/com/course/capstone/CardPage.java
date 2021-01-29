package com.course.capstone;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.course.capstone.adapter.CardAdapter;
import com.course.capstone.models.Card;
import com.course.capstone.models.CardInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class CardPage extends AppCompatActivity{
    private RecyclerView RV;
    private RecyclerView.LayoutManager LM;
    private CardAdapter myAdapter;
    ArrayList<Card> allList = new ArrayList<>();
    ArrayList<Card> list = new ArrayList<>();
    List<Card> cards;
    boolean isLoading = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myowncard);

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

//        firstData();
//        initAdapter();
//        initScrollListener();
    }

    private void firstData() {
        for (Card card : cards) {
            allList.add(card);
        }
        for (int i=0; i<10; i++){
            list.add(allList.get(i));
        }
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
                        Toast.makeText(CardPage.this, "스크롤감지", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
