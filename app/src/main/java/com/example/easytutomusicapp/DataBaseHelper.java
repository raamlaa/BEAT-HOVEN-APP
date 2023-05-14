package com.example.easytutomusicapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import android.database.Cursor;
import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "clients.db";
    public static final String TABLE_NAME = "user_table";
    public static final String COL_1 = "id";
    public static final String COL_2 = "email";
    public static final String COL_3 = "password";




    public DataBaseHelper(@Nullable Context context)
    {
        super(context, DATABASE_NAME,null, 1);
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+TABLE_NAME+" (id INTEGER PRIMARY KEY AUTOINCREMENT,email TEXT,password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    public boolean Insertdata(String email,String password){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues ContentValues = new ContentValues();
        ContentValues.put(COL_2,email);
        ContentValues.put(COL_3,password);
        long result = sqLiteDatabase.insert(TABLE_NAME,null,ContentValues);
        if (result == -1)
            return false;
        else
            return true ;
    }

    public Boolean checkEmail(String email){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from user_table where email = ?", new String[]{email});
        if(cursor.getCount() > 0) {
            return true;
        }else {
            return false;
        }
    }
    public Boolean checkEmailPassword(String email, String password){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from user_table where email = ? and password = ?", new String[]{email, password});
        if (cursor.getCount() > 0) {
            return true;
        }else {
            return false;
        }
    }

}