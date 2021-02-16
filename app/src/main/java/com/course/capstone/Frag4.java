package com.course.capstone;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.course.capstone.adapter.CrossAdapter;
import com.course.capstone.models.Cross;
import com.course.capstone.models.DataManager;
import com.course.capstone.activities.StudyActivity;
import com.course.capstone.models.GameInterface;
import com.course.capstone.models.User;
import com.course.capstone.models.UserInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.HTTP;

public class Frag4 extends Fragment {
    Button game1;
    Button game2;
    Button game3;
    Button game4;
    Button game5;
    Button game6;
    Button game7;
    Button game8;
    Button game9;
    Button game10;
    Button game11;
    Button game12;
    Button game13;
    Button game14;
    Button study;
    String nurl;
    Response<String> cross;
    String text;
    DataManager dataManager = DataManager.getInstance();
    TextView point1;
    List<Cross> crosses;
    String[] btn = {"game1","game2","game3","button4",
    "button","button5","button6","button7","button8",
    "button10","button9"};

    private RecyclerView recyclerView;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.frag4,container,false);
//        study=v.findViewById(R.id.study);
//        study.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(getActivity(), StudyActivity.class);
//                getActivity().startActivity(intent);
//            }
//        });
//
        crosses = dataManager.getUser().getCross();
//

        recyclerView = v.findViewById(R.id.games);
        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(getContext());
//        GridLayoutManager gm = new GridLayoutManager(getContext(),2);
        CrossAdapter crossAdapter = new CrossAdapter(crosses);
        recyclerView.setLayoutManager(gridLayoutManager);
//        recyclerView.setLayoutManager(gm);
        recyclerView.setAdapter(crossAdapter);
//
//        point1 = v.findViewById(R.id.game11);
//        point1.setText("93점");
//
//        //금융상품
//        game1 = v.findViewById(R.id.game1);
//        game1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), Frag5.class);
//                intent.putExtra("url", "http://ec2-3-139-15-252.us-east-2.compute.amazonaws.com:8080/page1.html");
//                getActivity().startActivity(intent);
//            }
//        });
//
//
//        //보험
//        game2 = v.findViewById(R.id.game2);
//        game2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), Frag5.class);
//                intent.putExtra("url", "http://ec2-3-139-15-252.us-east-2.compute.amazonaws.com:8080/page2.html");
//                getActivity().startActivity(intent);
//
//            }
//        });
//
//        //세금
//        game3 = v.findViewById(R.id.game3);
//        game3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), Frag5.class);
//                intent.putExtra("url", "http://ec2-3-139-15-252.us-east-2.compute.amazonaws.com:8080/page3.html");
//                getActivity().startActivity(intent);
//
//            }
//        });
//
//        //신용
//        game4 = v.findViewById(R.id.button4);
//        game4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), Frag5.class);
//                intent.putExtra("url", "http://ec2-3-139-15-252.us-east-2.compute.amazonaws.com:8080/page4.html");
//                getActivity().startActivity(intent);
//            }
//        });
//
//        //연금
//        game5 = v.findViewById(R.id.button);
//        game5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), Frag5.class);
//                intent.putExtra("url", "http://ec2-3-139-15-252.us-east-2.compute.amazonaws.com:8080/page5.html");
//                getActivity().startActivity(intent);
//            }
//        });
//
//        //증권
//        game6 = v.findViewById(R.id.button8);
//        game6.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), Frag5.class);
//                intent.putExtra("url", "http://ec2-3-139-15-252.us-east-2.compute.amazonaws.com:8080/page6.html");
//                getActivity().startActivity(intent);
//            }
//        });
//
//        //카드
//        game7 = v.findViewById(R.id.button9);
//        game7.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), Frag5.class);
//                intent.putExtra("url", "http://ec2-3-139-15-252.us-east-2.compute.amazonaws.com:8080/page7.html");
//                getActivity().startActivity(intent);
//            }
//        });
//
//        //펀드
//        game8 = v.findViewById(R.id.button10);
//        game8.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), Frag5.class);
//                intent.putExtra("url", "http://ec2-3-139-15-252.us-east-2.compute.amazonaws.com:8080/page8.html");
//                getActivity().startActivity(intent);
//            }
//        });
//
//        //일반상식1
//        game9 = v.findViewById(R.id.button5);
//        game9.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), Frag5.class);
//                intent.putExtra("url", "http://ec2-3-139-15-252.us-east-2.compute.amazonaws.com:8080/page9.html");
//                getActivity().startActivity(intent);
//            }
//        });
//
//        //일반상식2
//        game10 = v.findViewById(R.id.button6);
//        game10.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), Frag5.class);
//                intent.putExtra("url", "http://ec2-3-139-15-252.us-east-2.compute.amazonaws.com:8080/page10.html");
//                getActivity().startActivity(intent);
//            }
//        });
//
//        //일반상식3
//        game11 = v.findViewById(R.id.button7);
//        game11.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), Frag5.class);
//                intent.putExtra("url", "http://ec2-3-139-15-252.us-east-2.compute.amazonaws.com:8080/page11.html");
//                getActivity().startActivity(intent);
//            }
//        });


        return v;
    }
}
