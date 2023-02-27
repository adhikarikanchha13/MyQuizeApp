package com.example.myquizeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

  Button btnStart;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    btnStart = findViewById(R.id.btnStart);
    Intent intent = getIntent();
    String name = intent.getStringExtra("userName").toUpperCase();

    TextView welcomUser = findViewById(R.id.welcomeUser);
    welcomUser.setText("Welcome, " + name + ". Let\'s start quiz");
    btnStart.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, QuizActivity.class);
            startActivity(intent);
          }
        });
  }
}
