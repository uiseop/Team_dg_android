package com.course.capstone.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.course.capstone.models.Board;
import com.course.capstone.BoardActivity;
import com.course.capstone.ContestActivity;
import com.course.capstone.HOTActivity;
import com.course.capstone.R;

import java.util.ArrayList;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.BoardViewHolder> {
    static final String TAG = "BoardAdapter";
    Context context;
    ArrayList<Board> items = new ArrayList<>();

    public BoardAdapter(Context mContext,ArrayList<Board> items) {
        this.context = mContext;
        addItems(items);
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
    public void addItem(Board item) {
        items.add(item);
    }

    //한꺼번에 추가해주고싶을떄
    public void addItems(ArrayList<Board> items) {
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
    public void onBindViewHolder(@NonNull BoardViewHolder boardViewHolder, int i) {
       final Board model = items.get(i);
        if((model!=null)){
            boardViewHolder.boardNameTextView.setText(items.get(i).getId());
            boardViewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent;


                   if (model.getId().equals("HOT게시판")) {
                        intent = new Intent(context, HOTActivity.class);

                    }
                    else if (model.getId().equals("자유게시판")){
                        intent = new Intent(context, BoardActivity.class);

                    }
                    else {
                        intent = new Intent(context, ContestActivity.class);

                    }

                    intent.putExtra("board", model.getId());
                    Log.d(TAG, "전달된 채팅방 이름: "+model.getId());

                    context.startActivity(intent);


                }
            });
        }else{
            Log.d(TAG, "onBindViewHolder model null");
        }
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
        TextView boardNameTextView;
        LinearLayout linearLayout;

        public BoardViewHolder(@NonNull View itemView) {
            super(itemView);

            boardNameTextView = itemView.findViewById(R.id.board_tv_boardname);
            linearLayout = itemView.findViewById(R.id.board_item_linear);
        }
    }
}