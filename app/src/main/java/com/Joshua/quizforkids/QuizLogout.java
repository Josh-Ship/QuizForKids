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
import android.widget.TextView;
import android.widget.Toast;

import com.Joshua.quizforkids.Scores.QuizScoreHolder;

public class QuizLogout extends AppCompatActivity {

    private TextView logoutTitle;
    private Button logoutButton;
    private QuizScoreHolder quizScoreHolder;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_logout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        quizScoreHolder = new QuizScoreHolder();

        username = getIntent().getExtras().getString("username");
        logoutTitle = findViewById(R.id.logoutTitle);
        logoutTitle.setText(username + "\nYou have\n" + quizScoreHolder.getTotal(username) + "\n Points Overall!");

        logoutButton = findViewById(R.id.logoutPageLogoutButton);
        logoutButton.setOnClickListener(logoutButtonClick);

    }

    View.OnClickListener logoutButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(QuizLogout.this, Register.class);
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