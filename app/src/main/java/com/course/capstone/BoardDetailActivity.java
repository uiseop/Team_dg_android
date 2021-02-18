package com.course.capstone;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

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
    ImageButton back;
    ImageButton btn_r_write; //댓글 보내는 버튼
    ImageButton btn_like; //좋아요 버튼
    private ImageButton btn_rewrite;
    ImageButton btn_delete;//글 삭제 버튼
    ToggleButton buttonFavorite;

    int no;
    int likecount;
    int commentcount;
    String title;
    String content;
    String name;
    ArrayList<String> likepeoplelist;
    ArrayList<String> commentpeoplelist;

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
    RecyclerView recyclerView;
    ReplyTalkAdapter adapter;
    HotAdapter hotadapter;
    int code = 1;
    SwipeRefreshLayout refreshLayout; //당겨서 새로고침

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

        input_r_content = (EditText) findViewById(R.id.input_r_content);

        btn_r_write = (ImageButton) findViewById(R.id.btn_r_write); //댓글 쓰는 버튼
//        btn_like = (ImageButton) findViewById(R.id.btn_like);
        btn_delete = (ImageButton) findViewById(R.id.btn_write);
        btn_rewrite = findViewById(R.id.btn_rewrite);

        buttonFavorite = findViewById(R.id.btn_like);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 1.0f, 1.0f, 1.4f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 1.4f);
        scaleAnimation.setDuration(500);
        BounceInterpolator bounceInterpolator = new BounceInterpolator();
        scaleAnimation.setInterpolator(bounceInterpolator);
        buttonFavorite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                //animation
                compoundButton.startAnimation(scaleAnimation);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        refreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_comment);
        initialize();


        if (likepeoplelist.contains(dataManager.getUser().getUserid()) == false || likepeoplelist == null){
            buttonFavorite.setChecked(false);
        }
        else{
            buttonFavorite.setChecked(true);
        }


        //댓글버튼 클릭시! alert창
        btn_r_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert_confirm = new AlertDialog.Builder(BoardDetailActivity.this);
                alert_confirm.setMessage("댓글을 게시하겠습니까?").setCancelable(false).setPositiveButton("확인",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (commentpeoplelist.contains(dataManager.getUser().getUserid()) == false || commentpeoplelist == null) {
                                    commentpeoplelist.add(dataManager.getUser().getUserid());
                                }
                                Comment comment = new Comment(dataManager.getUser().getUserid(), input_r_content.getText().toString(), time2, qid);
                                Qna qna = new Qna(name, personid, title, content, date, commentcount, likecount, qid, likepeoplelist, commentpeoplelist);
                                Retrofit retrofit = new Retrofit.Builder()
                                        .baseUrl("http://ec2-3-139-15-252.us-east-2.compute.amazonaws.com:8080/")
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build();
                                QnaInterface qnainterface = retrofit.create(QnaInterface.class);
                                Call<Qna> call = qnainterface.updateQna(qid, qna);



                                call.enqueue(new Callback<Qna>() {
                                    @Override
                                    public void onResponse(Call<Qna> call, Response<Qna> response) {
                                        if (response.isSuccessful()) {
                                            setResult(RESULT_OK);
                                            finish();

                                        } else {
                                            Log.d(TAG, "onResponse1: Something Wrong");
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Qna> call, Throwable t) {
                                        Toast.makeText(getBaseContext(), "목록을 불러올 수 없습니다.", Toast.LENGTH_LONG).show();
                                        ;
                                        Log.d(TAG, "onFailure2:수정 왜안돼");
                                    }
                                });

                                post(comment);

                                HashMap<String, Object> hashMap = new HashMap<>();
                                hashMap.put("qnaid", qid);
                                hashMap.put("commenterid", dataManager.getUser().getUserid());
                                hashMap.put("writerid", name);

                                FCMInterface fcmInterface = retrofit.create(FCMInterface.class);
                                Call<GenericResponse> call2 = fcmInterface.send(hashMap);
                                call2.enqueue(new Callback<GenericResponse>() {
                                    @Override
                                    public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {
                                        Log.d(TAG, "알림 메시지 보내기 성공");
                                    }

                                    @Override
                                    public void onFailure(Call<GenericResponse> call, Throwable t) {
                                        Log.d(TAG, "알림 메시지 보내기 실패");
                                    }
                                });

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
        btn_rewrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert_confirm = new AlertDialog.Builder(BoardDetailActivity.this);
                alert_confirm.setMessage("글을 수정하겠습니까?").setCancelable(false).setPositiveButton("확인",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent(BoardDetailActivity.this, BoardWriteActivity.class);
                                //변수를 해당 activity로 넘긴다.

                                intent.putExtra("title", title);
                                intent.putExtra("name", name);
                                intent.putExtra("content", content);
                                intent.putExtra("qnaid", qid);

                                intent.putExtra("personid", personid);
                                intent.putExtra("edit", code);
                                intent.putExtra("like", likecount);
                                intent.putExtra("comment", commentcount);
                                intent.putExtra("likepeoplelist", likepeoplelist);
                                intent.putExtra("commentpeoplelist", commentpeoplelist);
                                intent.putExtra("date", date);

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

        //좋아요 버튼 클릭시

        buttonFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                if (likepeoplelist.contains(dataManager.getUser().getUserid()) == false || likepeoplelist == null) {
                    AlertDialog.Builder alert_confirm = new AlertDialog.Builder(BoardDetailActivity.this);


                    Log.d("-------제발!!후후루루루-성공!", string_like.toString());
                    likepeoplelist.add(dataManager.getUser().getUserid());
                    System.out.println(likepeoplelist);
                    likecount += 1;
                    Qna qna = new Qna(name, personid, title, content, date, commentcount, likecount, qid, likepeoplelist);
                    update_like(qna);


                } else {
                    AlertDialog.Builder alert_confirm = new AlertDialog.Builder(BoardDetailActivity.this);
                    alert_confirm.setMessage("좋아요를 취소하시겠습니까?").setCancelable(false).setPositiveButton("아니요",

                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Log.d("이미 눌렀다 이말이야", string_like.toString());
                                    buttonFavorite.setChecked(true);
                                    return;

                                }
                            }).setNegativeButton("확인",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    likecount--;
                                    likepeoplelist.remove(dataManager.getUser().getUserid());
                                    System.out.println(likepeoplelist);
                                    Qna qna = new Qna(name, personid, title, content, date, commentcount, likecount, qid, likepeoplelist);
                                    update_like(qna);

                                    return;
                                }
                            });
                    AlertDialog alert = alert_confirm.create();
                    alert.show();

                }

            }
        });
        setRecyclerView();

        commentinfo(qid);
      /*  Qna qna=new Qna(name, personid, title, content, date, commentcount, likecount, qid,likepeoplelist);
        update_like(qna);

       */
        if ((dataManager.getUser().getId()).equals(personid)) {
            btn_rewrite.setVisibility(ImageButton.VISIBLE);
            btn_delete.setVisibility(ImageButton.VISIBLE);
        } else {
            btn_rewrite.setVisibility(ImageButton.GONE);
            btn_delete.setVisibility(ImageButton.GONE);
        }
        //새로고침 당겨서 하기 구현
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                commentinfo(qid);
            }
        });
    }


    //댓글쓰기
    //서버 연결 후 어댑터 연결
    public void commentinfo(String qid) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-3-139-15-252.us-east-2.compute.amazonaws.com:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CommentInterface retrofitService = retrofit.create(CommentInterface.class);
        Call<List<Comment>> call = retrofitService.getByQid(qid);

        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (response.isSuccessful()) {
                    List<Comment> comment = response.body();

                    adapter = new ReplyTalkAdapter(getApplicationContext(), comment, name,personid,title, content, date, commentcount, likecount, qid, likepeoplelist,commentpeoplelist);
                    commentcount = adapter.items.size();
                    recyclerView.setAdapter(adapter);
                    refreshLayout.setRefreshing(false);

                    Log.e("데이터 읽어오기 성공", String.valueOf(commentcount));


                    txt_view.setText(String.valueOf(commentcount));


                } else {
                    Log.d(TAG, "onResponse1: Something Wrong");
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                Log.d("에러다 짜샤", t.getMessage());
            }
        });
    }

    public void post(Comment comment) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-3-139-15-252.us-east-2.compute.amazonaws.com:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        commentcount += 1;
        CommentInterface retrofitService = retrofit.create(CommentInterface.class);
        Call<Comment> call = retrofitService.addComment(comment);


        call.enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(Call<Comment> call, Response<Comment> response) {
                if (response.isSuccessful()) {
                    Log.d("--------------성공!", response.body().toString());

                    txt_view.setText(String.valueOf(commentcount));
                    setResult(RESULT_OK);

                    System.out.println(commentcount);

                } else {
                    Log.d(TAG, "onResponse1: Something Wrong");
                }
            }

            @Override
            public void onFailure(Call<Comment> call, Throwable t) {
                Log.e("왜 안되는건데...?", t.getMessage());
            }
        });
        Qna qna = new Qna(name, personid, title, content, date, commentcount, likecount, qid, likepeoplelist, commentpeoplelist);
        System.out.println(commentcount);
        QnaInterface retrofitService1 = retrofit.create(QnaInterface.class);
        Call<Qna> call2 = retrofitService1.updateQna(qid, qna);
        call2.enqueue(new Callback<Qna>() {
            @Override
            public void onResponse(Call<Qna> call, Response<Qna> response) {
                if (response.isSuccessful()) {
                    Log.d("--------------성공!", response.body().toString());

                    setResult(RESULT_OK);


                } else {
                    Log.d(TAG, "onResponse1: Something Wrong");
                }
            }

            @Override
            public void onFailure(Call<Qna> call, Throwable t) {
                Log.e("왜 안되는건데...?", t.getMessage());
            }
        });


        input_r_content.setText(""); //글쓰고 나서 텍스트 창 초기화
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
   // 좋아요 기능
   public  void update_like(Qna qna) {


       final TextView txt_like = (TextView) findViewById(R.id.txt_like);
       string_like = String.valueOf(likecount);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-3-139-15-252.us-east-2.compute.amazonaws.com:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    QnaInterface retrofitService = retrofit.create(QnaInterface.class);
       Call<Qna> call = retrofitService.updateQna(qid,qna);

       call.enqueue(new Callback<Qna>() {
           @Override
           public void onResponse(Call<Qna> call, Response<Qna> response) {
               Log.d("좋아요 기능 성공!", string_like.toString());
               Toast.makeText(getApplicationContext(),  "감사합니다.", Toast.LENGTH_LONG).show();
               txt_like.setText(string_like);
               setResult(RESULT_OK);

           }

           @Override
           public void onFailure(Call<Qna> call, Throwable t) {
               Log.e("좋아요 기능 실패ㅠㅠ", t.getMessage());
           }

       });
   }


    public void setRecyclerView() {
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



        likecount = getIntent().getIntExtra("like", 0);
        string_like = String.valueOf(likecount); //인트형의 좋아요 수를 스트링 형으로 변환한 것


        name = getIntent().getStringExtra("name");
        date = getIntent().getStringExtra("date");
        title = getIntent().getStringExtra("title");
        content = getIntent().getStringExtra("content");
        personid = getIntent().getStringExtra("id");
        qid = getIntent().getStringExtra("qnaid");
        likepeoplelist = getIntent().getStringArrayListExtra("likepeople");
        commentpeoplelist = getIntent().getStringArrayListExtra("commentpeople");


        txt_name.setText(name);
        txt_date.setText(date);
        txt_title.setText(title);
        txt_content.setText(content);
        txt_like.setText(string_like);


        Log.d(name, "-------------제발!!-성공!");
    }

    public void onBackPressed(){
        super.onBackPressed();
    }

}

