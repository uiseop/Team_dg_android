package com.course.capstone.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.course.capstone.R;
import com.course.capstone.activities.education.YoutubeActivity;
import com.course.capstone.models.Youtube;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class YoutubeAdapter extends RecyclerView.Adapter<YoutubeAdapter.MyViewHolder> {
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView myText;
        ImageView myImageView;

        MyViewHolder(View view) {
            super(view);
            myText = view.findViewById(R.id.textView);
            myImageView = view.findViewById(R.id.imageView6);
        }
    }

    private ArrayList<Youtube> youtubeList;

    public YoutubeAdapter(ArrayList<Youtube> youtubeList) {
        this.youtubeList = youtubeList;
    }

    @Override
    public YoutubeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_youtube, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(YoutubeAdapter.MyViewHolder holder, final int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.myText.setText(youtubeList.get(position).getTitle());
        Picasso.get().load(youtubeList.get(position).getImgurl()).into(myViewHolder.myImageView);

        myViewHolder.myText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, YoutubeActivity.class);
                intent.putExtra("address", youtubeList.get(position).getAddress());
                context.startActivity(intent);
            }
        });
        myViewHolder.myImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, YoutubeActivity.class);
                intent.putExtra("address", youtubeList.get(position).getAddress());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return youtubeList.size();
    }


}