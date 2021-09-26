package com.example.suwasethavaccinemanagement;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {


    public static final String TABLE_NAME ="Usertb";

    public static final String TABLE_Column_ID ="ID";
    public static final String TABLE_Column_Name ="NAME";
    public static final String TABLE_Column_Phone ="PHONE";
    public static final String TABLE_Column_Email ="EMAIL";
    public static final String TABLE_Column_Password ="PASSWORD";
    public static final String TABLE_Column_CONFPassword ="CONFPASSWORD";
    public static final String TABLE_Column_Type ="TYPE";

    public DBHelper(Context context) {
        super(context, "SuwasethaMedicalCenter.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String qry1 ="CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ("+TABLE_Column_ID+" INTEGER primary key AUTOINCREMENT, "+TABLE_Column_Name+" TEXT,"+TABLE_Column_Phone+" TEXT, "+TABLE_Column_Email+" TEXT, "+TABLE_Column_Password+" TEXT, "+TABLE_Column_Type+" TEXT,"+TABLE_Column_CONFPassword+" TEXT)";
        db.execSQL(qry1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate((db));
    }
}
