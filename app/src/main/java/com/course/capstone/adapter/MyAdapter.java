package com.course.capstone.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.course.capstone.CardPage;
import com.course.capstone.activities.LoginActivity;
import com.course.capstone.activities.PaymentPattern;
import com.course.capstone.activities.SelectBankActivity;
import com.course.capstone.activities.SelectConcernActivity;
import com.course.capstone.models.DataItem;
import com.course.capstone.activities.MyInfoActivity;
import com.course.capstone.R;
import com.course.capstone.models.DataManager;
import com.course.capstone.models.User;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_HEADER2 = 1;
    DataManager dataManager=DataManager.getInstance();

    public class idHolder extends RecyclerView.ViewHolder{
        TextView myText;

        idHolder(View itemView)
        {
            super(itemView);

            myText = itemView.findViewById(R.id.identity);
        }
    }

    public class contextHolder extends RecyclerView.ViewHolder{
        TextView myText;


        contextHolder(View itemView)
        {
            super(itemView);

            myText = itemView.findViewById(R.id.content);
        }
    }

    private ArrayList<DataItem> content;

    public MyAdapter(ArrayList<DataItem> content) {

        this.content = content;
    }



    @Override
    public  RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        if(viewType == TYPE_HEADER){
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent,false);
            return new idHolder(v);
        }
        else {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item2, parent, false);
            return new contextHolder(v);
        }
        }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        if(viewHolder instanceof idHolder){
            (( idHolder)viewHolder).myText.setText(content.get(position).getTitle());

            ((  idHolder)viewHolder).myText.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {
                    Context context = v.getContext();

                    if(content.get(position).getTitle()=="내정보"){
                        Intent intent = new Intent(context, MyInfoActivity.class);
                        context.startActivity(intent);
                    }
                    else if(content.get(position).getTitle()=="로그아웃"){
                        new AlertDialog.Builder(context)
                                .setTitle("로그아웃").setMessage("로그아웃 하시겠습니까?")
                                .setPositiveButton("로그아웃", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        Intent i = new Intent(context, LoginActivity.class);
                                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                        User user1 = new User();
                                        dataManager.setUser(user1);
//                                        i.putExtra("KILL",true);
                                        context.startActivity(i);
//                                        Log.d("정보",dataManager.getUser().toString());
                                    }
                                })
                                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {

                                    }
                                })
                                .show();
                    }
                    else if(content.get(position).getTitle()=="은행 선택"){
                        Intent intent = new Intent(context, SelectBankActivity.class);
                        context.startActivity(intent);
                    }
                    else if(content.get(position).getTitle()=="관심사 선택"){
                        Intent intent = new Intent(context, SelectConcernActivity.class);
                        context.startActivity(intent);
                    }
                    else if(content.get(position).getTitle()=="나의 소비패턴"){
                        Intent intent = new Intent(context, PaymentPattern.class);
                        context.startActivity(intent);
                    }
                    else if(content.get(position).getTitle()=="카드 추천"){
                        Intent intent = new Intent(context, CardPage.class);
                        context.startActivity(intent);
                    }
                }

            });



        }
        else {
            ((contextHolder)viewHolder).myText.setText(content.get(position).getTitle());
        }


    }
    @Override
    public int getItemViewType(int position) {
        if (content.get(position). getViewType() == 0) {
            return  TYPE_HEADER ;
        } else {
            return TYPE_HEADER2;
        }
    }

    @Override
    public int getItemCount() {

        return content.size();
    }

}

