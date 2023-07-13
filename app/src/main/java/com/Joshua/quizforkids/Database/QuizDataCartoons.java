package com.Joshua.quizforkids.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.Joshua.quizforkids.Animals.QuizItemAnimals;
import com.Joshua.quizforkids.Cartoons.QuizItemCartoons;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuizDataCartoons extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "QuizAppDB.db";
    private static final String TABLE_NAME_CARTOONS = "cartoons";
    private static final String CARTOONS_COL_1 = "ID";
    private static final String CARTOONS_COL_2 = "QUESTION";
    private static final String CARTOONS_COL_3 = "ANSWER";
    private static final String CARTOONS_COL_4 = "OPTION1";
    private static final String CARTOONS_COL_5 = "OPTION2";
    private static final String CARTOONS_COL_6 = "OPTION3";




    public QuizDataCartoons(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME_CARTOONS +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT, QUESTION TEXT, ANSWER TEXT, OPTION1 TEXT, OPTION2 TEXT, OPTION3 TEXT)" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_CARTOONS);
        onCreate(sqLiteDatabase);
    }

    public void insertData(){

        // Populate questions for cartoons
        insert(new QuizItemCartoons("What is Bugs Bunny's favourite food?", "Carrots", "Carrots", "Watermelon", "Raddish"));
        insert(new QuizItemCartoons("What color is the Hulk?", "Green", "Red", "Green", "Yellow"));
        insert(new QuizItemCartoons("What animal is batman scared of?", "Bats", "Dogs", "Cats", "Bats"));
        insert(new QuizItemCartoons("What is He-man's real name?", "Prince Adam of Eternia", "Prince Adam of Eternia", "James", "Man-he"));
        insert(new QuizItemCartoons("What is Ned Flanders’s wife’s name in the Simpsons? ", "Maude", "Maude", "Marge", "Jullie"));
        insert(new QuizItemCartoons("When the Tasmanian Devil made his debut, who starred in the cartoon? ", "Bugs Bunny", "Daffy Duck", "Bugs Bunny", "Porky Pig"));
        insert(new QuizItemCartoons("The Flintstones is one of the most popular cartoons in history, but which period is the series based? ", "The Stone Age", "The Stone Age", "The Iron Age", "Age Of Suffering"));
        insert(new QuizItemCartoons("Which cartoon series holds the longest T.V. series and sitcom in terms of episodes and seasons in the United States?", "The Simpsons", "Rick and Mort", "Family Guy", "The Simpsons"));
        insert(new QuizItemCartoons("Who is the antagonist in the “Lion King”? ", "Scar", "Simba", "Scar", "Nala"));
        insert(new QuizItemCartoons("What is the dog’s name in the series “Tom and Jerry”?", "Spike", "Spot", "Spike", "Bruce"));
    }

    public boolean insert(QuizItemCartoons quizItemCartoons) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CARTOONS_COL_2, quizItemCartoons.getQuestion());
        contentValues.put(CARTOONS_COL_3, quizItemCartoons.getCorrect());
        contentValues.put(CARTOONS_COL_4, quizItemCartoons.getAnswer());
        contentValues.put(CARTOONS_COL_5, quizItemCartoons.getAnswer2());
        contentValues.put(CARTOONS_COL_6, quizItemCartoons.getAnswer3());

        Log.i("Insert: ", "insert: " + contentValues);

        long res = db.insert(TABLE_NAME_CARTOONS, null, contentValues);
        if (res == -1)
            return false; Log.e("Insert cartoons fail", "Failed");
        return true;
    }


    public Cursor getAllCartoons(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME_CARTOONS, null);
        Log.i("Quizdataloader get all cartoons", ""+res.getCount());
        if (res.getCount() >= 1){
            while(res.moveToNext()){
                Log.i("Cursor: ", res.getString(res.getColumnIndexOrThrow(CARTOONS_COL_1)));
                Log.i("Cursor: ", res.getString(res.getColumnIndexOrThrow(CARTOONS_COL_2)));
                Log.i("Cursor: ", res.getString(res.getColumnIndexOrThrow(CARTOONS_COL_3)));
                Log.i("Cursor: ", res.getString(res.getColumnIndexOrThrow(CARTOONS_COL_4)));
                Log.i("Cursor: ", res.getString(res.getColumnIndexOrThrow(CARTOONS_COL_5)));
                Log.i("Cursor: ", res.getString(res.getColumnIndexOrThrow(CARTOONS_COL_6)));
            }
        }
        return res;
    }

    public void getQuestionsCartoons(List<QuizItemCartoons> list) {

        List<QuizItemCartoons> temp = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME_CARTOONS, null);
        Log.i("Debug:", "Size of cartoons questions query: "+res.getCount());
        if (res.getCount() >= 1){
            while(res.moveToNext()){
                Log.i("Debug: ", "Cartoon ID: " + res.getString(res.getColumnIndexOrThrow(CARTOONS_COL_1)));
                Log.i("Debug: ", "Cartoon Question: " + res.getString(res.getColumnIndexOrThrow(CARTOONS_COL_2)));
                Log.i("Debug: ", "Cartoon Answer: " + res.getString(res.getColumnIndexOrThrow(CARTOONS_COL_3)));
                Log.i("Debug: ", "Cartoon Choice1: " + res.getString(res.getColumnIndexOrThrow(CARTOONS_COL_4)));
                Log.i("Debug: ", "Cartoon Choice2: " + res.getString(res.getColumnIndexOrThrow(CARTOONS_COL_5)));
                Log.i("Debug: ", "Cartoon Choice3: " + res.getString(res.getColumnIndexOrThrow(CARTOONS_COL_6)));
                temp.add(new QuizItemCartoons(res.getString(
                        res.getColumnIndexOrThrow(CARTOONS_COL_2)), res.getString(res.getColumnIndexOrThrow(CARTOONS_COL_3)),
                        res.getString(res.getColumnIndexOrThrow(CARTOONS_COL_4)), res.getString(res.getColumnIndexOrThrow(CARTOONS_COL_5)),
                        res.getString(res.getColumnIndexOrThrow(CARTOONS_COL_6))));
            }
        }

        Log.i("Debug:", "Size of temp in animals cartoon: " + temp.size());

        Random random = new Random();
        // Get at random and insert into our list we
        // are going to use, removes once a item has been selected
        int bounds = 9;
        for (int i = 0; i < 4; i++){
            int x = random.nextInt(bounds--);
            list.add(temp.get(x));
            temp.remove(x);
        }
    }


}
