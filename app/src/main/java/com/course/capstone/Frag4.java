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
    Button game;
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



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.frag4,container,false);
        study=v.findViewById(R.id.study);
        study.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), StudyActivity.class);
                getActivity().startActivity(intent);
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-3-139-15-252.us-east-2.compute.amazonaws.com:8080/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        crosses = dataManager.getUser().getCross();

        for (Cross cross1 : crosses) {
            for (String btn_name : btn){
                Integer integer = cross1.getScore();
                if (cross1.getCategory() == btn_name && integer!=null){
                    text = btn_name + '1';
                    point1 = v.findViewById(R.id.text);
                    point1.setText(cross1.getScore());
                    break;
                }
            }
        }

        point1 = v.findViewById(R.id.game11);
        point1.setText("93점");


        GameInterface gameInterface = retrofit.create(GameInterface.class);
        Call<String> call = gameInterface.getHtml();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    Log.d("hi","it's me mario");
                    Log.d("hi","it's me mario123");
                    Log.d("hi","it's me mario456");
                    Log.d("hi","it's me mario789");
                    cross = response;
                    Log.d("hi",cross.toString());
                    Log.d("this is url", cross.raw().request().url().toString());
                    nurl = cross.raw().request().url().toString();
                    Log.d("hi",nurl);
                    game = v.findViewById(R.id.game1);
                    game.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(),Frag5.class);
                            intent.putExtra("url",nurl);
                            getActivity().startActivity(intent);
                        }
                    });


                }else{
                    Log.d("fail","안된다 ㅋㅋㅋ");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("nonono","nononono");
            }
        });



        return v;
    }
}
