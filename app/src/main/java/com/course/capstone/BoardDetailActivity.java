package com.course.capstone;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.course.capstone.adapter.BoardAdapter;
import com.course.capstone.models.Comment;
import com.course.capstone.models.CommentInterface;
import com.course.capstone.models.DataManager;
import com.course.capstone.models.Qna;
import com.course.capstone.models.QnaInterface;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class BoardDetailActivity extends AppCompatActivity {

    private ActionBar actionBar;
    private TextView toolbarText;
    TextView txt_title;
    TextView txt_content;
    TextView txt_date;
    TextView txt_name;
    TextView txt_like;
    TextView txt_view;
    TextView tv_title;
    ImageButton btn_r_write; //댓글 보내는 버튼
    ImageButton btn_like; //좋아요 버튼
    ImageButton btn_delete; //글 삭제 버튼
    int no;
    int like;
    int view;
    String title;
    String content;
    String name;

    String date;
    String id;
    String qid;
    DataManager dataManager=DataManager.getInstance();
    SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
    Date date1=new Date();
    String time2 = format1.format(date1);
    private String root_talk_no;
    private String string_like;
    private EditText input_r_id;
    private EditText input_r_content;
    RecyclerView recyclerView;
    ReplyTalkAdapter adapter;
   // CommunityTalkAdapter adapter_talk_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_detail);

        toolbarText = findViewById(R.id.toolbartext);
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true); //커스터마이징 하기 위해 필요
        actionBar.setDisplayShowTitleEnabled(false);

        input_r_id = (EditText) findViewById(R.id.input_r_id);
        input_r_content = (EditText) findViewById(R.id.input_r_content);

        btn_r_write = (ImageButton) findViewById(R.id.btn_r_write); //댓글 쓰는 버튼
        btn_like = (ImageButton) findViewById(R.id.btn_like);
        btn_delete = (ImageButton) findViewById(R.id.btn_delete);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);


        initialize();
        //댓글버튼 클릭시! alert창
        btn_r_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert_confirm = new AlertDialog.Builder(BoardDetailActivity.this);
                alert_confirm.setMessage("댓글을 게시하겠습니까?").setCancelable(false).setPositiveButton("확인",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Comment comment = new Comment(dataManager.getUser().getName(), input_r_content.getText().toString(), time2, qid);
                                post(comment);

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
        //글삭제 클릭시! alert창
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert_confirm = new AlertDialog.Builder(BoardDetailActivity.this);
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
        setRecyclerView();
        commentinfo();
    }


    //수정

    //댓글쓰기
    //서버 연결 후 어댑터 연결
   public void commentinfo() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-13-59-15-254.us-east-2.compute.amazonaws.com:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CommentInterface retrofitService = retrofit.create(CommentInterface.class);
        Call<List<Comment>> call = retrofitService.getAll();

        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (response.isSuccessful()) {
                    List<Comment> comment = response.body();
                    adapter =  new ReplyTalkAdapter(getApplicationContext(),  comment);
                    recyclerView.setAdapter(adapter);
                    view=adapter.items.size();

                    Log.e("데이터 읽어오기 성공", String.valueOf(view));

                    txt_view.setText(String.valueOf(view));
                } else {
                    Log.d(TAG, "onResponse1: Something Wrong");
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                Log.d("에러다 짜샤",t.getMessage());
            }
        });
   }
   public void post(Comment comment) {

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://ec2-13-59-15-254.us-east-2.compute.amazonaws.com:8080/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                CommentInterface retrofitService = retrofit.create(CommentInterface.class);
                Call<Comment> call = retrofitService.addComment(comment);

                call.enqueue(new Callback<Comment>() {
                    @Override
                    public void onResponse(Call<Comment> call, Response<Comment> response) {
                        if (response.isSuccessful()) {
                            Log.d("--------------성공!", response.body().toString());
                            setResult(RESULT_OK);
                            finish();

                        } else {
                            Log.d(TAG, "onResponse1: Something Wrong");
                        }
                    }

                    @Override
                    public void onFailure(Call<Comment> call, Throwable t) {
                        Log.e("왜 안되는건데...?", t.getMessage());
                    }
                });


                input_r_content.setText(""); //글쓰고 나서 텍스트 창 초기화
            }

            //댓글


            //게시글 삭제하는 함수
            public void delete() {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://ec2-13-59-15-254.us-east-2.compute.amazonaws.com:8080/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                QnaInterface qnainterface = retrofit.create(QnaInterface.class);
                Call<Void> call = qnainterface.removeQna(qid);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {

                            setResult(RESULT_OK);
                            finish();

                        } else {
                            Log.d(TAG, "onResponse1: Something Wrong");
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(getBaseContext(), "목록을 불러올 수 없습니다.", Toast.LENGTH_LONG).show();
                        ;
                        Log.d(TAG, "onFailure2: 게시물 목록 왜안나와");
                    }
                });

            }
    void setRecyclerView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        //adapter = new CommunityTalkAdapter(getApplicationContext(), item);
        //recyclerView.setAdapter(adapter);
    }
            public void initialize() {

                txt_name = (TextView) findViewById(R.id.txt_name);
                txt_date = (TextView) findViewById(R.id.txt_date);
                txt_title = (TextView) findViewById(R.id.txt_title);
                txt_content = (TextView) findViewById(R.id.txt_content);
                TextView txt_like = (TextView) findViewById(R.id.txt_like);
                txt_view = (TextView) findViewById(R.id.txt_view);


                txt_title.setFocusable(false);
                txt_title.setClickable(false);
                txt_content.setFocusable(false);
                txt_content.setClickable(false);


                no = getIntent().getIntExtra("no", 1);
                root_talk_no = String.valueOf(no); //인트형의 글 번호를 스트링 형으로 변환한 것
                like = getIntent().getIntExtra("like", 3);
                string_like = String.valueOf(like); //인트형의 좋아요 수를 스트링 형으로 변환한 것


                name = getIntent().getStringExtra("name");
                date = getIntent().getStringExtra("date");
                title = getIntent().getStringExtra("title");
                content = getIntent().getStringExtra("content");
                id = getIntent().getStringExtra("id");
                qid = getIntent().getStringExtra("qnaid");


                txt_name.setText(name);
                txt_date.setText(date);
                txt_title.setText(title);
                txt_content.setText(content);
                txt_like.setText(string_like);


                Log.d(name, "-------------제발!!-성공!");
            }


        }
