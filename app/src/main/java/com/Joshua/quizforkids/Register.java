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

import com.Joshua.quizforkids.Database.QuizDataAnimals;
import com.Joshua.quizforkids.Database.QuizDataCartoons;
import com.Joshua.quizforkids.Database.UsersDatabaseHelper;

public class Register extends AppCompatActivity {

    Button registerButton;
    public static UsersDatabaseHelper usersDatabaseHelper;
    public static QuizDataAnimals quizDataAnimals;
    public static QuizDataCartoons quizDataCartoons;

    TextView registerEmail;    boolean emailClicked = false;
    TextView registerUsername; boolean usernameClicked = false;
    TextView registerPassword; boolean passwordClicked = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Login.loggedIn = false;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        if (usersDatabaseHelper == null)
            usersDatabaseHelper = new UsersDatabaseHelper(this, null, null, 1);

        if (quizDataAnimals == null) {
            quizDataAnimals = new QuizDataAnimals(this, null, null, 1);
            quizDataAnimals.insertData();
            quizDataAnimals.getAllAnimals();
        }

        if (quizDataCartoons == null) {
            quizDataCartoons = new QuizDataCartoons(this, null, null, 1);
            quizDataCartoons.insertData();
        }

        registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(registerOnclick);

        registerEmail = (TextView) findViewById(R.id.registerEmail);
        registerEmail.setOnFocusChangeListener(firstTimeFocus);

        registerUsername = (TextView) findViewById(R.id.registerUsername);
        registerUsername.setOnFocusChangeListener(firstTimeFocus);

        registerPassword = (TextView) findViewById(R.id.registerPassword);
        registerPassword.setOnFocusChangeListener(firstTimeFocus);


    }

    View.OnClickListener registerOnclick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            usersDatabaseHelper.insert(registerEmail.getText().toString(), registerUsername.getText().toString(), registerPassword.getText().toString());
            usersDatabaseHelper.getAll();

            Intent intent = new Intent(Register.this, Login.class);
            startActivity(intent);
        }
    };

    View.OnFocusChangeListener firstTimeFocus = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean b) {
            switch (view.getId()){
                case R.id.registerEmail:
                    if (!emailClicked){
                        registerEmail.setText("");
                        emailClicked = true;
                    }
                    break;
                case R.id.registerUsername:
                    if (!usernameClicked){
                        registerUsername.setText("");
                        usernameClicked = true;
                    }
                    break;
                case R.id.registerPassword:
                    if (!passwordClicked){
                        registerPassword.setText("");
                        passwordClicked = true;
                    }
                    break;
            }
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
        switch (item.getItemId()){
            case R.id.toolbarHelp:
                if (Login.loggedIn){
                    Intent intent = new Intent(getApplicationContext(), QuizInstructions.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(this, "You need to be logged in to use this!", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}