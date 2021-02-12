package com.course.capstone.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.course.capstone.R;
import com.course.capstone.adapter.MyAdapter;
import com.course.capstone.adapter.StudyAdapter;
import com.course.capstone.models.DataItem;

import java.util.ArrayList;

public class StudyActivity extends AppCompatActivity {
    private RecyclerView studyrecyclerview;

    private RecyclerView.LayoutManager SManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);
        studyrecyclerview=  findViewById(R.id.study_recyclerview);
        studyrecyclerview.setHasFixedSize(true);
        SManager = new LinearLayoutManager(this);
        studyrecyclerview.setLayoutManager(SManager);
        ArrayList<String> study = new ArrayList<>();
        study.add("세계경제");
        study.add("대출");
        study.add("저축");
        study.add("금융상품");
        study.add("보험");
        study.add("세금");
        study.add("신용");
        study.add("연금");
        study.add("증권");
        study.add("카드");
        study.add("펀드");
        study.add("일반상식");
        study.add("일반상식2");
        study.add("일반상식3");


        StudyAdapter studyAdapter= new StudyAdapter(study);
        studyrecyclerview.setAdapter(studyAdapter);


    }
}