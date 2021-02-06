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
import android.widget.TextView;
import android.widget.Toast;


import com.course.capstone.firebase.MyFirebaseInstanceIDService;
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
    private ImageButton mypageImageButton;
    private TextView mToolbarLeftTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //툴바  정의
        mypageImageButton = findViewById(R.id.toolbar_mypage);
        mToolbarLeftTitle = findViewById(R.id.toolbartext);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true); //커스터마이징 하기 위해 필요
        actionBar.setDisplayShowTitleEnabled(false);

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
        mToolbarLeftTitle.setText("따끈따끈 금융");
        //case 함수를 통해 클릭 받을 때마다 화면 변경하기
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_search:

                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new Frag1()).commit();
                        mToolbarLeftTitle.setText("따끈따끈 금융");
                        mypageImageButton.setVisibility(ImageButton.VISIBLE);
                        break;
                    case R.id.action_settings:

                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new Frag2()).commit();
                        mToolbarLeftTitle.setText("게시판");
                        mypageImageButton.setVisibility(ImageButton.GONE);
                        break;
                    case R.id.action_navigation:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new Frag3()).commit();
                        break;

                    case R.id.action_play:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new Frag4()).commit();

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






