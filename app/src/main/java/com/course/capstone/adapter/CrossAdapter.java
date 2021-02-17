package com.course.capstone.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.course.capstone.R;
import com.course.capstone.models.Cross;
import com.ramijemli.percentagechartview.PercentageChartView;

import java.util.List;

public class CrossAdapter extends RecyclerView.Adapter<CrossAdapter.ViewHolder> {

    //데이터 배열 선언
    private List<Cross> mList;
    int[] col = {R.color.bread, R.color.main, R.color.cursor,
    R.color.colorAccent, R.color.colorPrimaryDark, R.color.teal_200,
    R.color.blue,R.color.teal_700, R.color.purple_200, R.color.purple_500,
    R.color.purple_700,R.color.bread,R.color.main,R.color.cursor};

    public  class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private PercentageChartView percentageChartView;
        private RelativeLayout rl;


        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.cat);
            percentageChartView = itemView.findViewById(R.id.scr);
            rl = itemView.findViewById(R.id.back);
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
        holder.rl.setBackgroundResource(col[position]);

        //다 해줬는데도 GlideApp 에러가 나면 rebuild project를 해주자.

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}

