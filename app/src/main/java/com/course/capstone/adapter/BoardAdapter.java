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

import com.course.capstone.BoardDetailActivity;
import com.course.capstone.models.Qna;
import com.course.capstone.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.BoardViewHolder> {
    static final String TAG = "BoardAdapter";
    Activity context;
    List<Qna> items = new ArrayList<>();


    public BoardAdapter(Activity mContext, List<Qna>items) {
        this.context = mContext;
        this.items=items;
        //addItems(items);
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
    public void addItem(Qna item) {
        items.add(item);
    }

    //한꺼번에 추가해주고싶을떄
    public void addItems(List<Qna> items) {
        this.items = items;
    }

    //아이템 전부 삭제
    public void clear() {
        items.clear();
    }

    @NonNull
    @Override
    public BoardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_board, viewGroup, false);
        return new BoardViewHolder(view);
    }

    @Override
    public void onBindViewHolder( BoardViewHolder holder, int position) {
        Comparator<Qna> datecomparator = new Comparator<Qna>() {
            @Override
            public int compare(Qna a, Qna b) {
                return b.getDate().compareTo(a.getDate());
            }
        };
        Collections.sort(items, datecomparator);
        holder.name.setText(items.get(position).getQ_username());
        holder.content.setText(items.get(position).getContent());
        holder.date.setText(items.get(position).getDate());
        holder.title.setText(items.get(position).getTitle());
        holder.like.setText(String.valueOf(items.get(position).getLikeCount()));
        holder.count.setText(String.valueOf(items.get(position).getCommentCount()));

    }

    @Override
    public int getItemCount() {
        if(items == null){
            return  0;
        }else {
            return items.size();
        }
    }

    public class BoardViewHolder extends RecyclerView.ViewHolder {
      /*  TextView boardNameTextView;
        LinearLayout linearLayout;*/
      TextView content, name, title, date,like, count;

        public BoardViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    System.out.println(getPosition());
                    //어댑터에서는 this를 쓸 수 없으므로 context를 쓴다. context는 이 레이아웃의 변수들?
                    Intent intent = new Intent(context , BoardDetailActivity.class);

                   intent.putExtra("title", items.get(getAdapterPosition()).getTitle());
                    intent.putExtra("name", items.get(getAdapterPosition()).getQ_username());
                    intent.putExtra("date", items.get(getAdapterPosition()).getDate());
                    intent.putExtra("content", items.get(getAdapterPosition()).getContent());
                    intent.putExtra("like", items.get(getAdapterPosition()).getLikeCount());
                    intent.putExtra("commentcount", items.get(getAdapterPosition()).getCommentCount());
                    intent.putExtra("id", items.get(getAdapterPosition()).getId());
                    intent.putExtra("qnaid", items.get(getAdapterPosition()).getQnaid());

                   intent.putExtra("likepeople", items.get(getAdapterPosition()).getLikepeople());
                    intent.putExtra("commentpeople", items.get(getAdapterPosition()).getCommentpeople());


                    Log.d(items.get(getAdapterPosition()).getId(),"id");
                    context.startActivityForResult(intent,Activity.RESULT_OK);
                }
            });

            title = itemView.findViewById(R.id.tv_text_title);
            content = itemView.findViewById(R.id.tv_text_content);
            name = itemView.findViewById(R.id.tv_text_name);
            date = itemView.findViewById(R.id.tv_text_date);
            like = itemView.findViewById(R.id.tv_text_like);
            count = itemView.findViewById(R.id.tv_text_view);
        }
    }


}