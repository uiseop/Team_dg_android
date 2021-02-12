package com.course.capstone.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.course.capstone.R;
import com.course.capstone.adapter.CardviewAdapter;
import com.course.capstone.adapter.MyAdapter;
import com.course.capstone.models.EducationCard;

import java.util.ArrayList;

public class TaxActivity extends AppCompatActivity {
    private RecyclerView taxrecyclerview;

    private RecyclerView.LayoutManager TManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tax);
        taxrecyclerview=  findViewById(R.id.info_recyclerview);
        taxrecyclerview.setHasFixedSize(true);
        TManager = new LinearLayoutManager(this);
        taxrecyclerview.setLayoutManager(TManager);
        ArrayList<EducationCard> Tax = new ArrayList<>();
        Tax.add(new EducationCard(R.drawable.tax_1,"비과세","국세청 등이 세금을 부과하는 과세권이  처음부터 없는 것으로, 세금 자체가 발생하지 않으므로 신고의 의무도 없다. 감면vs비과세 감면: 감면요건을 충족한 뒤 신고하고 감면 신청을 해야하는 등 신고의무와 납세협력의무가 있다. 비과세: 당초 세금 자체가 발생하지 않으므로 세금을 신고,신청 납부할 필요가 없다. "));
        CardviewAdapter cardviewAdapter= new CardviewAdapter(getApplicationContext(),Tax);

        taxrecyclerview.setAdapter(cardviewAdapter);
    }
}