package com.Joshua.quizforkids.Animals;

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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.Joshua.quizforkids.Login;
import com.Joshua.quizforkids.QuizFinish;
import com.Joshua.quizforkids.QuizInstructions;
import com.Joshua.quizforkids.QuizTopicSelection;
import com.Joshua.quizforkids.R;
import com.Joshua.quizforkids.Register;
import com.Joshua.quizforkids.Scores.QuizScore;
import com.Joshua.quizforkids.Scores.QuizScoreHolder;

import java.util.ArrayList;
import java.util.List;

public class QuizPageAnimals extends AppCompatActivity {

    private QuizScoreHolder quizScoreHolder;
    private ImageView animalImage;


    private TextView page;
    private int pageNumb = 1;

    private TextView answerText;

    private Button nextButton;
    private Button previousButton;
    private Button backToQuizButton;
    private Button finishButton;

    private List<QuizItemAnimals> questions = new ArrayList<>();
    private String[] userAnswers = {"","","",""};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_page_animals);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Register.quizDataAnimals.getQuestionsAnimals(questions);

        quizScoreHolder = new QuizScoreHolder(new QuizScore(getIntent().getExtras().getString("username"), "Animals"));

        animalImage = findViewById(R.id.QuizAnimalsImage);
        animalImage.setBackgroundResource(getImageIdByName(questions.get(0).getResImageName()));

        page = findViewById(R.id.QuizAnimalPage);
        page.setText(pageNumb + "/4");

        answerText = findViewById(R.id.QuizAnimalAnswerText);

        nextButton = findViewById(R.id.QuizAnimalsPageRightButton);
        nextButton.setOnClickListener(nextButtonclicked);

        previousButton = findViewById(R.id.QuizAnimalsPageLeftButton);
        previousButton.setOnClickListener(previousButtonclicked);

        backToQuizButton = findViewById(R.id.QuizAnimalsBackButton);
        backToQuizButton.setOnClickListener(backToQuizButtonClicked);

        finishButton = findViewById(R.id.QuizAnimalsFinishButton);
        finishButton.setOnClickListener(finishButtonClicked);



        Toast.makeText(this, "Answer: " + questions.get(0).getAnimalName(), Toast.LENGTH_SHORT).show();

    }

    View.OnClickListener nextButtonclicked = view -> nextPage();

    View.OnClickListener previousButtonclicked = view -> previousPage();

    View.OnClickListener finishButtonClicked = view -> finishAnimalQuiz();

    View.OnClickListener backToQuizButtonClicked = view -> backToQuizSelection();

    private void finishAnimalQuiz(){
        setPageAnswers();
        for (int i = 0; i < 4; i++){
            Log.i("Debug: ", "Finish Animal Quiz User answer: " + i+ ": " + userAnswers[i] + " Actual Answer: "+ questions.get(i).getAnimalName());
            Log.i("Debug: ", "Correct?: " + userAnswers[i].compareTo(questions.get(i).getAnimalName()));
            if (userAnswers[i].compareTo(questions.get(i).getAnimalName()) == 0){
                quizScoreHolder.increaseScore();
            }
        }
        quizScoreHolder.finish();
        Intent intent = new Intent(this, QuizFinish.class);
        intent.putExtra("username", getIntent().getExtras().getString("username"));
        intent.putExtra("topic", "Animals");
        startActivity(intent);
    }

    private void backToQuizSelection(){
        Intent intent = new Intent(this, QuizTopicSelection.class);
        startActivity(intent);
    }


    private int getImageIdByName(String nameOfFile){
        return this.getResources().getIdentifier(nameOfFile, "drawable", this.getPackageName());
    }

    private void nextPage(){
        Log.i("Page Number: ", ""+pageNumb);
        if (pageNumb != 4){
            setPageAnswers();
            pageNumb++;
            page.setText(pageNumb+"/4");
            setAnimalImage(pageNumb-1);
        }
        Log.i("Page Number: ", ""+pageNumb);
    }

    private void previousPage(){
        Log.i("Page Number: ", ""+pageNumb);
        if (pageNumb != 1) {
            setPageAnswers();
            pageNumb--;
            page.setText(pageNumb + "/4");
            setAnimalImage(pageNumb - 1);
        }
        Log.i("Page Number: ", ""+pageNumb);

    }

    private void setPageAnswers(){
        userAnswers[pageNumb-1] = answerText.getText().toString();
        answerText.setText("");
    }

    private void setAnimalImage(int index) {
        Toast.makeText(this, "Answer :" + questions.get(index).getAnimalName(), Toast.LENGTH_LONG).show();
        animalImage.setBackgroundResource(getImageIdByName(questions.get(index).getResImageName()));
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