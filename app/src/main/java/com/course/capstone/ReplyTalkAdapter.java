package com.course.capstone;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.course.capstone.models.Comment;
import com.course.capstone.models.CommentInterface;
import com.course.capstone.models.DataManager;
import com.course.capstone.models.QnaInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class ReplyTalkAdapter extends RecyclerView.Adapter<ReplyTalkAdapter.ViewHolder> {
    Context context;
    List<Comment> items;
    String personid;
    DataManager dataManager=DataManager.getInstance();
    ImageButton btn_r_menu;


    public ReplyTalkAdapter(Context context, List<Comment> items,String personid) {
        this.context = context;
        this.items = items;
        this.personid=personid;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_reply_list, parent, false);

        return new ViewHolder(view);
    }

    //온바인드뷰홀더는 아이템을 세팅하거나 스크롤링 할때 호출되는 애다. 때문에 position이 필요하다.
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        holder.no.setText(items.get(position).getR_no());
//        holder.t_no.setText(items.get(position).getR_t_no());
        holder.name.setText(items.get(position).getCommentpeople());
        holder.content.setText(items.get(position).getCommentcontent());
        holder.date.setText(items.get(position).getCommentdate());
        if(dataManager.getUser().getUserid().equals(items.get(position).getCommentpeople()))
        {
            btn_r_menu.setVisibility(ImageButton.VISIBLE);

        }
        else{
            btn_r_menu.setVisibility(ImageButton.GONE);

        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Comment item) {
        items.add(item);
        notifyDataSetChanged();
    }

    //뷰홀더라는 애는 아이템안에 들어갈 텍스트등의 내용을 초기화 하는 역할이다.
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView content, name, date;



        public ViewHolder(View itemView) {
            super(itemView);
            btn_r_menu= itemView.findViewById(R.id.btn_r_menu);
            btn_r_menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final PopupMenu popupMenu = new PopupMenu(context, itemView);
                    popupMenu.getMenuInflater().inflate(R.menu.coment_popup, popupMenu.getMenu());

                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {
                            if (menuItem.getItemId() == R.id.action_delete) {
                                comentdelete( items.get(getAdapterPosition()).getCommentid());

                            }/* else if (menuItem.getItemId() == R.id.action_recommentwrite) {

                            } else {

                            }
*/
                            return false;
                        }

                    });
                    popupMenu.show();
                }
            });

            content = itemView.findViewById(R.id.tv_text_content);
            name = itemView.findViewById(R.id.tv_text_name);
            date = itemView.findViewById(R.id.tv_text_date);

        }

    }
    //게시글 삭제하는 함수
    public void comentdelete(String commnetid) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-13-59-15-254.us-east-2.compute.amazonaws.com:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
       CommentInterface commentInterface = retrofit.create(CommentInterface.class);
        Call<Void> call = commentInterface.removeComment(commnetid);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse1: Something");



                } else {
                    Log.d(TAG, "onResponse1: Something Wrong");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(context, "목록을 불러올 수 없습니다.", Toast.LENGTH_LONG).show();
                ;
                Log.d(TAG, "onFailure2: 게시물 목록 왜안나와");
            }
        });

    }

}