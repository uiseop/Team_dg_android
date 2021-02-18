package com.course.capstone.adapter;

import android.animation.ValueAnimator;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.course.capstone.R;
import com.course.capstone.models.Cross;
import com.ramijemli.percentagechartview.PercentageChartView;
import com.ramijemli.percentagechartview.callback.OnProgressChangeListener;

public class ViewHolderGame extends RecyclerView.ViewHolder {

    TextView textView;
    PercentageChartView percentageChartView;
    RelativeLayout rl,hide;
    int[] col = {R.color.bread, R.color.main, R.color.cursor,
            R.color.colorAccent, R.color.colorPrimaryDark, R.color.teal_200,
            R.color.blue,R.color.teal_700, R.color.purple_200, R.color.purple_500,
            R.color.purple_700,R.color.bread,R.color.main,R.color.cursor};


    OnViewHolderItemClickListener onViewHolderItemClickListener;

    public ViewHolderGame(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.cat);
        percentageChartView = itemView.findViewById(R.id.scr);
        rl = itemView.findViewById(R.id.back);
//        hide = itemView.findViewById(R.id.go);
        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onViewHolderItemClickListener.onViewHolderItemClick();
            }
        });
    }

    public void onBind(Cross data, int position, boolean selectedItems){
        textView.setText(data.getCategory());
        percentageChartView.setProgress(data.getScore(),true);
        Log.d("print", String.valueOf(data.getScore()));
        rl.setBackgroundResource(col[position]);
        changeVisibility(selectedItems);

    }

    private void changeVisibility(final boolean isExpanded){
        // ValueAnimator.ofInt(int... values)는 View가 변할 값을 지정, 인자는 int 배열
        ValueAnimator va = isExpanded ? ValueAnimator.ofInt(0, 600) : ValueAnimator.ofInt(600, 0);
        // Animation이 실행되는 시간, n/1000초
        va.setDuration(600);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                hide.getLayoutParams().height = (int)animation.getAnimatedValue();
                hide.requestLayout();
                hide.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
            }
        });
        // 애니메이션 시작
        va.start();
    }

    public void setOnViewHolderItemClickListener(OnViewHolderItemClickListener onViewHolderItemClickListener){
        this.onViewHolderItemClickListener = onViewHolderItemClickListener;
    }
}
