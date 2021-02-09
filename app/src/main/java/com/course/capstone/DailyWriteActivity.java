package com.course.capstone;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

import com.course.capstone.models.DataManager;
import com.course.capstone.models.FCMInterface;
import com.course.capstone.models.GenericResponse;
import com.course.capstone.models.Knowledge;
import com.course.capstone.models.KnowledgeInterface;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class DailyWriteActivity extends AppCompatActivity {
    private ActionBar actionBar;
    private TextView toolbarText;
    private EditText input_id;
    private EditText input_pwd;
    private EditText input_title;
    private EditText input_content;
    DataManager dataManager = DataManager.getInstance();
    TextView tv_title;
    ImageButton btn_write;
    SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = new Date();
    String time1 = format1.format(date);
    String retitle;
    String recontent;
    String reqid;
    String rename;
    String redate;
    String repersonid;
    int edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_write);
        toolbarText = findViewById(R.id.toolbartext);
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true); //커스터마이징 하기 위해 필요
        actionBar.setDisplayShowTitleEnabled(false);

        input_title = findViewById(R.id.input_title);
        input_content = findViewById(R.id.input_content);
        btn_write = (ImageButton) findViewById(R.id.btn_write);

        retitle = getIntent().getStringExtra("knowledgetitle");
        input_title.setText(retitle);
        recontent = getIntent().getStringExtra("knowledgecontent");
        input_content.setText(recontent);
        reqid = getIntent().getStringExtra("id");
        rename = getIntent().getStringExtra("knowledgepeople");
        repersonid = getIntent().getStringExtra("writerid");
        edit = getIntent().getIntExtra("edit", 0);
        redate = getIntent().getStringExtra("knowledgedate");
        System.out.println(edit);


        //공유버튼 클릭시 alert창

        btn_write.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert_confirm = new AlertDialog.Builder(DailyWriteActivity.this);
                alert_confirm.setMessage("글을 게시하겠습니까?").setCancelable(false).setPositiveButton("확인",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if (edit == 1) {
                                    Knowledge rewrite_knowledge = new Knowledge(input_title.getText().toString(), rename, input_content.getText().toString(), repersonid, redate, reqid);
                                    System.out.println(redate);
                                    rewrite(rewrite_knowledge);
                                } else {
                                    Knowledge new_knowledge = new Knowledge(input_title.getText().toString(), dataManager.getUser().getUserid(), input_content.getText().toString(), dataManager.getUser().getId(), time1);
                                    post(new_knowledge);

                                    Retrofit retrofit = new Retrofit.Builder()
                                            .baseUrl("http://ec2-3-139-15-252.us-east-2.compute.amazonaws.com:8080/")
                                            .addConverterFactory(GsonConverterFactory.create())
                                            .build();

                                    HashMap<String, Object> hashMap = new HashMap<>();
                                    hashMap.put("newstitle", input_title.getText().toString());

                                    FCMInterface fcmInterface = retrofit.create(FCMInterface.class);
                                    Call<GenericResponse> call = fcmInterface.daily(hashMap);
                                    call.enqueue(new Callback<GenericResponse>() {
                                        @Override
                                        public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {
                                            Log.d(TAG, "알림 메시지 보내기 성공");
                                        }

                                        @Override
                                        public void onFailure(Call<GenericResponse> call, Throwable t) {
                                            Log.d(TAG, "알림 메시지 보내기 실패");
                                        }
                                    });
                                    Log.d(dataManager.getUser().getName(), "이름");
                                }

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
                //Intent intent=new Intent(BoardWriteActivity.this,CommonBoardFragment.class);

            }
        });
    }


    public void post(Knowledge knowledge) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-3-139-15-252.us-east-2.compute.amazonaws.com:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        KnowledgeInterface knowledgeInterface = retrofit.create(KnowledgeInterface.class);
        Call<Knowledge> call = knowledgeInterface.addKnowledge(knowledge);

        call.enqueue(new Callback<Knowledge>() {
            @Override
            public void onResponse(Call<Knowledge> call, Response<Knowledge> response) {
                if (response.isSuccessful()) {
                    Log.d("--------------성공!", response.body().toString());

                    setResult(RESULT_OK);
                    finish();

                } else {
                    Log.d(TAG, "onResponse1: Something Wrong");
                }
            }

            @Override
            public void onFailure(Call<Knowledge> call, Throwable t) {
                Toast.makeText(getBaseContext(), "목록을 불러올 수 없습니다.", Toast.LENGTH_LONG).show();
                ;
                Log.d(TAG, "onFailure2: 게시물 목록 왜안나와");
            }
        });
    }

    public void rewrite(Knowledge knowledge) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-3-139-15-252.us-east-2.compute.amazonaws.com:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        KnowledgeInterface knowledgeInterface = retrofit.create(KnowledgeInterface.class);
        Call<Knowledge> call = knowledgeInterface.updateKnowledge(reqid, knowledge);

        call.enqueue(new Callback<Knowledge>() {
            @Override
            public void onResponse(Call<Knowledge> call, Response<Knowledge> response) {
                if (response.isSuccessful()) {
                    setResult(RESULT_OK);
                    finish();

                } else {
                    Log.d(TAG, "onResponse1: Something Wrong");
                }
            }

            @Override
            public void onFailure(Call<Knowledge> call, Throwable t) {
                Toast.makeText(getBaseContext(), "목록을 불러올 수 없습니다.", Toast.LENGTH_LONG).show();
                ;
                Log.d(TAG, "onFailure2:수정 왜안돼");
            }
        });

    }

}
