package com.Joshua.quizforkids;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.Joshua.quizforkids.MyAnimator.MyAnimator;
import com.Joshua.quizforkids.Recycler.QuizPreviouisScoresRecycler;
import com.Joshua.quizforkids.Scores.QuizScore;
import com.Joshua.quizforkids.Scores.QuizScoreHolder;


public class QuizPreviousScores extends AppCompatActivity {


    private RecyclerView listOfPreviousScores;
    private TextView dateSort;
    private TextView scoreSort;
    private TextView topicSort;
    private TextView usernameSort;

    private Button backtoQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_previous_scores);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dateSort = findViewById(R.id.QuizPreviousScoresDate);
        dateSort.setOnClickListener(dateSortClicked);

        scoreSort = findViewById(R.id.QuizPreviousScorePoints);
        scoreSort.setOnClickListener(scoreSortClicked);

        topicSort = findViewById(R.id.QuizPreviousTopicSort);
        topicSort.setOnClickListener(topicSortClicked);

        usernameSort = findViewById(R.id.QuizPreviousScoresUsernameSort);
        usernameSort.setOnClickListener(usernameSortClicked);

        backtoQuiz = findViewById(R.id.QuizPreviousScoresBackButton);
        // Lazy
        backtoQuiz.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View view) {
                                              startActivity(new Intent(QuizPreviousScores.this, QuizTopicSelection.class));
                                          }
                                      });

        Log.i("Debug:", "How many items are in ScoreHolder?: " + new QuizScoreHolder().getScores().size());
        for (QuizScore score : new QuizScoreHolder().getScores())
            Log.i("Debug", "Here are the scores: " + "Username: " + score.getUsername() + " Topic: " + score.getTopic() + " Date: " + score.getDate().toString() + " Score: " + score.getScore());

        listOfPreviousScores = findViewById(R.id.QuizPreivousScoresRcycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        listOfPreviousScores.setLayoutManager(layoutManager);
        listOfPreviousScores.setItemAnimator(new DefaultItemAnimator());
        listOfPreviousScores.setAdapter(new QuizPreviouisScoresRecycler());
    }

    View.OnClickListener topicSortClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MyAnimator.shakeAnimationOnView(view);
            new QuizScoreHolder().sort("topic");
            listOfPreviousScores = findViewById(R.id.QuizPreivousScoresRcycler);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            listOfPreviousScores.setLayoutManager(layoutManager);
            listOfPreviousScores.setItemAnimator(new DefaultItemAnimator());
            listOfPreviousScores.setAdapter(new QuizPreviouisScoresRecycler());
            printList();
        }
    };

    View.OnClickListener usernameSortClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MyAnimator.shakeAnimationOnView(view);
            new QuizScoreHolder().sort("username");
            listOfPreviousScores = findViewById(R.id.QuizPreivousScoresRcycler);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            listOfPreviousScores.setLayoutManager(layoutManager);
            listOfPreviousScores.setItemAnimator(new DefaultItemAnimator());
            listOfPreviousScores.setAdapter(new QuizPreviouisScoresRecycler());
            printList();
        }
    };

    View.OnClickListener dateSortClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MyAnimator.shakeAnimationOnView(view);
            new QuizScoreHolder().sort("date");
            listOfPreviousScores = findViewById(R.id.QuizPreivousScoresRcycler);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            listOfPreviousScores.setLayoutManager(layoutManager);
            listOfPreviousScores.setItemAnimator(new DefaultItemAnimator());
            listOfPreviousScores.setAdapter(new QuizPreviouisScoresRecycler());
            printList();
        }
    };

    View.OnClickListener scoreSortClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MyAnimator.shakeAnimationOnView(view);
            new QuizScoreHolder().sort("score");
            listOfPreviousScores = findViewById(R.id.QuizPreivousScoresRcycler);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            listOfPreviousScores.setLayoutManager(layoutManager);
            listOfPreviousScores.setItemAnimator(new DefaultItemAnimator());
            listOfPreviousScores.setAdapter(new QuizPreviouisScoresRecycler());
            printList();
        }
    };

    private void printList() {
        QuizScoreHolder quizScoreHolder = new QuizScoreHolder();
        Log.i("Debug: ", "-------");
        for (QuizScore score : quizScoreHolder.getScores()){
            Log.i("Debug: ", "Score: " + score.getTopic() + " Date: " + score.getDate() + " Score: " +score.getScore());
        }
        Log.i("Debug: ", "-------");
    }

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