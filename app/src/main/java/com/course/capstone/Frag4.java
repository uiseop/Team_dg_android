package com.course.capstone;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
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

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.HTTP;

public class Frag4 extends Fragment {
    DataManager dataManager = DataManager.getInstance();
    List<Cross> crosses;
    TextView textView;
    ProgressBar progressBar;
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
        textView = v.findViewById(R.id.wlsgodfbf);
        progressBar = v.findViewById(R.id.progressBar2);
        textView.setText(progressBar.getProgress()+"%");
        recyclerView = v.findViewById(R.id.games);
        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(getContext());
//        GridLayoutManager gm = new GridLayoutManager(getContext(),2);
        CrossAdapter crossAdapter = new CrossAdapter(crosses);
        recyclerView.setLayoutManager(gridLayoutManager);
//        recyclerView.setLayoutManager(gm);
        recyclerView.setAdapter(crossAdapter);

        return v;
    }
}
