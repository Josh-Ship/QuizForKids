package com.Joshua.quizforkids.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class UsersDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "QuizAppDB.db";
    private static final String TABLE_NAME = "users";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "EMAIL";
    private static final String COL_3 = "USERNAME";
    private static final String COL_4 = "PASSWORD";


    public UsersDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT, EMAIL TEXT, USERNAME TEXT, PASSWORD TEXT)" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insert(String email, String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, email);
        contentValues.put(COL_3, username);
        contentValues.put(COL_4, password);

        long res = db.insert(TABLE_NAME, null, contentValues);
        Log.i("Users Insert: ", "Result: "+res);
        if (res == -1)
            return false;
        return true;
    }

    public Cursor getAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        Log.i("UsersGetAll", ""+res.getCount());
        if (res.getCount() >= 1){
            while(res.moveToNext()){
                Log.i("Cursor: ", res.getString(res.getColumnIndexOrThrow("ID")));
                Log.i("Cursor: ", res.getString(res.getColumnIndexOrThrow("EMAIL")));
                Log.i("Cursor: ", res.getString(res.getColumnIndexOrThrow("USERNAME")));
                Log.i("Cursor: ", res.getString(res.getColumnIndexOrThrow("PASSWORD")));
            }
        }
        return res;
    }

    public boolean checkLogin(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_3 + " = '" + username + "' and " + COL_4 + " = '" + password + "' ", null);
        if (res.getCount() == 0)
            return false;
        return true;
    }

}
