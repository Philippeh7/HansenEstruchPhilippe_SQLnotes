package com.example.hansenestruchp0969.sqlapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        android.content.Intent intent = getIntent();
        String message = intent.getStringExtra(com.example.hansenestruchp0969.sqlapp.MainActivity.EXTRA_MESSAGE);

        TextView textview = findViewById(R.id.textView2);
        textview.setText(message);

    }
}