package com.example.merousha.braintrain;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    Button startButton2;
    Button but1;
    Button but2;
    Button but3;
    Button but4;
    Button but5;
    TextView result;
    TextView points;
    TextView sumText;
    TextView timer;
    ConstraintLayout game;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int correctIndex;
    int score = 0;
    int numQuestions = 0;
    Boolean active;

    public void playAgain(final View view) {
        score = 0;
        numQuestions = 0;
        timer.setText("40s");
        points.setText("0/0");
        result.setText("");
        but5.setVisibility(view.INVISIBLE);


        active = true;

        CountDownTimer countDownTimer = new CountDownTimer(40000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(String.valueOf(millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {
                timer.setText("0s");
                result.setText("Your Score is: " + Integer.toString(score) + "/" + Integer.toString(numQuestions));
                but5.setVisibility(view.VISIBLE);
                active = false;
            }
        }.start();
    }


    public void chooseAnswer(View view) {
        if (active) {
            if (view.getTag().toString().equals(Integer.toString(correctIndex))) {
                score++;
                result.setText("Correct!");
            } else {
                result.setText("Wrong!");
            }
            numQuestions++;
            points.setText(Integer.toString(score) + "/" + Integer.toString(numQuestions));
            onClick(view);
        }
    }

    public void start(View view) {
        startButton.setVisibility(view.INVISIBLE);
        startButton2.setVisibility(view.INVISIBLE);
        game.setVisibility(ConstraintLayout.VISIBLE);
        playAgain(but5);
    }

    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.startButton:
                
                Random rand = new Random();
                int a = rand.nextInt(51);
                int b = rand.nextInt(50);

                sumText.setText(String.valueOf(a) + " + " + String.valueOf(b) + " = ");

                correctIndex = rand.nextInt(4);

                answers.clear();

                int incorrectAnswer;

                for (int i = 0; i < 4; i++) {
                    if (i == correctIndex) answers.add(a + b);
                    else {
                        incorrectAnswer = rand.nextInt(100);
                        while (incorrectAnswer == a + b) incorrectAnswer = rand.nextInt(201);
                        answers.add(incorrectAnswer);
                    }
                }

                but1.setText(Integer.toString(answers.get(0)));
                but2.setText(Integer.toString(answers.get(1)));
                but3.setText(Integer.toString(answers.get(2)));
                but4.setText(Integer.toString(answers.get(3)));
                break;

            case R.id.startButton2:

                break;

            default:
                break;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button)findViewById(R.id.startButton);
        startButton.setOnClickListener((View.OnClickListener) this);
        startButton2 = (Button)findViewById(R.id.startButton2);
        startButton2.setOnClickListener((View.OnClickListener) this);
        sumText = (TextView)findViewById(R.id.sumText);
        but1 = (Button)findViewById(R.id.button1);
        but2 = (Button)findViewById(R.id.button2);
        but3 = (Button)findViewById(R.id.button3);
        but4 = (Button)findViewById(R.id.button4);
        but5 = (Button)findViewById(R.id.playAgain);
        result = (TextView)findViewById(R.id.resultText);
        points = (TextView)findViewById(R.id.pointText);
        timer = (TextView)findViewById(R.id.timerText);
        game = (ConstraintLayout)findViewById(R.id.game);
    }
}

