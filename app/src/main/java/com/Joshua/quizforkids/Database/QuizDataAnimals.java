package com.Joshua.quizforkids.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.Joshua.quizforkids.Animals.QuizItemAnimals;
import com.Joshua.quizforkids.Animals.QuizPageAnimals;
import com.Joshua.quizforkids.Cartoons.QuizItemCartoons;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuizDataAnimals extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "QuizApp.db";
    private static final String TABLE_NAME_ANIMALS = "animals";
    private static final String COL_ID = "ID";
    private static final String COL_NAME= "NAME";
    private static final String COL_IMGNAME = "IMGNAME";




    public QuizDataAnimals(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME_ANIMALS +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, IMGNAME TEXT)" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_ANIMALS);
        onCreate(sqLiteDatabase);
    }

    public void insertData(){
        // Populate qeustions for animals, these are hard!
        insert("Agra Cadabra", "agracadabra");
        insert("Aploparaksis Turdi", "aploparaksisturdi");
        insert("Aye Aye", "ayeaye");
        insert("Blobfish", "blobfish");
        insert("Boops Boops", "boopsboops");
        insert("Chicken Turtle", "chickenturtle");
        insert("Colon Rectum", "colonrectum");
        insert("Common Cockchafer", "commoncockchafer");
        insert("Dik Dik", "dikdik");
        insert("Fried Egg Jellyfish", "agracadabra");
    }

    public boolean insert(String name, String imageName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME, name);
        contentValues.put(COL_IMGNAME, imageName);

        long res = db.insert(TABLE_NAME_ANIMALS, null, contentValues);
        Log.i("Animals: " , res + " Name: " + name + " ImageName: " + imageName);
        if (res == -1)
            return false;
        return true;
    }

    public Cursor getAllAnimals(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME_ANIMALS, null);
        Log.i("Animals: ", "Count: "+res.getCount());
        if (res.getCount() >= 1){
            while(res.moveToNext()){
                Log.i("Animals: ", res.getString(res.getColumnIndexOrThrow(COL_ID)));
                Log.i("Animals: ", res.getString(res.getColumnIndexOrThrow(COL_NAME)));
                Log.i("Animals: ", res.getString(res.getColumnIndexOrThrow(COL_IMGNAME)));
            }
        }
        res.close();
        return res;
    }

    public void getQuestionsAnimals(List<QuizItemAnimals> list) {

        List<QuizItemAnimals> temp = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME_ANIMALS, null);
        if (res.getCount() >= 1){
            while(res.moveToNext()){
                Log.i("Cursor: ", res.getString(res.getColumnIndexOrThrow(COL_ID)));
                Log.i("Cursor: ", res.getString(res.getColumnIndexOrThrow(COL_NAME)));
                Log.i("Cursor: ", res.getString(res.getColumnIndexOrThrow(COL_IMGNAME)));
                temp.add(new QuizItemAnimals(res.getString(res.getColumnIndexOrThrow(COL_NAME)), res.getString(res.getColumnIndexOrThrow(COL_IMGNAME))));
            }
        }

        Log.i("Debug: ", "Selection Size: "+temp.size());

        Random random = new Random();
        // Get at random and insert into our list we
        // are going to use, removes once a item has been selected
        int bounds = 9;
        for (int i = 0; i < 4; i++){
            int x = random.nextInt(bounds--); // We have to shrink the bounds as the god damn list gets smaller
            Log.i("Debug: ", "Random Number :"+x);
            list.add(temp.get(x));
            temp.remove(x);
        }

        Log.i("Debug: ", "List Size: "+list.size());
        for (QuizItemAnimals animals : list){
            Log.i("Debug: ", "Animal Name: "+animals.getAnimalName());
        }
    }

}
