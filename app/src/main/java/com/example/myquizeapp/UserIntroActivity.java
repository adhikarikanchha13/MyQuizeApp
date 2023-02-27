package com.example.myquizeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserIntroActivity extends AppCompatActivity {
  EditText etUserName;
  Button btnEnter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_user_intro);

    etUserName = findViewById(R.id.etUserName);
    btnEnter = findViewById(R.id.btnEnter);

    btnEnter.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            String userName = etUserName.getText().toString();
            if (userName.length() != 0) {
              Intent intent = new Intent(UserIntroActivity.this, MainActivity.class);
              intent.putExtra("userName", userName);
              startActivity(intent);
            } else {
              Toast.makeText(
                      UserIntroActivity.this, "Please enter your name to start", Toast.LENGTH_LONG)
                  .show();
            }
          }
        });
  }
}
