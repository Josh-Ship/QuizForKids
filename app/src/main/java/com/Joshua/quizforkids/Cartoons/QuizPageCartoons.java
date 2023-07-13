package com.Joshua.quizforkids.Cartoons;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.Joshua.quizforkids.Login;
import com.Joshua.quizforkids.MyAnimator.MyAnimator;
import com.Joshua.quizforkids.QuizFinish;
import com.Joshua.quizforkids.QuizInstructions;
import com.Joshua.quizforkids.QuizTopicSelection;
import com.Joshua.quizforkids.R;
import com.Joshua.quizforkids.Register;
import com.Joshua.quizforkids.Scores.QuizScore;
import com.Joshua.quizforkids.Scores.QuizScoreHolder;

import java.util.ArrayList;
import java.util.Random;

public class QuizPageCartoons extends AppCompatActivity {

    private QuizScoreHolder quizScoreHolder;

    private String username ="None";
    private ArrayList<QuizItemCartoons> quizItemsList;
    private TextView topic;
    private String topicString;

    private TextView question1;
    private TextView question2;
    private TextView question3;
    private TextView question4;

    private Button answer1Choice1;
    private Button answer1Choice2;
    private Button answer1Choice3;
    private Button selected1;

    private Button answer2Choice1;
    private Button answer2Choice2;
    private Button answer2Choice3;
    private Button selected2;

    private Button answer3Choice1;
    private Button answer3Choice2;
    private Button answer3Choice3;
    private Button selected3;

    private Button answer4Choice1;
    private Button answer4Choice2;
    private Button answer4Choice3;
    private Button selected4;

    private Button backButton;
    private Button finishButton;

