package com.course.capstone;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.course.capstone.adapter.BoardAdapter;
import com.course.capstone.models.DataManager;
import com.course.capstone.models.Qna;
import com.course.capstone.models.QnaInterface;
import com.course.capstone.models.User;

import java.util.List;

import javax.xml.namespace.QName;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class BoardWriteActivity extends AppCompatActivity {



    private ActionBar actionBar;
    private TextView toolbarText;
    private EditText input_id;
    private EditText input_pwd;
    private EditText input_title;
    private EditText input_content;
    DataManager dataManager=DataManager.getInstance();
    TextView tv_title;
    ImageButton btn_write;

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
        input_id = findViewById(R.id.input_id);
        input_pwd = findViewById(R.id.input_pwd);
        input_title = findViewById(R.id.input_title);
        input_content = findViewById(R.id.input_content);
        btn_write = (ImageButton) findViewById(R.id.btn_write);

        //공유버튼 클릭시 alert창
        btn_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert_confirm = new AlertDialog.Builder(BoardWriteActivity.this);
                alert_confirm.setMessage("글을 게시하겠습니까?").setCancelable(false).setPositiveButton("확인",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Qna qna=new Qna( dataManager.getUser().getName(),input_title.getText().toString(),input_content.getText().toString(),null,null,null);
                                post(qna);
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
    }
        public void post(Qna qna) {


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://ec2-13-59-15-254.us-east-2.compute.amazonaws.com:8080/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            QnaInterface qnainterface = retrofit.create(QnaInterface.class);
            Call<Qna> call = qnainterface.addqna(qna);

            call.enqueue(new Callback<Qna>() {
                @Override
                public void onResponse(Call<Qna> call, Response<Qna> response) {
                    if (response.isSuccessful()){
                        Log.d("--------------성공!", response.body().toString());

                        setResult(RESULT_OK);
                        finish();

                    }
                    else{
                        Log.d(TAG, "onResponse1: Something Wrong");
                    }
                }

                @Override
                public void onFailure(Call<Qna>call, Throwable t) {
                    Toast.makeText(getBaseContext(), "목록을 불러올 수 없습니다.",Toast.LENGTH_LONG).show();;
                    Log.d(TAG, "onFailure2: 게시물 목록 왜안나와");
                }
            });
        }
        }



