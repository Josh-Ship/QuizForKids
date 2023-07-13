package com.Joshua.quizforkids;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.Joshua.quizforkids.MyAnimator.MyAnimator;

public class QuizInstructions extends AppCompatActivity {

    private TextView textView;

    private Button animals;
    private Button cartoons;
    private Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quit_instructions);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textView = findViewById(R.id.InstructionsText);

        animals = findViewById(R.id.InstructionsAnimalButton);
        animals.setOnClickListener(animalsButtonClicked);


        cartoons = findViewById(R.id.InstructionsCartoonsButton);
        cartoons.setOnClickListener(cartoonsButtonClicked);

        back = findViewById(R.id.InstructionsBackButton);
        back.setOnClickListener(backButtonClicked);

    }

    View.OnClickListener animalsButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            textView.setText("For the animals quiz\nYou have to type in the\nNames of the animals\n"+
                    "The animals can be tricky to get right\nThe answer can be seen at the bottom\nOf the screen if you need help.");
            MyAnimator.shakeAnimationOnView(view);
        }
    };

    View.OnClickListener cartoonsButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            textView.setText("For the cartoons quiz\nYou have to pick the option that\nYou think is right!");
            MyAnimator.shakeAnimationOnView(view);
        }
    };

    View.OnClickListener backButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(QuizInstructions.this, QuizTopicSelection.class);
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