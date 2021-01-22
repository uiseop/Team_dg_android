package com.course.capstone.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.course.capstone.R;
import com.course.capstone.adapter.PaymentAdapter;
import com.course.capstone.models.Payment;
import com.course.capstone.models.PaymentInterface;
import com.course.capstone.models.RetrofitInterface;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PaymentPattern extends AppCompatActivity {

    PieChart graph;
    TextView total_amount;

    PaymentAdapter paymentAdapter;
    ExpandableListView expandableListView;
    List<String> parentData;
    HashMap<String, List<String>> childData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_pattern);

        HashMap<String, Integer> apc = new HashMap<>();
        apc.put("편의점",0);
        apc.put("외식",0);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-13-59-15-254.us-east-2.compute.amazonaws.com:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
        PaymentInterface paymentInterface = retrofit.create(PaymentInterface.class);
        Call<List<Payment>> call = paymentInterface.getPaymentList();

        call.enqueue(new Callback<List<Payment>>() {
            @Override
            public void onResponse(Call<List<Payment>> call, Response<List<Payment>> response) {
                List<Payment> payment = response.body();

                for (int i=0; i<payment.size(); i++){
                    if (payment.get(i).getCategory().equals("편의점")){
                        apc.put("편의점",apc.get("편의점")+payment.get(i).getAmount());
                    }
                    else if(payment.get(i).getCategory().equals("외식")){
                        apc.put("외식",apc.get("외식")+payment.get(i).getAmount());
                    }
                }
                int total = 0;
                for (String key : apc.keySet()){
                    total += apc.get(key);
                }
                float convenience_store = Math.round((float)apc.get("편의점") / total * 100);
                float restaurant = Math.round((float)apc.get("외식") / total * 100);

                //그래프 나타내기
                graph = (PieChart)findViewById(R.id.graph);
                graph.setUsePercentValues(true);
                graph.getDescription().setEnabled(false);
                graph.setExtraOffsets(5,10,5,5);

                graph.setDragDecelerationFrictionCoef(0.95f);

                graph.setDrawHoleEnabled(false);
                graph.setHoleColor(Color.WHITE);
                graph.setTransparentCircleRadius(61f);

                ArrayList<PieEntry> pieEntries = new ArrayList<>();

                pieEntries.add(new PieEntry(convenience_store,"편의점"));
                pieEntries.add(new PieEntry(restaurant,"외식"));

                Description description = new Description();
                description.setText("카테고리"); //라벨
                description.setTextSize(15);
                graph.setDescription(description);

                graph.animateY(1000, Easing.EasingOption.EaseInOutCubic); //애니메이션

                PieDataSet dataSet = new PieDataSet(pieEntries,"Categories");
                dataSet.setSliceSpace(3f);
                dataSet.setSelectionShift(5f);
                dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

                PieData data = new PieData((dataSet));
                data.setValueTextSize(10f);
                data.setValueTextColor(Color.BLACK);

                graph.setData(data);

                total_amount =(TextView)findViewById(R.id.textView_totalamount);
                total_amount.setText(Integer.toString(total));
            }

            @Override
            public void onFailure(Call<List<Payment>> call, Throwable t) {
                Toast.makeText(getBaseContext(), "소비내역 불러오기에 실패하였습니다",Toast.LENGTH_LONG).show();
                Log.d("TAG","FAIL");
            }
        });

        expandableListView = (ExpandableListView)findViewById(R.id.expandable);
        prepareListData();

        paymentAdapter = new PaymentAdapter(this, parentData, childData);
        expandableListView.setAdapter(paymentAdapter);
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return false;
            }
        });
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        parentData.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
            }
        });
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        parentData.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();
            }
        });
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                return false;
            }
        });

    }
    private void prepareListData(){
        parentData = new ArrayList<String>();
        childData = new HashMap<String, List<String>>();

        parentData.add("카테고리1");
        parentData.add("카테고리2");

        List<String> category1 = new ArrayList<String>();
        category1.add("item1");
        category1.add("item2");
        category1.add("item3");

        List<String> category2 = new ArrayList<String>();
        category2.add("item1");
        category2.add("item2");
        category2.add("item3");

        childData.put(parentData.get(0),category1);
        childData.put(parentData.get(1),category2);
    }
}