package com.course.capstone.activities.education;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.course.capstone.R;
import com.course.capstone.adapter.YoutubeAdapter;
import com.course.capstone.models.Edu;
import com.course.capstone.models.EduInterface;
import com.course.capstone.models.Youtube;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class YoutubeListActivity extends AppCompatActivity {
    private RecyclerView myRecyclerView;
    private RecyclerView.LayoutManager myLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_list);

        myRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        myRecyclerView.setHasFixedSize(true);
        myLayoutManager = new LinearLayoutManager(this);
        myRecyclerView.setLayoutManager(myLayoutManager);

        ArrayList<Youtube> youtubeList = new ArrayList<>();
        Intent intent = getIntent();
        String category1 = intent.getStringExtra("category1");
        String category2 = intent.getStringExtra("category2");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-3-139-15-252.us-east-2.compute.amazonaws.com:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Call<List<Edu>> call;
        EduInterface eduInterface = retrofit.create(EduInterface.class);
        if(category2.equals("전체"))
            call = eduInterface.getEduByEducategory(category1);
        else
            call = eduInterface.getEduByEducategory(category2);
        call.enqueue(new Callback<List<Edu>>() {
            @Override
            public void onResponse(Call<List<Edu>> call, Response<List<Edu>> response) {
                List<Edu> edulist = response.body();
                for (int i = 0; i < edulist.size(); i++) {
                    Edu edu = edulist.get(i);
                    if (edu.getEducategory().contains(category1)) {
                        youtubeList.add(new Youtube(edu.getEduname(), edu.getEduurl(), edu.getEduimgurl()));
                        Log.d("title", edu.getEduname());
                        Log.d("url", edu.getEduurl());
                        Log.d("imgurl", edu.getEduimgurl());
                    }
                }

                YoutubeAdapter youtubeAdapter = new YoutubeAdapter(youtubeList);
                myRecyclerView.setAdapter(youtubeAdapter);

            }

            @Override
            public void onFailure(Call<List<Edu>> call, Throwable t) {
                Log.d("Fail", "호출 실패");
            }
        });


    }
}