    private ArrayList<QuizItemCartoons> cartoons = new ArrayList<>();
    private ArrayList<QuizItemCartoons> cartoonsUsedList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_cartoons);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        topic = (TextView) findViewById(R.id.topic);
        topicString = "Cartoons";
        topic.setText(topicString);

        username = getIntent().getExtras().getString("username");
        quizScoreHolder = new QuizScoreHolder(new QuizScore(username, topicString));



        // Getting the questions
        question1 = findViewById(R.id.question1);
        question2 = findViewById(R.id.question2);
        question3 = findViewById(R.id.question3);
        question4 = findViewById(R.id.question4);

        // Setting up answer buttons
        answer1Choice1 = findViewById(R.id.answer1choice1);
        answer1Choice1.setOnClickListener(question1ButtonClick);
        answer1Choice2 = findViewById(R.id.answer1choice2);
        answer1Choice2.setOnClickListener(question1ButtonClick);
        answer1Choice3 = findViewById(R.id.answer1choice3);
        answer1Choice3.setOnClickListener(question1ButtonClick);


        answer2Choice1 = findViewById(R.id.answer2choice1);
        answer2Choice1.setOnClickListener(question2ButtonClick);
        answer2Choice2 = findViewById(R.id.answer2choice2);
        answer2Choice2.setOnClickListener(question2ButtonClick);
        answer2Choice3 = findViewById(R.id.answer2choice3);
        answer2Choice3.setOnClickListener(question2ButtonClick);

        answer3Choice1 = findViewById(R.id.answer3choice1);
        answer3Choice1.setOnClickListener(question3ButtonClick);
        answer3Choice2 = findViewById(R.id.answer3choice2);
        answer3Choice2.setOnClickListener(question3ButtonClick);
        answer3Choice3 = findViewById(R.id.answer3choice3);
        answer3Choice3.setOnClickListener(question3ButtonClick);


        answer4Choice1 = findViewById(R.id.answer4choice1);
        answer4Choice1.setOnClickListener(question4ButtonClick);
        answer4Choice2 = findViewById(R.id.answer4choice2);
        answer4Choice2.setOnClickListener(question4ButtonClick);
        answer4Choice3 = findViewById(R.id.answer4choice3);
        answer4Choice3.setOnClickListener(question4ButtonClick);

        backButton = findViewById(R.id.quizCartoonBackButton);
        backButton.setOnClickListener(backButtonClick);

        finishButton = findViewById(R.id.quizCartoonFinishButton);
        finishButton.setOnClickListener(finishButtonClick);

        // Set quiz info
        setQuizInfo();
    }


    private void setQuizInfo() {

        Register.quizDataCartoons.getQuestionsCartoons(cartoonsUsedList);

        // Setting questions and answers to the buttons
        question1.setText(cartoonsUsedList.get(0).getQuestion());
        answer1Choice1.setText(cartoonsUsedList.get(0).getAnswer());
        answer1Choice2.setText(cartoonsUsedList.get(0).getAnswer2());
        answer1Choice3.setText(cartoonsUsedList.get(0).getAnswer3());

        question2.setText(cartoonsUsedList.get(1).getQuestion());
        answer2Choice1.setText(cartoonsUsedList.get(1).getAnswer());
        answer2Choice2.setText(cartoonsUsedList.get(1).getAnswer2());
        answer2Choice3.setText(cartoonsUsedList.get(1).getAnswer3());

        question3.setText(cartoonsUsedList.get(2).getQuestion());
        answer3Choice1.setText(cartoonsUsedList.get(2).getAnswer());
        answer3Choice2.setText(cartoonsUsedList.get(2).getAnswer2());
        answer3Choice3.setText(cartoonsUsedList.get(2).getAnswer3());

        question4.setText(cartoonsUsedList.get(3).getQuestion());
        answer4Choice1.setText(cartoonsUsedList.get(3).getAnswer());
        answer4Choice2.setText(cartoonsUsedList.get(3).getAnswer2());
        answer4Choice3.setText(cartoonsUsedList.get(3).getAnswer3());
    }

    // Onclick listners for buttons, clicked buttons get highlighted so user can see
    // selection choice.
    private View.OnClickListener question1ButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button answer1Button = findViewById(R.id.answer1choice1);
            Button answer2Button = findViewById(R.id.answer1choice2);
            Button answer3Button = findViewById(R.id.answer1choice3);
            Button selected = (Button) view.findViewById(view.getId());

            MyAnimator.shakeAnimationOnView(selected);

            answer1Button.setBackgroundResource(android.R.drawable.btn_default_small);
            answer2Button.setBackgroundResource(android.R.drawable.btn_default_small);
            answer3Button.setBackgroundResource(android.R.drawable.btn_default_small);

            selected.setBackgroundColor(Color.parseColor("#FFA500"));
            selected1 = selected;
        }
    };

    private View.OnClickListener question2ButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button answer1Button = findViewById(R.id.answer2choice1);
            Button answer2Button = findViewById(R.id.answer2choice2);
            Button answer3Button = findViewById(R.id.answer2choice3);
            Button selected = (Button) view.findViewById(view.getId());

            MyAnimator.shakeAnimationOnView(selected);

            answer1Button.setBackgroundResource(android.R.drawable.btn_default_small);
            answer2Button.setBackgroundResource(android.R.drawable.btn_default_small);
            answer3Button.setBackgroundResource(android.R.drawable.btn_default_small);

            selected.setBackgroundColor(Color.parseColor("#FFA500"));
            selected2 = selected;
        }
    };

    private View.OnClickListener question3ButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button answer1Button = findViewById(R.id.answer3choice1);
            Button answer2Button = findViewById(R.id.answer3choice2);
            Button answer3Button = findViewById(R.id.answer3choice3);
            Button selected = (Button) view.findViewById(view.getId());

            MyAnimator.shakeAnimationOnView(selected);

            answer1Button.setBackgroundResource(android.R.drawable.btn_default_small);
            answer2Button.setBackgroundResource(android.R.drawable.btn_default_small);
            answer3Button.setBackgroundResource(android.R.drawable.btn_default_small);

            selected.setBackgroundColor(Color.parseColor("#FFA500"));
            selected3 = selected;
        }
    };

    private View.OnClickListener question4ButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button answer1Button = findViewById(R.id.answer4choice1);
            Button answer2Button = findViewById(R.id.answer4choice2);
            Button answer3Button = findViewById(R.id.answer4choice3);
            Button selected = (Button) view.findViewById(view.getId());

            MyAnimator.shakeAnimationOnView(selected);

            answer1Button.setBackgroundResource(android.R.drawable.btn_default_small);
            answer2Button.setBackgroundResource(android.R.drawable.btn_default_small);
            answer3Button.setBackgroundResource(android.R.drawable.btn_default_small);

            selected.setBackgroundColor(Color.parseColor("#FFA500"));
            selected4 = selected;
        }
    };


    // Finish button
    private View.OnClickListener finishButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (selected1 != null)
                if (selected1.getText().toString().compareTo(cartoonsUsedList.get(0).getCorrect()) == 0)
                    quizScoreHolder.increaseScore();

            if (selected2 != null)
                if (selected2.getText().toString().compareTo(cartoonsUsedList.get(1).getCorrect()) == 0)
                    quizScoreHolder.increaseScore();

            if (selected3 != null)
                if (selected3.getText().toString().compareTo(cartoonsUsedList.get(2).getCorrect()) == 0)
                    quizScoreHolder.increaseScore();

            if (selected4 != null)
                if (selected4.getText().toString().compareTo(cartoonsUsedList.get(3).getCorrect()) == 0)
                    quizScoreHolder.increaseScore();

            quizScoreHolder.finish();

            Intent intent = new Intent(QuizPageCartoons.this, QuizFinish.class);
            intent.putExtra("username", username);
            intent.putExtra("topic", topicString);

            startActivity(intent);
        }
    };

    private View.OnClickListener backButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(QuizPageCartoons.this , QuizTopicSelection.class);
            startActivity(intent);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toolbarHelp:
                if (Login.loggedIn) {
                    Intent intent = new Intent(getApplicationContext(), QuizInstructions.class);
                    startActivity(intent);
                } else
                    Toast.makeText(this, "You need to be logged in to use this!", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }


}