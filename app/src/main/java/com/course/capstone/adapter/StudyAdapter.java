package com.course.capstone.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.course.capstone.R;
import com.course.capstone.activities.MyInfoActivity;
import com.course.capstone.activities.ProductsActivity;
import com.course.capstone.activities.Sense2Activity;
import com.course.capstone.activities.SenseActivity;
import com.course.capstone.activities.TaxActivity;

import java.util.ArrayList;

public class StudyAdapter extends RecyclerView.Adapter<StudyAdapter.StudyViewHolder> {
    ArrayList<String> content = new ArrayList<>();

    int[] col = {R.color.bread, R.color.main, R.color.cursor,
            R.color.colorAccent, R.color.colorPrimaryDark, R.color.teal_200,
            R.color.blue, R.color.teal_700, R.color.purple_200, R.color.purple_500,
            R.color.purple_700, R.color.bread, R.color.main, R.color.cursor};


    public StudyAdapter(ArrayList<String> content) {

        this.content = content;
    }

    public class StudyViewHolder extends RecyclerView.ViewHolder {
        TextView myText;
        RelativeLayout cardView;

        public StudyViewHolder(View itemView) {
            super(itemView);

            myText = itemView.findViewById(R.id.studyitem);
            cardView = itemView.findViewById(R.id.card_view3);
        }
    }


    @Override
    public StudyAdapter.StudyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_study, parent, false);
        return new StudyAdapter.StudyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(StudyAdapter.StudyViewHolder holder, int position) {
        holder.cardView.setBackgroundResource(col[position]);
        holder.myText.setText(content.get(position));
        holder.myText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();

                if (content.get(position) == "세금") {
                    Intent intent = new Intent(context, TaxActivity.class);
                    context.startActivity(intent);
                } else if (content.get(position) == "일반상식2") {
                    Intent intent = new Intent(context, Sense2Activity.class);
                    context.startActivity(intent);
                } else if (content.get(position) == "일반상식") {
                    Intent intent = new Intent(context, SenseActivity.class);
                    context.startActivity(intent);
                } else if (content.get(position) == "금융상품") {
                    Intent intent = new Intent(context, ProductsActivity.class);
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

