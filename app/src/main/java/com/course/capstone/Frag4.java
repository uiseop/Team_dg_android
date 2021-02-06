package com.course.capstone;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.course.capstone.models.CrossInfo;
import com.course.capstone.models.GameInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Frag4 extends Fragment {
    Button game;
    String nurl;
    List<CrossInfo> cross;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.frag4,container,false);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-3-139-15-252.us-east-2.compute.amazonaws.com:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GameInterface gameInterface = retrofit.create(GameInterface.class);
        Call<List<CrossInfo>> call = gameInterface.getAllCross();

        call.enqueue(new Callback<List<CrossInfo>>() {
            @Override
            public void onResponse(Call<List<CrossInfo>> call, Response<List<CrossInfo>> response) {
                if(response.isSuccessful()){
                    cross = response.body();
                    nurl = cross.get(0).getCrossUrl();
                }else{
                    Log.d("fail","안된다 ㅋㅋㅋ");
                }
            }

            @Override
            public void onFailure(Call<List<CrossInfo>> call, Throwable t) {

            }
        });
        game = v.findViewById(R.id.game);

        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(nurl));
                startActivity(intent);
            }
        });
        return v;
    }
}
