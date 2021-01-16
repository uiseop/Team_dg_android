package com.course.capstone.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.course.capstone.models.ChartDTO;
import com.course.capstone.R;

import java.util.ArrayList;

public class RateRecyclerAdapter extends RecyclerView.Adapter<RateRecyclerAdapter.ItemViewHolder> {

    private ArrayList<ChartDTO> listData = new ArrayList<>();

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_board, viewGroup, false);
        return new
                ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int i) {
//        itemViewHolder.onBind(listData.get(i));
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
    void addItem(ChartDTO data) {
        // 외부에서 item을 추가시킬 함수입니다.
        listData.add(data);
    }
    class ItemViewHolder extends RecyclerView.ViewHolder{

        private TextView txt_ranktNum, txt_chartName, txt_chartTitle;
        private ImageView img_chart;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
//            txt_chartTitle = itemView.findViewById(R.id.txt_chartTitle);
            txt_chartName = itemView.findViewById(R.id.current);
            txt_ranktNum  = itemView.findViewById(R.id.price);
            img_chart     = itemView.findViewById(R.id.rate);

        }

//        void onBind(ChartDTO data){
//            txt_ranktNum.setText(datae.getRankNum());
//            txt_chartName.setText(data.getName());
//            txt_chartTitle.setText(data.getTitle());
//
//            Glide.with(itemView.getContext()).load(data.getImageUrl()).into(img_chart);
//
//        }
    }
}