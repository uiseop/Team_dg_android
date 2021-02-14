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
        Tax.add(new EducationCard(R.drawable.tax_1,"비과세"));
        Tax.add(new EducationCard(R.drawable.tax_1,"비과세소득"));
        CardviewAdapter cardviewAdapter= new CardviewAdapter(getApplicationContext(),Tax);

        taxrecyclerview.setAdapter(cardviewAdapter);
    }
}