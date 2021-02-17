package com.course.capstone.adapter;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ramijemli.percentagechartview.PercentageChartView;
import com.course.capstone.R;
import com.course.capstone.models.Cross;

import com.course.capstone.models.EducationCard;

import java.util.List;

public class CrossAdapter extends RecyclerView.Adapter<CrossAdapter.ViewHolder> {
    private SparseBooleanArray selectedItems = new SparseBooleanArray();
    // 직전에 클릭됐던 Item의 position
    private int prePosition = -1;
    //데이터 배열 선언
    private List<Cross> mList;
    Context context;
    int[] col = {R.color.bread, R.color.main, R.color.cursor,
    R.color.colorAccent, R.color.colorPrimaryDark, R.color.teal_200,
    R.color.blue,R.color.teal_700, R.color.purple_200, R.color.purple_500,
    R.color.purple_700,R.color.bread,R.color.main,R.color.cursor};

    public  class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {
        private TextView textView;
        private PercentageChartView percentageChartView;
        private ImageButton gostudy, gogame;
        private RelativeLayout rl;
        private Cross data;
        private int position;
        private LinearLayout go;


        public ViewHolder(View itemView) {
            super(itemView);
            go = itemView.findViewById(R.id.go);
            gogame = itemView.findViewById(R.id.gogame);
            gostudy = itemView.findViewById(R.id.gostudy);
            textView = itemView.findViewById(R.id.cat);
            percentageChartView = itemView.findViewById(R.id.scr);
            rl = itemView.findViewById(R.id.back);
        }

        void onBind(Cross data, int position) {
            this.data = data;
            this.position = position;
            Log.d("debug :", "List " + mList.get(position).getCategory());
            Integer integer = mList.get(position).getScore();
            if(integer == null){
                percentageChartView.setProgress(0,true);
            }
            else{
                percentageChartView.setProgress(mList.get(position).getScore(),true);
            }

            textView.setText(mList.get(position).getCategory());
            rl.setBackgroundResource(col[position]);

            //다 해줬는데도 GlideApp 에러가 나면 rebuild project를 해주자.
            changeVisibility(selectedItems.get(position));
            itemView.setOnClickListener(this);
            percentageChartView.setOnClickListener(this);
            textView.setOnClickListener(this);

        }
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.cat:
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
                    go.requestLayout();
                    // imageView가 실제로 사라지게하는 부분
                    go.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
                }
            });
            // Animation start
            va.start();
        }
    }

    public void addItem(Cross data) {
        // 외부에서 item을 추가시킬 함수입니다.
        mList.add(data);
    }

    //생성자
    public CrossAdapter(List<Cross> list,Context context) {
        this.mList = list;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_score, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(mList.get(position),position);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}

