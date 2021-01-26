package com.course.capstone.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.course.capstone.R;
import com.course.capstone.models.Interest;

import java.util.ArrayList;

public class InterestAdapter extends BaseAdapter {
    LayoutInflater layoutInflater;
    ArrayList<Interest> interests;

    @Override
    public int getCount() {
        return interests.size();
    }

    @Override
    public Object getItem(int position) {
        return interests.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view =  layoutInflater.inflate(R.layout.item_interest,null);
        TextView degree = (TextView)view.findViewById(R.id.textView18);
        TextView won = (TextView)view.findViewById(R.id.textView21);

        degree.setText(interests.get(position).getDegree()+"회차");
        won.setText(interests.get(position).getWon()+"원");

        return view;
    }
}
