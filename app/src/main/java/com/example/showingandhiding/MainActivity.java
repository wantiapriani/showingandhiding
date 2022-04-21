package com.example.showingandhiding;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button startBtn;
    Button againBtn;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView sumView;
    TextView resultView;
    TextView pointsView;
    TextView timerView;
    TextView openingText;
    RelativeLayout playGame;

    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationCorrect;
    int score = 0;
    int quesNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startBtn = findViewById(R.id.startBtn);
        sumView = findViewById(R.id.sumView);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        resultView = findViewById(R.id.resultView);
        pointsView = findViewById(R.id.pointsView);
        timerView = findViewById(R.id.timerView);
        openingText = findViewById(R.id.openingText);
        againBtn = findViewById(R.id.againBtn);
        playGame = findViewById(R.id.playGame);

    }
    public void start(View view){
        startBtn.setVisibility(View.INVISIBLE);
        openingText.setVisibility(View.INVISIBLE);
        playGame.setVisibility(RelativeLayout.VISIBLE);

        playAgain(findViewById(R.id.againBtn));
    }

    public void generateQuest(){
        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationCorrect = rand.nextInt(4);
        answers.clear();
        int incorrectAnswer;

        for (int i = 0; i <4; i++){
            if (i == locationCorrect){
                answers.add(a+b);
            } else {
                incorrectAnswer = rand.nextInt(41);
                while (incorrectAnswer == a + b){
                    incorrectAnswer = rand.nextInt(41);
                }
                answers.add(incorrectAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    public void chooseAnswer(View view){
        if (view.getTag().toString().equals(Integer.toString(locationCorrect))){
            score++;
            resultView.setText("Correct!");
        } else {
            resultView.setText("Wrong!");
        }

        quesNum++;
        pointsView.setText(Integer.toString(score) + "/" + Integer.toString(quesNum));
        generateQuest();

    }

    public void playAgain (View view){
        score = 0;
        quesNum = 0;

        timerView.setText("30s");
        pointsView.setText("0/0");
        resultView.setText("");
        againBtn.setVisibility(View.INVISIBLE);
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);

        generateQuest();

        new CountDownTimer(30100, 1000){

            @Override
            public void onTick(long l) {
                timerView.setText(String.valueOf(l/1000) + "s");
            }

            @Override
            public void onFinish() {
                againBtn.setVisibility(View.VISIBLE);
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
                timerView.setText("0s");
                resultView.setText("Your Score: " + Integer.toString(score) + "/" + Integer.toString(quesNum));
            }
        }.start();
    }
}