package com.course.capstone.adapter;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.course.capstone.models.Cross;
import com.course.capstone.R;
import com.course.capstone.models.Concern;
import com.ramijemli.percentagechartview.PercentageChartView;

import java.util.ArrayList;
import java.util.List;

public class CrossAdapter extends RecyclerView.Adapter<CrossAdapter.ViewHolder> {

    //데이터 배열 선언
    private List<Cross> mList;

    public  class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private PercentageChartView percentageChartView;
        LinearLayout layoutManager;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.cat);
            percentageChartView = itemView.findViewById(R.id.scr);
        }
    }

    public void addItem(Cross data) {
        // 외부에서 item을 추가시킬 함수입니다.
        mList.add(data);
    }

    //생성자
    public CrossAdapter(List<Cross> list) {
        this.mList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_score, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("debug :", "List " + mList.get(position).getCategory());
        Integer integer = mList.get(position).getScore();
        if(integer == null){
            holder.percentageChartView.setProgress(0,true);
        }
        else{
            holder.percentageChartView.setProgress(mList.get(position).getScore(),true);
        }
        holder.textView.setText(mList.get(position).getCategory());

        //다 해줬는데도 GlideApp 에러가 나면 rebuild project를 해주자.

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}

