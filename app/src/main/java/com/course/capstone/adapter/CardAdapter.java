package com.course.capstone.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.course.capstone.R;
import com.course.capstone.models.Card;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    Bitmap bitmap;
    Bitmap bitmap1;
    Bitmap bitmap2;
    int i=0;
    private List<Card> mList;
    public CardAdapter(List<Card> mList){
        this.mList = mList;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgView_desert, imgView_card, imgView_desert2;
        TextView textView_name, textView_desert, textView_bene, textView_desert2;
        LinearLayout layoutManager;

        public MyViewHolder(View itemView) {
            super(itemView);
            imgView_card = itemView.findViewById(R.id.imageView2);
            textView_name = itemView.findViewById(R.id.cardName);
            textView_bene = itemView.findViewById(R.id.benefit);
            textView_desert = itemView.findViewById(R.id.desert);
            imgView_desert = itemView.findViewById(R.id.desertimg);
            textView_desert2 = itemView.findViewById(R.id.desert2);
            imgView_desert2 = itemView.findViewById(R.id.desertimg2);
        }
    }

    private class LoadingViewHolder extends RecyclerView.ViewHolder {

        ProgressBar progressBar;

        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }
    private void showLoadingView(LoadingViewHolder viewHolder, int position) {
        //
    }
    private void populateItemRows(MyViewHolder viewHolder, int position) {

//        String item = mList.get(position);
//        viewHolder.tvItem.setText(item);
//
//
//        MyViewHolder viewHolder = holder;
        // 안드로이드에서 네트워크와 관련된 작업을 할 때,
        // 반드시 메인 Thread가 아닌 별도의 작업 Thread를 생성하여 작업해야 한다.
        Thread mThread = new Thread() {
            @Override
            public void run() {
                try {
                    URL url = new URL(mList.get(position).getImageCard());
                    URL url1 = new URL(mList.get(position).getImageReward().get(0));
                    if(mList.get(position).getImageReward().size()==2){
                        URL url2 = new URL(mList.get(position).getImageReward().get(1));
                        HttpURLConnection conn2 = (HttpURLConnection) url2.openConnection();
                        conn2.setDoInput(true); // 서버로 부터 응답 수신
                        conn2.connect();
                        InputStream is2 = conn2.getInputStream(); // InputStream 값 가져오기
                        bitmap2 = BitmapFactory.decodeStream(is2);} // Bitmap으로 변환
                    else{
                        i = 1;
                    }
                    // Web에서 이미지를 가져온 뒤
                    // ImageView에 지정할 Bitmap을 만든다
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    HttpURLConnection conn1 = (HttpURLConnection) url1.openConnection();

                    conn.setDoInput(true); // 서버로 부터 응답 수신
                    conn.connect();
                    conn1.setDoInput(true); // 서버로 부터 응답 수신
                    conn1.connect();


                    InputStream is = conn.getInputStream(); // InputStream 값 가져오기
                    bitmap = BitmapFactory.decodeStream(is); // Bitmap으로 변환
                    InputStream is1 = conn1.getInputStream(); // InputStream 값 가져오기
                    bitmap1 = BitmapFactory.decodeStream(is1); // Bitmap으로 변환


                } catch (MalformedURLException e) {
                    e.printStackTrace();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        mThread.start(); // Thread 실행

        try {
            // 메인 Thread는 별도의 작업 Thread가 작업을 완료할 때까지 대기해야한다
            // join()를 호출하여 별도의 작업 Thread가 종료될 때까지 메인 Thread가 기다리게 한다
            mThread.join();

            // 작업 Thread에서 이미지를 불러오는 작업을 완료한 뒤
            // UI 작업을 할 수 있는 메인 Thread에서 ImageView에 이미지를 지정한다
            viewHolder.imgView_card.setImageBitmap(bitmap);
            viewHolder.imgView_desert.setImageBitmap(bitmap1);
            if(i == 0){
            viewHolder.imgView_desert2.setImageBitmap(bitmap2);}
            else{
                viewHolder.imgView_desert2.setImageDrawable(null);
                i = 0;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        viewHolder.textView_name.setText(mList.get(position).getCardName());
        viewHolder.textView_bene.setText(mList.get(position).getDesc());
        viewHolder.textView_desert.setText(mList.get(position).getReward().get(0));
        if(mList.get(position).getReward().size() == 2){
        viewHolder.textView_desert2.setText(mList.get(position).getReward().get(1));}
//        viewHolder.imgView_desert.setImageResource(Integer.parseInt(mList.get(position).getImageReward()));
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
            return new MyViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }

//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.item_card,parent,false);
//        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {

            populateItemRows((MyViewHolder) holder, position);
        } else if (holder instanceof LoadingViewHolder) {
            showLoadingView((LoadingViewHolder) holder, position);
        }


    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }



    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }
}
