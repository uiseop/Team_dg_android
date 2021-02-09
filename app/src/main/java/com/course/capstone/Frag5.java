package com.course.capstone;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class Frag5 extends AppCompatActivity {
    WebView webView;
    String url;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cross_activity);

        Intent intent = getIntent();
        url = intent.getStringExtra("url");

        Log.d("haa",url);

        webView = (WebView)findViewById(R.id.wv);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setSupportZoom(true);

//        progressBar = findViewById(R.id.progressBar2);
//        progressBar.setVisibility(View.GONE);

        webView.loadUrl(url);
    }
}
