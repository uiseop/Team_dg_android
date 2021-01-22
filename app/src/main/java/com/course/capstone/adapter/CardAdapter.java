package com.course.capstone.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.course.capstone.R;
import com.course.capstone.models.Card;

import java.util.ArrayList;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.MyViewHolder> {

    private ArrayList<Card> mList;
    public CardAdapter(ArrayList<Card> mList){
        this.mList = mList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgView_desert, imgView_card;
        TextView textView_name, textView_desert, textView_bene;
        LinearLayout layoutManager;

        public MyViewHolder(View itemView) {
            super(itemView);
            imgView_card = itemView.findViewById(R.id.imageView2);
            textView_name = itemView.findViewById(R.id.cardName);
            textView_bene = itemView.findViewById(R.id.benefit);
            textView_desert = itemView.findViewById(R.id.desert);
            imgView_desert = itemView.findViewById(R.id.desertimg);
        }
    }


    @NonNull
    @Override
    public CardAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card,parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MyViewHolder viewHolder = holder;
        viewHolder.imgView_card.setImageResource(mList.get(position).getImageCard());
        viewHolder.textView_name.setText(mList.get(position).getCardName());
        viewHolder.textView_bene.setText(mList.get(position).getDesc());
        viewHolder.textView_desert.setText(mList.get(position).getReward());
        viewHolder.imgView_desert.setImageResource(mList.get(position).getImageReward());
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }
}
