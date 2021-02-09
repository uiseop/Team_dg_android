package com.course.capstone;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.course.capstone.models.Comment;
import com.course.capstone.models.CommentInterface;
import com.course.capstone.models.DataManager;
import com.course.capstone.models.FCMInterface;
import com.course.capstone.models.GenericResponse;
import com.course.capstone.models.Qna;
import com.course.capstone.models.QnaInterface;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class DailyDetailActivity extends AppCompatActivity {
    private ActionBar actionBar;
    private TextView toolbarText;
    TextView txt_title;
    TextView txt_content;
    TextView txt_date;
    TextView txt_name;
    TextView txt_view;
    TextView tv_title;
    private ImageButton btn_rewrite;
    ImageButton btn_delete;//글 삭제 버튼

    int no;
    String title;
    String content;
    String name;

    String date;
    String personid;
    String qid;
    DataManager dataManager = DataManager.getInstance();
    SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date1 = new Date();
    String time2 = format1.format(date1);
    String predate;
    private String root_talk_no;
    private String string_like;
    private EditText input_r_id;
    private EditText input_r_content;
    HotAdapter hotadapter;
    int code = 1;

    // CommunityTalkAdapter adapter_talk_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_detail);

        toolbarText = findViewById(R.id.toolbartext);
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true); //커스터마이징 하기 위해 필요
        actionBar.setDisplayShowTitleEnabled(false);

        input_r_content = (EditText) findViewById(R.id.input_r_content);

        btn_delete = (ImageButton) findViewById(R.id.btn_write);
        btn_rewrite = findViewById(R.id.btn_rewrite);


        initialize();


        //글삭제 클릭시! alert창
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert_confirm = new AlertDialog.Builder(DailyDetailActivity.this);
                alert_confirm.setMessage("글을 삭제하겠습니까?").setCancelable(false).setPositiveButton("확인",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                delete();
                            }
                        }).setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        });
                AlertDialog alert = alert_confirm.create();
                alert.show();
            }
        });
        btn_rewrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert_confirm = new AlertDialog.Builder(DailyDetailActivity.this);
                alert_confirm.setMessage("글을 수정하겠습니까?").setCancelable(false).setPositiveButton("확인",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent(DailyDetailActivity.this, DailyWriteActivity.class);
                                //변수를 해당 activity로 넘긴다.

                                intent.putExtra("knowledgetitle", title);
                                intent.putExtra("knowledgepeople", name);
                                intent.putExtra("knowledgecontent", content);
                                intent.putExtra("id", qid);

                                intent.putExtra("writerid", personid);
                                intent.putExtra("edit", code);
                                intent.putExtra("knowledgedate", date);

                                startActivity(intent);

                            }
                        }).setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        });
                AlertDialog alert = alert_confirm.create();
                alert.show();
            }
        });


        setRecyclerView();


        if ((dataManager.getUser().getId()).equals(personid)) {
            btn_rewrite.setVisibility(ImageButton.VISIBLE);
            btn_delete.setVisibility(ImageButton.VISIBLE);
        } else {
            btn_rewrite.setVisibility(ImageButton.GONE);
            btn_delete.setVisibility(ImageButton.GONE);
        }
        //새로고침 당겨서 하기 구현
    }


    //게시글 삭제하는 함수
    public void delete() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-3-139-15-252.us-east-2.compute.amazonaws.com:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        QnaInterface qnainterface = retrofit.create(QnaInterface.class);
        Call<Void> call = qnainterface.removeQna(qid);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), name + "님의 글이 삭제되었습니다.", Toast.LENGTH_LONG).show();

                    setResult(RESULT_OK);
                    finish();

                } else {
                    Log.d(TAG, "onResponse1: Something Wrong");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getBaseContext(), "목록을 불러올 수 없습니다.", Toast.LENGTH_LONG).show();

                Log.d(TAG, "onFailure2: 게시물 목록 왜안나와");
            }
        });

    }


    public void setRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //adapter = new CommunityTalkAdapter(getApplicationContext(), item);
        //recyclerView.setAdapter(adapter);
    }

    public void initialize() {

        txt_name = (TextView) findViewById(R.id.txt_name);
        txt_date = (TextView) findViewById(R.id.txt_date);
        txt_title = (TextView) findViewById(R.id.txt_title);
        txt_content = (TextView) findViewById(R.id.txt_content);
        txt_view = (TextView) findViewById(R.id.txt_view);


        txt_title.setFocusable(false);
        txt_title.setClickable(false);
        txt_content.setFocusable(false);
        txt_content.setClickable(false);


        name = getIntent().getStringExtra("knowledgepeople");
        date = getIntent().getStringExtra("knowledgedate");
        title = getIntent().getStringExtra("knowledgetitle");
        content = getIntent().getStringExtra("knowledgecontent");
        personid = getIntent().getStringExtra("writerid");
        qid = getIntent().getStringExtra("id");

        txt_name.setText(name);
        txt_date.setText(date);
        txt_title.setText(title);
        txt_content.setText(content);

        Log.d(name, "-------------제발!!-성공!");
    }

}