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
import com.course.capstone.models.Bank;
import com.course.capstone.models.BankInterface;
import com.course.capstone.models.Child;
import com.course.capstone.models.DataManager;
import com.course.capstone.models.FCMInterface;
import com.course.capstone.models.GenericResponse;
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

import java.text.NumberFormat;
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
    HashMap<String, ArrayList<Child>> childData;
    int alarmbalance = 100000;
    String dotnum = NumberFormat.getInstance().format(alarmbalance);
    int totalbalance = 0;
    String msg = "잔고가 "+dotnum+" 미만입니다. 주의하세요!";
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://ec2-3-139-15-252.us-east-2.compute.amazonaws.com:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_pattern);

        HashMap<String, Integer> hashMap = new HashMap<>();
        parentData = new ArrayList<String>();
        childData = new HashMap<String, ArrayList<Child>>();

        HashMap<String, Integer> apc = new HashMap<>();
        apc.put("편의점", 0);
        apc.put("외식", 0);
        apc.put("여가", 0);

        ArrayList<Child> restaurant = new ArrayList<Child>();
        ArrayList<Child> convenience_store = new ArrayList<Child>();
        ArrayList<Child> leisure = new ArrayList<Child>();

        DataManager dataManager = DataManager.getInstance();
        final RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
        BankInterface bankInterface = retrofit.create(BankInterface.class);
        PaymentInterface paymentInterface = retrofit.create(PaymentInterface.class);
        Call<List<Bank>> call2 = bankInterface.getByParentId((dataManager.getUser().getId()));
        Callback<List<Bank>> callback = new Callback<List<Bank>>() {
            @Override
            public void onResponse(Call<List<Bank>> call, Response<List<Bank>> response) {
                List<Bank> bank = response.body();
                //bank는 call2를  통해서 불러온 bank list
                for (int i=0; i<bank.size();i++){
                    totalbalance += bank.get(i).getBalance();
                }
                Log.d("total", String.valueOf(totalbalance));
                Log.d("alaram", String.valueOf(alarmbalance));

                if(totalbalance < alarmbalance) {
                    HashMap<String, Object> alarm = new HashMap<>();
                    alarm.put("message",msg);
                    alarm.put("userid",dataManager.getUser().getId());
                    Log.d("msg",msg);
                    Log.d("userid",dataManager.getUser().getId());

                    FCMInterface fcmInterface = retrofit.create(FCMInterface.class);
                    Call<GenericResponse> call3 = fcmInterface.balance(alarm);
                    call3.enqueue(new Callback<GenericResponse>() {
                        @Override
                        public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {
                            Log.d("TAG","success");
                        }

                        @Override
                        public void onFailure(Call<GenericResponse> call, Throwable t) {
                            Log.d("TAG","fail");
                        }
                    });
                }

                for (int j = 0; j < bank.size(); j++) {
                    Call<List<Payment>> call1 = paymentInterface.getByParentaccount(bank.get(j).getAccount());
                    //call1은 은행별 account를 통해 불러온 payment list
                    call1.enqueue(new Callback<List<Payment>>() {
                        @Override
                        public void onResponse(Call<List<Payment>> call, Response<List<Payment>> response) {
                            List<Payment> payment = response.body();
                            for (int i = 0; i < payment.size(); i++) {
                                if (parentData.contains(payment.get(i).getCategory())) {
                                    if (payment.get(i).getCategory().equals("편의점")) {
                                        convenience_store.add(new Child(payment.get(i).getShopname(), payment.get(i).getAmount()));
                                    } else if (payment.get(i).getCategory().equals("외식")) {
                                        restaurant.add(new Child(payment.get(i).getShopname(), payment.get(i).getAmount()));
                                    } else if (payment.get(i).getCategory().equals("여가")) {
                                        leisure.add(new Child(payment.get(i).getShopname(), payment.get(i).getAmount()));
                                    }
                                } else {
                                    parentData.add(payment.get(i).getCategory());
                                    if (payment.get(i).getCategory().equals("편의점")) {
                                        convenience_store.add(new Child(payment.get(i).getShopname(), payment.get(i).getAmount()));
                                    } else if (payment.get(i).getCategory().equals("외식")) {
                                        restaurant.add(new Child(payment.get(i).getShopname(), payment.get(i).getAmount()));
                                    } else if (payment.get(i).getCategory().equals("여가")) {
                                        leisure.add(new Child(payment.get(i).getShopname(), payment.get(i).getAmount()));
                                    }
                                }
                            }

                            childData.put("외식", restaurant);
                            childData.put("편의점", convenience_store);
                            childData.put("여가",leisure);

                            for (int i = 0; i < payment.size(); i++) {
                                if (payment.get(i).getCategory().equals("편의점")) {
                                    apc.put("편의점", apc.get("편의점") + payment.get(i).getAmount());
                                } else if (payment.get(i).getCategory().equals("외식")) {
                                    apc.put("외식", apc.get("외식") + payment.get(i).getAmount());
                                } else if (payment.get(i).getCategory().equals("여가")) {
                                    apc.put("여가", apc.get("여가") + payment.get(i).getAmount());
                                }
                            }
                            int total = 0;
                            for (String key : apc.keySet()) {
                                total += apc.get(key);
                            }
                            float convenience_store = Math.round((float) apc.get("편의점") / total * 100);
                            float restaurant = Math.round((float) apc.get("외식") / total * 100);
                            float leisure = Math.round((float) apc.get("여가") / total * 100);

                            //그래프 나타내기
                            graph = findViewById(R.id.graph);
                            graph.setUsePercentValues(true);
                            graph.getDescription().setEnabled(false);
                            graph.setExtraOffsets(5, 10, 5, 5);

                            graph.setDragDecelerationFrictionCoef(0.95f);

                            graph.setDrawHoleEnabled(false);
                            graph.setHoleColor(Color.WHITE);
                            graph.setTransparentCircleRadius(61f);

                            ArrayList<PieEntry> pieEntries = new ArrayList<>();

                            if(convenience_store != 0){pieEntries.add(new PieEntry(convenience_store, "편의점"));}
                            if(restaurant != 0){pieEntries.add(new PieEntry(restaurant, "외식"));}
                            if(leisure != 0){pieEntries.add(new PieEntry(leisure, "여가"));}

                            Description description = new Description();
                            description.setText("카테고리"); //라벨
                            description.setTextSize(15);
                            graph.setDescription(description);

                            graph.animateY(1000, Easing.EasingOption.EaseInOutCubic); //애니메이션

                            PieDataSet dataSet = new PieDataSet(pieEntries, "Categories");
                            dataSet.setSliceSpace(3f);
                            dataSet.setSelectionShift(5f);
                            dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

                            PieData data = new PieData((dataSet));
                            data.setValueTextSize(10f);
                            data.setValueTextColor(Color.BLACK);

                            graph.setData(data);

                            total_amount = (TextView) findViewById(R.id.textView_totalamount);
                            total_amount.setText(Integer.toString(total));
                        }

                        @Override
                        public void onFailure(Call<List<Payment>> call, Throwable t) {
                            Toast.makeText(getBaseContext(), "소비내역 불러오기에 실패하였습니다", Toast.LENGTH_LONG).show();
                            Log.d("TAG", "FAIL");
                        }
                    });
                }


            }

            @Override
            public void onFailure(Call<List<Bank>> call, Throwable t) {

            }
        };
        call2.enqueue(callback);


        expandableListView = (ExpandableListView) findViewById(R.id.expandable);

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
//                Toast.makeText(getApplicationContext(),
//                        parentData.get(groupPosition) + " Expanded",
//                        Toast.LENGTH_SHORT).show();
            }
        });
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
//                Toast.makeText(getApplicationContext(),
//                        parentData.get(groupPosition) + " Collapsed",
//                        Toast.LENGTH_SHORT).show();
            }
        });
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                return false;
            }
        });

    }

