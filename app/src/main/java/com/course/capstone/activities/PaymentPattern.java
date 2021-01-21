package com.course.capstone.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.course.capstone.R;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class PaymentPattern extends AppCompatActivity {

    PieChart graph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_pattern);

        graph = (PieChart)findViewById(R.id.graph);

        graph.addPieSlice(new PieModel("외식",60, Color.parseColor("#FF03DAC5")));
        graph.addPieSlice(new PieModel("여가",40, Color.parseColor("#FFAB40")));

        graph.startAnimation();
    }
}