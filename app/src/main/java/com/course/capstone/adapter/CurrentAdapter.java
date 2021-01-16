package com.course.capstone.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.course.capstone.models.ChartDTO;
import com.course.capstone.R;

import java.util.ArrayList;

public class CurrentAdapter extends RecyclerView.Adapter<CurrentAdapter.ViewHolder> {

    //데이터 배열 선언
    private ArrayList<ChartDTO> mList;

    public  class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView_name;
        private TextView textView_current, textView_release, texView_director;
        LinearLayout layoutManager;

        public ViewHolder(View itemView) {
            super(itemView);

            textView_name = itemView.findViewById(R.id.Country);
            textView_current = itemView.findViewById(R.id.current);
            textView_release = itemView.findViewById(R.id.price);
            texView_director = itemView.findViewById(R.id.rate);
        }
    }

    public void addItem(ChartDTO data) {
        // 외부에서 item을 추가시킬 함수입니다.
        mList.add(data);
    }

    //생성자
    public CurrentAdapter(ArrayList<ChartDTO> list) {
        this.mList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("debug :", "List " + mList.get(position).getTitle());
        Log.d("debug :", "List " + mList.get(position).getCurrent());
        Log.d("debug :", "List " + mList.get(position).getGapPrice());
        Log.d("debug :", "List " + mList.get(position).getGapRate());
        holder.textView_name.setText(mList.get(position).getTitle());
        holder.textView_current.setText(mList.get(position).getCurrent());
        holder.textView_release.setText(mList.get(position).getGapPrice());
        holder.texView_director.setText(mList.get(position).getGapRate());
        //다 해줬는데도 GlideApp 에러가 나면 rebuild project를 해주자.

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}

