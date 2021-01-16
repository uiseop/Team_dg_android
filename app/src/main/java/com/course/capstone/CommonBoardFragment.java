package com.course.capstone;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.course.capstone.adapter.BoardAdapter;
import com.course.capstone.models.Board;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class CommonBoardFragment extends Fragment implements View.OnClickListener {
    ViewGroup rootView;

    private RecyclerView mBoardRecyclerView;
    private ArrayList<Board> mBoardArrayList;
    private BoardAdapter mBoardAdapter;
    private FloatingActionButton bFab;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_common_board, container, false);
        mBoardRecyclerView = (RecyclerView) rootView.findViewById(R.id.board_recycler_view);

        mBoardArrayList = new ArrayList<>();
        mBoardRecyclerView.setHasFixedSize(true);
        final String[] boards = getResources().getStringArray(R.array.boards); //게시판 제목들
        for (String boardName : boards) {
            mBoardArrayList.add(new Board(boardName));
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mBoardRecyclerView.setLayoutManager(layoutManager);
        mBoardRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        mBoardAdapter = new BoardAdapter(getActivity(), mBoardArrayList);
        mBoardRecyclerView.setAdapter(mBoardAdapter);
       // bFab=rootView.findViewById(R.id.board_fab);
        /*bFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getActivity();
                Intent i = new Intent(getActivity(),  BoardWriteActivity.class);
                v.startActivity(i);
            }
        });*/
        FloatingActionButton onButtonClickedInputRecord = (FloatingActionButton) rootView.findViewById(R.id.board_fab);
        onButtonClickedInputRecord.setOnClickListener(this);

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
        }


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