//    private void prepareListData() {
//
//        HashMap<String, Integer> hashMap = new HashMap<>();
//        parentData = new ArrayList<String>();
//        childData = new HashMap<String, ArrayList<Child>>();
//
//        ArrayList<Child> restaurant = new ArrayList<Child>();
//        ArrayList<Child> convenience_store = new ArrayList<Child>();
//        DataManager dataManager = DataManager.getInstance();
//        final RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
//        PaymentInterface paymentInterface = retrofit.create(PaymentInterface.class);
//        BankInterface bankInterface = retrofit.create(BankInterface.class);
//        Call<List<Bank>> call2 = bankInterface.getByParentId(dataManager.getUser().getId());
//        call2.enqueue(new Callback<List<Bank>>() {
//            @Override
//            public void onResponse(Call<List<Bank>> call, Response<List<Bank>> response) {
//                List<Bank> bank = response.body();
//                for (int i = 0; i < bank.size(); i++) {
//                    int parentAccount = bank.get(i).getAccount();
//                    Call<List<Payment>> call1 = paymentInterface.getByParentaccount(parentAccount);
//                    call1.enqueue(new Callback<List<Payment>>() {
//                        @Override
//                        public void onResponse(Call<List<Payment>> call, Response<List<Payment>> response) {
//                            List<Payment> payment = response.body();
//
//                            for (int i = 0; i < payment.size(); i++) {
//                                if (parentData.contains(payment.get(i).getCategory())) {
//                                    if (payment.get(i).getCategory().equals("편의점")) {
//                                        convenience_store.add(new Child(payment.get(i).getShopname(), payment.get(i).getAmount()));
//                                    } else if (payment.get(i).getCategory().equals("외식")) {
//                                        restaurant.add(new Child(payment.get(i).getShopname(), payment.get(i).getAmount()));
//                                    }
//                                } else {
//                                    parentData.add(payment.get(i).getCategory());
//                                    if (payment.get(i).getCategory().equals("편의점")) {
//                                        convenience_store.add(new Child(payment.get(i).getShopname(), payment.get(i).getAmount()));
//                                    } else if (payment.get(i).getCategory().equals("외식")) {
//                                        restaurant.add(new Child(payment.get(i).getShopname(), payment.get(i).getAmount()));
//                                    }
//                                }
//                            }
//
//                            childData.put("외식", restaurant);
//                            childData.put("편의점", convenience_store);
//                        }
//
//                        @Override
//                        public void onFailure(Call<List<Payment>> call, Throwable t) {
//                            Log.d("fail", "prepareListData FAILED");
//                        }
//                    });
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<List<Bank>> call, Throwable t) {
//
//            }
//        });
//
//
//    }
}