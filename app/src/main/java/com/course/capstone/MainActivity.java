package com.course.capstone;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.course.capstone.firebase.MyFirebaseInstanceIDService;
import com.course.capstone.models.DataManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Main_Activity";
    private static final int REQUESTCODE_REVIEW_WRITE = 1002;
    private BottomNavigationView mBottomNavigationView;
    private ActionBar actionBar;
    private ImageView mypageImageButton,search_bttn;
    private TextView mToolbarLeftTitle,nam,pla;
    private String name;
    DataManager dataManager = DataManager.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = dataManager.getUser().getName();
        pla = findViewById(R.id.place);
        nam = findViewById(R.id.username);
        nam.setText(name+"님 환영합니다!");
        pla.setVisibility(TextView.GONE);
        search_bttn = findViewById(R.id.img_search);
        search_bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,BoardSearchActivity.class);
                startActivity(intent);
            }
        });

        //툴바  정의
//        mypageImageButton = findViewById(R.id.toolbar_mypage);
//        mToolbarLeftTitle = findViewById(R.id.toolbartext);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        actionBar = getSupportActionBar();
//        actionBar.setDisplayShowCustomEnabled(true); //커스터마이징 하기 위해 필요
//        actionBar.setDisplayShowTitleEnabled(false);
        mypageImageButton = findViewById(R.id.img_user);
        mypageImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent i = new Intent(context, MypageActivity.class);
                context.startActivity(i);
            }
        });


        // 크롤링 시작

        mBottomNavigationView = findViewById(R.id.bottomNavBar);

        //첫 화면 띄우기
        getSupportFragmentManager().beginTransaction().add(R.id.frame_container, new Frag1()).commit();
//        mToolbarLeftTitle.setText("따끈따끈 금융");
        //case 함수를 통해 클릭 받을 때마다 화면 변경하기
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_search:

                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new Frag1()).commit();
//                        mToolbarLeftTitle.setText("따끈따끈 금융");
                        nam.setText(name+"님 환영합니다!");
                        nam.setVisibility(TextView.VISIBLE);
                        mypageImageButton.setVisibility(ImageButton.VISIBLE);
                        search_bttn.setVisibility(ImageButton.VISIBLE);
                        pla.setVisibility(TextView.GONE);
                        break;
                    case R.id.action_settings:

                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new Frag2()).commit();
//                        mToolbarLeftTitle.setText("게시판");
                        nam.setVisibility(TextView.GONE);
                        pla.setText("게시판");
                        pla.setVisibility(TextView.VISIBLE);
                        mypageImageButton.setVisibility(ImageButton.GONE);
                        search_bttn.setVisibility(ImageButton.GONE);
                        break;
                    case R.id.action_navigation:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new Frag3()).commit();
//                        mToolbarLeftTitle.setText("교육영상");
                        nam.setVisibility(TextView.GONE);
                        pla.setText("교육영상");
                        pla.setVisibility(TextView.VISIBLE);
                        mypageImageButton.setVisibility(ImageButton.GONE);
                        search_bttn.setVisibility(ImageButton.GONE);
                        break;

                    case R.id.action_play:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new Frag4()).commit();
//                        mToolbarLeftTitle.setText("게임");
                        nam.setVisibility(TextView.GONE);
                        pla.setText("게임");
                        pla.setVisibility(TextView.VISIBLE);
                        mypageImageButton.setVisibility(ImageButton.GONE);
                        search_bttn.setVisibility(ImageButton.GONE);
                        break;
                }
                return true;
            }
        });
        //기기 토큰 서버로 전송(푸시 알림용)
        MyFirebaseInstanceIDService myFirebaseInstanceIDService = new MyFirebaseInstanceIDService();
        myFirebaseInstanceIDService.onTokenRefresh();

    }


    public void onActivityResult(ActivityResultEvent activityResultEvent) {
        onActivityResult(activityResultEvent.getRequestCode(), activityResultEvent.getResultCode(), activityResultEvent.getData());
    }

}






