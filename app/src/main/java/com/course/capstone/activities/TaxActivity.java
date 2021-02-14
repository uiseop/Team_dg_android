package com.course.capstone.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Build;
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
        // Check if we're running on Android 5.0 or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Apply activity transition
        } else {
            // Swap without transition
        }

        taxrecyclerview=  findViewById(R.id.info_recyclerview);
        taxrecyclerview.setHasFixedSize(true);
        TManager = new LinearLayoutManager(this);
        taxrecyclerview.setLayoutManager(TManager);
        ArrayList<EducationCard> Tax = new ArrayList<>();
        Tax.add(new EducationCard(R.drawable.tax_1,"비과세","국세청 등이 세금을 부과하는 과세권이  처음부터 없는 것으로, 세금 자체가 발생하지 않으므로 신고의 의무도 없다.감면vs비과세 감면: 감면요건을 충족한 뒤 신고하고 감면 신청을 해야하는 등 신고의무와 납세협력의무가 있다. 비과세: 당초 세금 자체가 발생하지 않으므로 세금을 신고,신청 납부할 필요가 없다."));
        Tax.add(new EducationCard(R.drawable.tax_1,"비과세소득","비과세 소득은 세법에서 특별히 과세하지 않는 소득으로 규정한 소득이다.근로자 본인의 학자금, 실업급여, 출산&보육수당, 숙직료, 자가 운전 보조금등이 있다."));

        CardviewAdapter cardviewAdapter= new CardviewAdapter(getApplicationContext(),Tax);

        taxrecyclerview.setAdapter(cardviewAdapter);
    }
}