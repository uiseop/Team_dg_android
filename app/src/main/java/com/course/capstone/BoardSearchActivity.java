package com.course.capstone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import android.widget.Toast;

import com.course.capstone.adapter.BoardAdapter;
import com.course.capstone.adapter.SearchAdapter;
import com.course.capstone.models.Qna;
import com.course.capstone.models.QnaInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class BoardSearchActivity extends AppCompatActivity {
    private SearchView mSearchView;
    private RecyclerView listView;

    List<Qna> qna;
    private SearchAdapter adapter;      // 리스트뷰에 연결할 아답터
    private ArrayList<Qna> arraylist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_search);
        mSearchView = (SearchView)findViewById(R.id.search_view);

        listView = (RecyclerView) findViewById(R.id.listView);
        listView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false);
        listView.setLayoutManager(layoutManager);
        listView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));


        searchinfo();
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                // 검색 버튼 누를 때 호출
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                // 검색창에서 글자가 변경이 일어날 때마다 호출
                String text = s;
              search(text);
                return true;
            }
        });
    }
    public void search(String charText) {

        qna.clear();
        // 문자 입력이 없을때는 모든 데이터를 보여준다.
        if (charText.length() == 0) {
            qna.addAll(arraylist);
        }
        // 문자 입력을 할때..
        else
        {
            // 리스트의 모든 데이터를 검색한다.
            for(int i = 0;i < arraylist.size(); i++)
            {
                // arraylist의 모든 데이터에 입력받은 단어(charText)가 포함되어 있으면 true를 반환한다.
                if (arraylist.get(i).getContent().contains(charText)||arraylist.get(i).getTitle().contains(charText)||arraylist.get(i).getQ_username().contains(charText))
                {
                    // 검색된 데이터를 리스트에 추가한다.
                    qna.add(arraylist.get(i));
                }
            }
        }
        // 리스트 데이터가 변경되었으므로 아답터를 갱신하여 검색된 데이터를 화면에 보여준다.
        adapter.notifyDataSetChanged();
    }




    public void searchinfo() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-3-139-15-252.us-east-2.compute.amazonaws.com:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        QnaInterface qnainterface = retrofit.create(QnaInterface.class);
        Call<List<Qna>> call = qnainterface.getAll();

        call.enqueue(new Callback<List<Qna>>() {
            @Override
            public void onResponse(Call<List<Qna>> call, Response<List<Qna>> response) {
                if (response.isSuccessful()) {
                    qna = response.body();
                    arraylist = new ArrayList<Qna>();
                    arraylist.addAll(qna);

                    adapter = new SearchAdapter( getApplicationContext(),qna);

                    // 리스트뷰에 아답터를 연결한다.
                    listView.setAdapter(adapter);


                } else {
                    Log.d(TAG, "onResponse1: Something Wrong");
                }

            }

            @Override
            public void onFailure(Call<List<Qna>> call, Throwable t) {
                Toast.makeText(getBaseContext(), "목록을 불러올 수 없습니다.", Toast.LENGTH_LONG).show();
                ;
                Log.d(TAG, "onFailure2: 게시물 목록 왜안나와");
            }
        });
    }

}
