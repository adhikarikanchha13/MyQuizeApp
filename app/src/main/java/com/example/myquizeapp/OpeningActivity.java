package com.example.myquizeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class OpeningActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening);

        new Handler().postDelayed(() -> {
            // After 1 sec , navigating to main activity
                startActivity(new Intent(OpeningActivity.this,UserIntroActivity.class));
            finish();

        },1000);

    }
}