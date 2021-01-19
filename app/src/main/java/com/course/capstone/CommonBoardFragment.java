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

import com.course.capstone.adapter.BoardAdapter;
import com.course.capstone.models.Qna;
import com.course.capstone.models.QnaInterface;
import com.course.capstone.models.User;
import com.course.capstone.models.UserInterface;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class CommonBoardFragment extends Fragment implements View.OnClickListener {
    ViewGroup rootView;

    private RecyclerView mBoardRecyclerView;
    private ArrayList<Qna> mBoardArrayList;
    private BoardAdapter mBoardAdapter;
    private FloatingActionButton bFab;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_common_board, container, false);
        mBoardRecyclerView = (RecyclerView) rootView.findViewById(R.id.board_recycler_view);
        mBoardRecyclerView.setHasFixedSize(true);
        /*final String[] boards = getResources().getStringArray(R.array.boards); //게시판 제목들
        for (String boardName : boards) {
            mBoardArrayList.add(new Board(boardName));
        }*/
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mBoardRecyclerView.setLayoutManager(layoutManager);
        mBoardRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        boardinfo();
        /*  mBoardAdapter = new BoardAdapter(getActivity(), mBoardArrayList);
        mBoardRecyclerView.setAdapter(mBoardAdapter);
       */
        FloatingActionButton onButtonClickedInputRecord1 = (FloatingActionButton) rootView.findViewById(R.id.board_fab);
        onButtonClickedInputRecord1.setOnClickListener(this);
        FloatingActionButton onButtonClickedInputRecord2 = (FloatingActionButton) rootView.findViewById(R.id.board_search_fab);
        onButtonClickedInputRecord2.setOnClickListener(this);


        return rootView;
    }
    @Override
    public void onClick(View v) {
        FloatingActionButton bFab = (FloatingActionButton) v;

        switch(bFab.getId()) {
            //id에 따라서 다른 구현을 한다.
            case R.id.board_fab:
                //버튼 클릭시 아래 구현이 실행된다.
                System.out.println("InputRecord clicked");
                getActivity().startActivity(new Intent(getActivity(), BoardWriteActivity.class));
                break;
            case R.id.board_search_fab:
                //버튼 클릭시 아래 구현이 실행된다.
                System.out.println("InputRecord clicked");
                getActivity().startActivity(new Intent(getActivity(), BoardSearchActivity.class));
                break;
        }


    }

    //서버 연결 후 어댑터 연결
    public void boardinfo(){
            Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-13-59-15-254.us-east-2.compute.amazonaws.com:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        QnaInterface qnainterface = retrofit.create(QnaInterface.class);
        Call<List<Qna>> call = qnainterface.getAll();

        call.enqueue(new Callback<List<Qna>>() {
            @Override
            public void onResponse(Call<List<Qna>> call, Response<List<Qna>> response) {
                if (response.isSuccessful()){
                    List<Qna> qna=response.body();


                    mBoardAdapter = new BoardAdapter(getActivity(), qna);
                  mBoardRecyclerView.setAdapter(mBoardAdapter);
                }
                else{
                    Log.d(TAG, "onResponse1: Something Wrong");
                }
            }

            @Override
            public void onFailure(Call<List<Qna>>call, Throwable t) {
                Toast.makeText(getContext(), "목록을 불러올 수 없습니다.",Toast.LENGTH_LONG).show();;
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
}



