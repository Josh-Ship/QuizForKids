package com.Joshua.quizforkids;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.Joshua.quizforkids.Database.UsersDatabaseHelper;


public class Login extends AppCompatActivity {
    UsersDatabaseHelper usersDatabaseHelper = Register.usersDatabaseHelper;
    TextView loginUserName;
    TextView loginPassword;
    Button loginButton;
    public static String username;
    public static boolean loggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        loginUserName = (TextView) findViewById(R.id.loginUsername);
        loginPassword = (TextView) findViewById(R.id.loginPassword);
        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(loginButtonOnlick);

        loggedIn = false;
    }

    View.OnClickListener loginButtonOnlick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (usersDatabaseHelper.checkLogin(loginUserName.getText().toString(), loginPassword.getText().toString()) == true){
                Intent intent = new Intent(Login.this, QuizTopicSelection.class);
                intent.putExtra("username", loginUserName.getText().toString());
                username = loginUserName.getText().toString();
                loggedIn = true;
                startActivity(intent);
            }
            else
                Toast.makeText(Login.this, "Login Failed!", Toast.LENGTH_SHORT).show();

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