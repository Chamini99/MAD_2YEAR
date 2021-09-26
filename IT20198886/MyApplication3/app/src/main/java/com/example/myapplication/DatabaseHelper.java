package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    //DB name
    public static final String DATABASE_NAME="SUWASETHA.db";
    //table name
    public static final String TABLE_NAME="REQUEST_VACCINE";

    public static final String COLS_1="ID";
    public static final String COLS_2="date";
    public static final String COLS_3="type";
    public static final String COLS_4="amount";



    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    //create table
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,date TEXT,type TEXT,amount TEXT)");
    }

    //if exist drop and create
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
         db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
         onCreate(db);
    }
}
