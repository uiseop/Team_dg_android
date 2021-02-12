package com.course.capstone.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.course.capstone.R;
import com.course.capstone.activities.MyInfoActivity;
import com.course.capstone.activities.TaxActivity;

import java.util.ArrayList;

public class StudyAdapter extends RecyclerView.Adapter<StudyAdapter.StudyViewHolder> {
     ArrayList<String> content = new ArrayList<>();

      public StudyAdapter(ArrayList<String> content) {

        this.content = content;
    }
    public class StudyViewHolder extends RecyclerView.ViewHolder {
        TextView myText;

        public StudyViewHolder(View itemView) {
            super(itemView);

            myText = itemView.findViewById(R.id.studyitem);

        }
    }


    @Override
    public StudyAdapter.StudyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_study, parent, false);
        return new  StudyAdapter.StudyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(StudyAdapter.StudyViewHolder holder, int position) {

        holder.myText.setText(content.get(position));
        holder.myText.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                if (content.get(position) == "세금") {
                    Intent intent = new Intent(context, TaxActivity.class);
                    context.startActivity(intent);
                }

            }
        });
    }


    @Override
    public int getItemCount() {
        if (content == null) {
            return 0;
        } else {
            return content.size();
        }
    }



}

