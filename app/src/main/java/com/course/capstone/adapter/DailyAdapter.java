package com.course.capstone.adapter;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.course.capstone.DailyDetailActivity;
import com.course.capstone.R;
import com.course.capstone.models.Knowledge;

import java.util.ArrayList;
import java.util.List;

public class DailyAdapter extends RecyclerView.Adapter<DailyAdapter.DailyViewHolder> {
    static final String TAG = "DailyAdapter";
    Activity context;
    List<Knowledge> items = new ArrayList<>();

    public DailyAdapter(Activity mContext, List<Knowledge> items) {
        this.context = mContext;
        this.items = items;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    //아이템을 추가해주고싶을때 이거쓰면됨
    public void addItem(Knowledge item) {
        items.add(item);
    }
    public void removeItem(Knowledge item) {
        items.remove(item);
    }


    //한꺼번에 추가해주고싶을떄
    public void addItems(List<Knowledge> items) {
        this.items = items;
    }

    //아이템 전부 삭제
    public void clear() {
        items.clear();
    }

    @NonNull
    @Override
    public DailyAdapter.DailyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_daily, viewGroup, false);
        return new DailyAdapter.DailyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DailyAdapter.DailyViewHolder holder, int position) {

        holder.name.setText(items.get(position).getKnowledgepeople());
        holder.content.setText(items.get(position).getKnowledgecontent());
        holder.date.setText(items.get(position).getKnowledgedate());
        holder.title.setText(items.get(position).getKnowledgetitle());
    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        } else {
            return items.size();
        }
    }

    public class DailyViewHolder extends RecyclerView.ViewHolder {

        TextView content, name, title, date;

        public DailyViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    //TODO: 관리자만 작성 가능하게 바꾸자
                    Intent intent = new Intent(context, DailyDetailActivity.class);

                    intent.putExtra("knowledgetitle", items.get(getAdapterPosition()).getKnowledgetitle());
                    intent.putExtra("knowledgecontent", items.get(getAdapterPosition()).getKnowledgecontent());
                    intent.putExtra("knowledgedate", items.get(getAdapterPosition()).getKnowledgedate());
                    intent.putExtra("knowledgepeople", items.get(getAdapterPosition()).getKnowledgepeople());
                    intent.putExtra("writerid", items.get(getAdapterPosition()).getParentid());
                    intent.putExtra("id", items.get(getAdapterPosition()).getId());

                    Log.d(items.get(getAdapterPosition()).getId(), "id");
                    context.startActivityForResult(intent, Activity.RESULT_OK);
                }
            });
            title = itemView.findViewById(R.id.d_title);
            content = itemView.findViewById(R.id.d_content);
            name = itemView.findViewById(R.id.d_name);
            date = itemView.findViewById(R.id.tv_text_date);
        }
    }
}
