package com.Joshua.quizforkids;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.Joshua.quizforkids.Animals.QuizPageAnimals;
import com.Joshua.quizforkids.Cartoons.QuizPageCartoons;

public class QuizTopicSelection extends AppCompatActivity {

    Button animalQuizButton;
    Button cartoonQuizButton;
    Button logoutButton;
    Button instructionsButton;
    Button scoresButton;

    String username = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_topic_selection);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        username = Login.username;

        animalQuizButton = findViewById(R.id.selectionAnimalButton);
        animalQuizButton.setOnClickListener(animalQuizButtonClicked);

        cartoonQuizButton = findViewById(R.id.selectionCartoonButton);
        cartoonQuizButton.setOnClickListener(cartooonQuizButtonClicked);

        logoutButton = findViewById(R.id.logOffButton);
        logoutButton.setOnClickListener(logoutButtonClicked);

        instructionsButton = findViewById(R.id.instructionsButton);
        instructionsButton.setOnClickListener(instructionsButtonClicked);

        scoresButton = findViewById(R.id.selectionPreviousScores);
        scoresButton.setOnClickListener(scoresButtonClicked);
    }

    View.OnClickListener scoresButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(QuizTopicSelection.this, QuizPreviousScores.class);
            startActivity(intent);
        }
    };

    View.OnClickListener logoutButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(QuizTopicSelection.this, Register.class);
            startActivity(intent);
        }
    };

    View.OnClickListener instructionsButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(QuizTopicSelection.this, QuizInstructions.class);
            startActivity(intent);
        }
    };

    View.OnClickListener cartooonQuizButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent( QuizTopicSelection.this , QuizPageCartoons.class);
            intent.putExtra("username", username);
            startActivity(intent);
        }
    };


    View.OnClickListener animalQuizButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent( QuizTopicSelection.this , QuizPageAnimals.class);
            intent.putExtra("username", username);
            startActivity(intent);
        }
    };

    View.OnClickListener instructionsQuizButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(QuizTopicSelection.this, QuizInstructions.class);
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