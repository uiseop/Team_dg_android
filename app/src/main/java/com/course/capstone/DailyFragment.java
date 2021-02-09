package com.course.capstone;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.course.capstone.adapter.DailyAdapter;
import com.course.capstone.models.DataManager;
import com.course.capstone.models.Knowledge;
import com.course.capstone.models.KnowledgeInterface;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class DailyFragment extends Fragment implements View.OnClickListener{
    ViewGroup rootView;
    private RecyclerView DailyRecyclerView;
    SwipeRefreshLayout refreshLayout;
    private com.course.capstone.adapter.DailyAdapter DailyAdapter;
    DataManager dataManager = DataManager.getInstance();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_daily, container, false);
        DailyRecyclerView = (RecyclerView) rootView.findViewById(R.id.d_recyclerview);
        DailyRecyclerView.setHasFixedSize(true);
        refreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_drefresh);
        getKnowledge();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        DailyRecyclerView.setLayoutManager(layoutManager);
        DailyRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        //  refreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh);
        FloatingActionButton onButtonClickedInputRecord = (FloatingActionButton) rootView.findViewById(R.id.write_daily);
        onButtonClickedInputRecord.setOnClickListener(this);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getKnowledge();
            }
        });

        return rootView;
    }

    @Override
    public void onClick(View v) {
        FloatingActionButton bFab = (FloatingActionButton) v;

        switch (bFab.getId()) {
            //id에 따라서 다른 구현을 한다.
            case R.id.write_daily:
                //버튼 클릭시 아래 구현이 실행된다.
                System.out.println("InputRecord clicked");
                getActivity().startActivity(new Intent(getActivity(), DailyWriteActivity.class));

                break;
        }


    }


    //서버 연결 후 어댑터 연결
    public void getKnowledge() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-3-139-15-252.us-east-2.compute.amazonaws.com:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        KnowledgeInterface knowledgeInterface = retrofit.create(KnowledgeInterface.class);
        Call<List<Knowledge>> call = knowledgeInterface.getAll();

        call.enqueue(new Callback<List<Knowledge>>() {
            @Override
            public void onResponse(Call<List<Knowledge>> call, Response<List<Knowledge>> response) {
                if (response.isSuccessful()) {
                    List<Knowledge> knowledge = response.body();

                    DailyAdapter = new DailyAdapter(getActivity(), knowledge);
                    DailyRecyclerView.setAdapter(DailyAdapter);

                    refreshLayout.setRefreshing(false);
                } else {
                    Log.d(TAG, "onResponse1: Something Wrong");
                }
            }

            @Override
            public void onFailure(Call<List<Knowledge>> call, Throwable t) {
                Toast.makeText(getContext(), "목록을 불러올 수 없습니다.", Toast.LENGTH_LONG).show();
                ;
                Log.d(TAG, "onFailure2: 게시물 목록 왜안나와");
            }
        });

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    public void onActivityResult(ActivityResultEvent activityResultEvent) {
        onActivityResult(activityResultEvent.getRequestCode(), activityResultEvent.getResultCode(), activityResultEvent.getData());
    }

}

