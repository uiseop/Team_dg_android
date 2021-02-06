package com.course.capstone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.course.capstone.adapter.MyAdapter;
import com.course.capstone.models.DataItem;
import com.course.capstone.models.DataManager;

import java.util.ArrayList;

public class MypageActivity extends AppCompatActivity {

    private RecyclerView myIdentity,myContext;

    private RecyclerView.LayoutManager IManager,CManger;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);
        myIdentity=  findViewById(R.id.identity);
        myIdentity.setHasFixedSize(true);
        IManager = new LinearLayoutManager(this);


        myContext= findViewById(R.id.context);
        myContext.setHasFixedSize(true);
        CManger = new LinearLayoutManager(this);
        myIdentity.setLayoutManager(IManager);
        myContext.setLayoutManager(CManger);


        ArrayList<DataItem> id = new ArrayList<>();
        ArrayList<DataItem> context = new ArrayList<>();

        id.add(new DataItem("내정보",0));
        id.add(new DataItem("나의 소비패턴",0));
        id.add(new DataItem("은행 선택",0));
        id.add(new DataItem("관심사 선택",0));
        id.add(new DataItem("카드 추천",0));

        context.add(new DataItem("내가 쓴글",1));
        context.add(new DataItem("댓글 단 글",1));
        context.add(new DataItem("스크랩",1));


        MyAdapter idAdapter= new MyAdapter(id);
        MyAdapter contextAdapter= new MyAdapter(context);


        myIdentity.setAdapter(idAdapter);
        myContext.setAdapter(contextAdapter);

    }
}