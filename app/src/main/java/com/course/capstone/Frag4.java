package com.course.capstone;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.course.capstone.models.GameInterface;

import java.util.List;

import okhttp3.Headers;
import okhttp3.HttpUrl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.HTTP;

public class Frag4 extends Fragment {
    Button game;
    String nurl;
    Response<String> cross;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.frag4,container,false);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-3-139-15-252.us-east-2.compute.amazonaws.com:8080/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

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
