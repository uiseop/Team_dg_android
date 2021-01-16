package com.course.capstone;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class BoardWriteActivity extends AppCompatActivity {
    private EditText titleEditText;
    private EditText contentsEditText;
    private Spinner board_spinner;
    private int currentPosition;
    private boolean rewrite;
    private String postKey;
   // private PostModel postModel;
    private ActionBar actionBar;
    private TextView toolbarText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_write);
    }
}