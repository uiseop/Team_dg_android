package com.course.capstone.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.course.capstone.R;
import com.course.capstone.adapter.BoardAdapter;
import com.course.capstone.models.DataManager;
import com.course.capstone.models.Qna;
import com.course.capstone.models.QnaInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class Iwrite extends AppCompatActivity {
    private RecyclerView iwriteRecyclerView;
    private BoardAdapter iwriteAdapter;
    SwipeRefreshLayout refreshLayout;
    DataManager dataManager = DataManager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iwrite);
        iwriteRecyclerView = (RecyclerView) findViewById(R.id.i_write_recyclerview);
        iwriteRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false);
        iwriteRecyclerView.setLayoutManager(layoutManager);
        iwriteRecyclerView.addItemDecoration(new DividerItemDecoration(Iwrite.this, DividerItemDecoration.VERTICAL));
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.write_swipe_refresh);
        mywrited();
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mywrited();
            }
        });
    }

    public void mywrited() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-3-139-15-252.us-east-2.compute.amazonaws.com:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        QnaInterface qnainterface = retrofit.create(QnaInterface.class);
        Call<List<Qna>> call = qnainterface.getAll();

        call.enqueue(new Callback<List<Qna>>() {
            @Override
            public void onResponse(Call<List<Qna>> call, Response<List<Qna>> response) {
                if (response.isSuccessful()) {
                    List<Qna> qna = response.body();
                    List<Qna> iwriteqna = new ArrayList<>();
                    for (int i = 0; i < qna.size(); i++) {
                        if (qna.get(i).getQ_username().equals(dataManager.getUser().getUserid()) ) {
                            Log.d(qna.get(i).getTitle(), "onResponse1: Something Wrong");

                            iwriteqna.add(qna.get(i));


                        }
                    }
                    iwriteAdapter = new BoardAdapter(Iwrite.this, iwriteqna);
                    iwriteRecyclerView.setAdapter(iwriteAdapter);

                    refreshLayout.setRefreshing(false);


                } else {
                    Log.d(TAG, "onResponse1: Something Wrong");
                }
            }

            @Override
            public void onFailure(Call<List<Qna>> call, Throwable t) {
                Toast.makeText(getBaseContext(), "목록을 불러올 수 없습니다.", Toast.LENGTH_LONG).show();
                ;
                Log.d(TAG, "onFailure2: 게시물 목록 왜안나와");
            }
        });


    }


}