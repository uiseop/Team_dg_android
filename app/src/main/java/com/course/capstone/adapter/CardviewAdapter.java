package com.course.capstone.adapter;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.provider.ContactsContract;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.course.capstone.R;
import com.course.capstone.models.EducationCard;

import java.util.ArrayList;

public class CardviewAdapter extends RecyclerView.Adapter<CardviewAdapter.ViewHolder> {

    Context context;
    ArrayList<EducationCard> items;
    private SparseBooleanArray selectedItems = new SparseBooleanArray();
    // 직전에 클릭됐던 Item의 position
    private int prePosition = -1;


    public CardviewAdapter(Context context, ArrayList<EducationCard> items) {
        this.context = context;
        this.items = items;

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview, null);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.onBind(items.get(position),position);


    }
    @Override
    public int getItemCount() {
        return this.items.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
        ImageView image;
        TextView title;
        TextView content;
        CardView cardview;
        private EducationCard data;
        private int position;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.imageView5);
            title = (TextView) itemView.findViewById(R.id.info_title);
            content = (TextView) itemView.findViewById(R.id.info_content);
            cardview = (CardView) itemView.findViewById(R.id.card_view);
        }

        void onBind(EducationCard data, int position) {
            this.data = data;
            this.position = position;


            Drawable drawable = ContextCompat.getDrawable(context, data.getImage());
           image.setBackground(drawable);
            title.setText(data.getTitle());
            content.setText(data.getContent());



            changeVisibility(selectedItems.get(position));
            itemView.setOnClickListener(this);
            image.setOnClickListener(this);
            title.setOnClickListener(this);

        }
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.info_title:
                    if (selectedItems.get(position)) {
                        // 펼쳐진 Item을 클릭 시
                        selectedItems.delete(position);
                    } else {
                        // 직전의 클릭됐던 Item의 클릭상태를 지움
                        selectedItems.delete(prePosition);
                        // 클릭한 Item의 position을 저장
                        selectedItems.put(position, true);
                    }
                    // 해당 포지션의 변화를 알림
                    if (prePosition != -1) notifyItemChanged(prePosition);
                    notifyItemChanged(position);
                    // 클릭된 position 저장
                    prePosition = position;
                    break;
                case R.id.textView1:
                    Toast.makeText(context, data.getTitle(), Toast.LENGTH_SHORT).show();
                    break;
                case R.id.textView2:
                    Toast.makeText(context, data.getContent(), Toast.LENGTH_SHORT).show();
                    break;
                case R.id.imageView:
                    Toast.makeText(context, data.getTitle() + " 이미지 입니다.", Toast.LENGTH_SHORT).show();
                    break;

            }


    }

    private void changeVisibility(final boolean isExpanded) {
        // height 값을 dp로 지정해서 넣고싶으면 아래 소스를 이용
        int dpValue = 150;
        float d = context.getResources().getDisplayMetrics().density;
        int height = (int) (dpValue * d);

        // ValueAnimator.ofInt(int... values)는 View가 변할 값을 지정, 인자는 int 배열
        ValueAnimator va = isExpanded ? ValueAnimator.ofInt(0, height) : ValueAnimator.ofInt(height, 0);
        // Animation이 실행되는 시간, n/1000초
        va.setDuration(600);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // value는 height 값
                int value = (int) animation.getAnimatedValue();
                // imageView의 높이 변경
              //content.getLayoutParams().height = value;
                content.requestLayout();
                // imageView가 실제로 사라지게하는 부분
                content.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
            }
        });
        // Animation start
        va.start();
    }
}

}
