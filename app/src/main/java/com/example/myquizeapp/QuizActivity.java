package com.example.myquizeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {

  TextView totalQuestionsTextView;
  TextView textTimerTextview;
  TextView questionTextView;
  Button ansA, ansB, ansC, ansD;
  Button submitBtn;

  int score = 0;
  int totalQuestion = QuestionAnswer.question.length;
  int currentQuestionIndex = 0;
  String selectedAnswer = "";

  //    countdown

  private static final long START_TIME_IN_MILLIS = 1800000;

  private TextView mTextViewCountDown;
  private Button mButtonStartPause;
  private Button mButtonReset;

  private CountDownTimer mCountDownTimer;

  private boolean mTimerRunning;

  private long mTimeLeftInMillis = START_TIME_IN_MILLIS;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_quiz);

    totalQuestionsTextView = findViewById(R.id.total_question);

    questionTextView = findViewById(R.id.question);

    ansA = findViewById(R.id.ans_A);
    ansB = findViewById(R.id.ans_B);
    ansC = findViewById(R.id.ans_C);
    ansD = findViewById(R.id.ans_D);
    submitBtn = findViewById(R.id.submit_btn);

    ansA.setOnClickListener(this);
    ansB.setOnClickListener(this);
    ansC.setOnClickListener(this);
    ansD.setOnClickListener(this);
    submitBtn.setOnClickListener(this);

    totalQuestionsTextView.setText("Total questions :" + "1/" + totalQuestion);

    loadNewQuestion();

    //        countdown

    mTextViewCountDown = findViewById(R.id.text_view_countdown);

    updateCountDownText();
  }

  private void startTimer() {
    mCountDownTimer =
        new CountDownTimer(mTimeLeftInMillis, 1000) {
          @Override
          public void onTick(long millisUntilFinished) {
            mTimeLeftInMillis = millisUntilFinished;
            updateCountDownText();
          }

          @Override
          public void onFinish() {
            mTimerRunning = false;
            mButtonStartPause.setText("Start");
            mButtonStartPause.setVisibility(View.INVISIBLE);
          }
        }.start();

    mTimerRunning = true;
  }


  
  //    update timer

  private void updateCountDownText() {
    int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
    int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

    String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

    mTextViewCountDown.setText(timeLeftFormatted);
  }

  @Override
  public void onClick(View view) {

    ansA.setBackgroundColor(Color.WHITE);
    ansB.setBackgroundColor(Color.WHITE);
    ansC.setBackgroundColor(Color.WHITE);
    ansD.setBackgroundColor(Color.WHITE);
    startTimer();

    Button clickedButton = (Button) view;
    //        submit button clicked

    if (clickedButton.getId() == R.id.submit_btn) {

      if (selectedAnswer.equals(QuestionAnswer.correctAnswers[currentQuestionIndex])) {
        score++;
      }
      int qsnCount = currentQuestionIndex++;
      loadNewQuestion();

      startTimer();
      if (currentQuestionIndex != totalQuestion) {
        totalQuestionsTextView.setText("Total questions :" + (qsnCount + 2) + "/" + totalQuestion);
      } else {
        totalQuestionsTextView.setText("Total questions :" + "1/" + totalQuestion);
      }

    } else {
      // choices button clicked
      selectedAnswer = clickedButton.getText().toString();
      clickedButton.setBackgroundColor(Color.MAGENTA);
    }
  }

  void loadNewQuestion() {
    if (currentQuestionIndex == totalQuestion) {
      mCountDownTimer.cancel();
      finishQuiz();
      return;
    }

    questionTextView.setText(QuestionAnswer.question[currentQuestionIndex]);
    ansA.setText(QuestionAnswer.choices[currentQuestionIndex][0]);
    ansB.setText(QuestionAnswer.choices[currentQuestionIndex][1]);
    ansC.setText(QuestionAnswer.choices[currentQuestionIndex][2]);
    ansD.setText(QuestionAnswer.choices[currentQuestionIndex][3]);
  }

  void finishQuiz() {
    String passStatus = "";
    if (score > totalQuestion * 0.60) {
      passStatus = "Passed";
    } else {
      passStatus = "Failed! Try again";
    }

    new AlertDialog.Builder(this)
        .setTitle(passStatus)
        .setMessage("Score is " + score + " Out of" + totalQuestion)
        .setPositiveButton("Restart", (dialogInterface, i) -> restartQuiz())
        .setCancelable(false)
        .show();

    Intent iFinish = new Intent(QuizActivity.this, MainActivity.class);
    startActivity(iFinish);
  }

  void restartQuiz() {
    score = 0;
    currentQuestionIndex = 0;
    loadNewQuestion();
  }
}
