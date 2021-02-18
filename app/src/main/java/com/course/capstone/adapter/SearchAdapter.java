package com.course.capstone.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.course.capstone.BoardDetailActivity;
import com.course.capstone.models.Qna;
import com.course.capstone.R;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    static final String TAG = "BoardAdapter";
    Context context;
    List<Qna> items = new ArrayList<>();


    public SearchAdapter(Context mContext, List<Qna> items) {
        this.context = mContext;
        this.items = items;
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
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.search_listview, viewGroup, false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {

        holder.name.setText(items.get(position).getQ_username());
        holder.content.setText(items.get(position).getContent());
        holder.title.setText(items.get(position).getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();

//like, read버튼 클릭시 이벤트 처리 here


                Intent intent = new Intent(v.getContext(), BoardDetailActivity.class);
                intent.putExtra("title", items.get(position).getTitle());
                intent.putExtra("name", items.get(position).getQ_username());
                intent.putExtra("date", items.get(position).getDate());
                intent.putExtra("content", items.get(position).getContent());
                intent.putExtra("like", items.get(position).getLikeCount());
                intent.putExtra("commentcount", items.get(position).getCommentCount());
                intent.putExtra("id", items.get(position).getId());
                intent.putExtra("qnaid", items.get(position).getQnaid());
                intent.putExtra("likepeople", items.get(position).getLikepeople());

                context.startActivity(intent);


            }
        });

    }


    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        } else {
            return items.size();
        }
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {
        /*  TextView boardNameTextView;
          LinearLayout linearLayout;*/
        TextView content, name, title;
        ImageView imageView;
        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.person);
            title = itemView.findViewById(R.id.s_title);
            content = itemView.findViewById(R.id.s_content);
            name = itemView.findViewById(R.id.s_name);

        }
    }


}