package com.Joshua.quizforkids;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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

import com.Joshua.quizforkids.Animals.QuizPageAnimals;
import com.Joshua.quizforkids.Cartoons.QuizPageCartoons;
import com.Joshua.quizforkids.Scores.QuizScoreHolder;

public class QuizFinish extends AppCompatActivity {

    private TextView title;
    private Button logoutButton;
    private Button backToQuizButton;
    private Button redoQuizButton;
    private Button previousScores;

    private String username;
    private String topic;
    private QuizScoreHolder quizScoreHolder = new QuizScoreHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_finish);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        logoutButton = findViewById(R.id.quizAttemptLoggOffButton);
        logoutButton.setOnClickListener(logoutButtonClick);

        backToQuizButton = findViewById(R.id.backToQuizSelectionButton);
        backToQuizButton.setOnClickListener(backToQuizSelectionButtonClick);

        redoQuizButton = findViewById(R.id.redoQuizButton);
        redoQuizButton.setOnClickListener(redoButtonClick);

        previousScores = findViewById(R.id.QuizFinishSeePreviousAttempts);
        previousScores.setOnClickListener(previousScoresButtonClicked);

        // We could also figure out what the username and topic is
        // through the QuizScore object
        username = getIntent().getExtras().get("username").toString();
        topic = getIntent().getExtras().get("topic").toString();

        title = findViewById(R.id.quizAttemptTitle);

        // Set the text for the scores
        String correct = String.valueOf(quizScoreHolder.getScores().get(quizScoreHolder.getScores().size()-1).getScore());
        String incorrect = String.valueOf(4-Integer.valueOf(correct));
        title.setText("Congratulations " + username + "\nYou have completed the " + topic + " quiz!" +
                "\n You have " + correct + " correct and " + incorrect+ " incorrect answers!\n" +
                "You get " + correct +" points for this attempt. \nOverall you have " + quizScoreHolder.getTotal(username) + " points");

        Log.i("Debug :", "User total points: " + quizScoreHolder.getTotal());
    }

    private View.OnClickListener previousScoresButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(QuizFinish.this, QuizPreviousScores.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener backToQuizSelectionButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(QuizFinish.this, QuizTopicSelection.class);
            intent.putExtra("username", username);
            startActivity(intent);
        }
    };

    private View.OnClickListener logoutButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(QuizFinish.this, QuizLogout.class);
            intent.putExtra("username", username);
            startActivity(intent);
        }
    };

    private View.OnClickListener redoButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String topic = getIntent().getExtras().getString("topic");
            Intent intent = new Intent(QuizFinish.this, QuizPageAnimals.class);
            if (topic.compareTo("Cartoons") == 0)
                intent = new Intent(QuizFinish.this, QuizPageCartoons.class);

            intent.putExtra("username", username);
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