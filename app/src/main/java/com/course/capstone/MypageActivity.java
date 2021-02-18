package com.course.capstone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.course.capstone.adapter.MyAdapter;
import com.course.capstone.models.DataItem;
import com.course.capstone.models.DataManager;

import java.util.ArrayList;

public class MypageActivity extends AppCompatActivity {

    private RecyclerView myIdentity,myContext;
    ImageButton imageButton;
    private RecyclerView.LayoutManager IManager,CManger;
    DataManager dataManager = DataManager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);
        imageButton = findViewById(R.id.arrow);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        myIdentity=  findViewById(R.id.identity);
        myIdentity.setHasFixedSize(true);
        IManager = new LinearLayoutManager(this);
        TextView textView = findViewById(R.id.id);
        TextView textView1 = findViewById(R.id.name);
        TextView textView2 = findViewById(R.id.email);

        textView.setText(dataManager.getUser().getUserid());
        textView1.setText(dataManager.getUser().getName());
        textView2.setText(dataManager.getUser().getEmailaddress());


        myContext= findViewById(R.id.context);
        myContext.setHasFixedSize(true);
        CManger = new LinearLayoutManager(this);
        myIdentity.setLayoutManager(IManager);
        myContext.setLayoutManager(CManger);


        ArrayList<DataItem> id = new ArrayList<>();
        ArrayList<DataItem> context = new ArrayList<>();

        id.add(new DataItem("내정보",0));
        id.add(new DataItem("은행 선택",0));
        id.add(new DataItem("관심사 선택",0));

        context.add(new DataItem("내가 쓴글",1));
        context.add(new DataItem("댓글 단 글",1));


        MyAdapter idAdapter= new MyAdapter(id);
        MyAdapter contextAdapter= new MyAdapter(context);


        myIdentity.setAdapter(idAdapter);
        myContext.setAdapter(contextAdapter);

    }
}