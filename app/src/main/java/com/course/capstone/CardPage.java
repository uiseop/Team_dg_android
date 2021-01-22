package com.course.capstone;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.course.capstone.adapter.CardAdapter;
import com.course.capstone.models.Card;

import java.util.ArrayList;

public class CardPage extends AppCompatActivity{
    private RecyclerView RV;
    private RecyclerView.LayoutManager LM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myowncard);



        // 리싸이클러 뷰에 레이아웃을 설정해 준다. 이 레이아웃은xml 로 만들어 준 내용을 사용한다.
        RV = findViewById(R.id.cardRecomand);
        RV.setHasFixedSize(true);
        LM = new LinearLayoutManager(this);
        RV.setLayoutManager(LM);
        // 아이템들을 목록으로 만들어 준다. 목록은 ArrayList 객체를사용한다.
        ArrayList<Card> CallsInfo = new ArrayList<>();
        Card c = new Card();
        Card d = new Card();
        d.setImageCard(R.drawable.bnk);
        d.setCardName("경남우냉");
        d.setDesc("그만하고 집가고싶어라");
        d.setImageReward(R.drawable.menu4);
        d.setReward("짜장면임");
        CallsInfo.add(d);
        c.setImageCard(R.drawable.hana);
        c.setCardName("하나우냉");
        c.setDesc("그만하고 짜장면먹으러 갈래??");
        c.setImageReward(R.drawable.menu5);
        c.setReward("짬뽕임");
        CallsInfo.add(c);

        // 목록을 어댑터에 연결해 준다.
        CardAdapter myAdapter = new CardAdapter(CallsInfo);
        // 어댑터를 뷰에 연결해 준다.
        RV.setAdapter(myAdapter);
        Log.d("haha","baba");
    }

}
