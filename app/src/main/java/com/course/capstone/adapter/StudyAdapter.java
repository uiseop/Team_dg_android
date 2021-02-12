package com.course.capstone.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.course.capstone.R;
import com.course.capstone.activities.SelectBankActivity;
import com.course.capstone.activities.TaxActivity;

import java.util.ArrayList;

public class StudyAdapter extends RecyclerView.Adapter<StudyAdapter.StudyViewHolder> {
    private ArrayList<String> content=new ArrayList<>();
    public StudyAdapter(ArrayList<String> content) {

        this.content = content;
    }

    @NonNull
    @Override
    public StudyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_study, parent, false);
        return new StudyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(StudyViewHolder holder, int position) {
        holder.myText.setText(content.get(position));
        holder.myText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();

                if (content.get(position) == "세금") {
                    Intent intent = new Intent(context, TaxActivity.class);
                    context.startActivity(intent);
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return 0;
    }

    public class StudyViewHolder extends RecyclerView.ViewHolder{
        TextView myText;

        public StudyViewHolder(@NonNull View itemView)
        {
            super(itemView);

            myText = itemView.findViewById(R.id.study);

        }
    }


}
