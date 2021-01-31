package com.course.capstone.activities;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.course.capstone.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class YoutubeActivity extends YouTubeBaseActivity {
    YouTubePlayerView youTubePlayerView;
    Button btn;
    YouTubePlayer.OnInitializedListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);

        btn = findViewById(R.id.youtubeBtn);
        youTubePlayerView = findViewById(R.id.youtubeView);
        listener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo("zR7B8Dp-Hbw&t=189s"); //
                //https://www.youtube.com/watch?v=NmkYHmiNArc 유투브에서 v="" 이부분이 키에 해당
                //loadVideo 안에 키를 넣으면 해당 영상 재생
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                youTubePlayerView.initialize("AIzaSyDkOc-7LFsvFX8DEXqVYD624UGWDO_9ERk", listener);

            }
        });
    }
}
