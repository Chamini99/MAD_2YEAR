package com.example.suwasethavaccinemanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Userdetails(notenumber TEXT primary key,  date TEXT, about TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Userdetails");

    }


    public Boolean insertuserdata(String notenumber, String date, String about){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("notenumber", notenumber);
        contentValues.put("date", date);
        contentValues.put("about", about);
        long result=DB.insert("Userdetails", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }

    }

    public Boolean updateuserdata(String notenumber, String date, String about){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("date", date);
        contentValues.put("about", about);
        Cursor cursor = DB.rawQuery("Select * from Userdetails where notenumber=?", new String[]{notenumber});
        if (cursor.getCount() > 0) {
        long result=DB.update("Userdetails", contentValues, "notenumber=?", new String[]{notenumber});
        if(result==-1){
            return false;
        }else{
            return true;
        }

    }else{
            return false;
        }
    }

    public Boolean deletedata(String notenumber){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Cursor cursor = DB.rawQuery("Select * from Userdetails where notenumber=?", new String[]{notenumber});
        if (cursor.getCount() > 0) {
            long result=DB.delete("Userdetails","notenumber=?", new String[]{notenumber});
            if(result==-1){
                return false;
            }else{
                return true;
            }

        }else{
            return false;
        }
    }

    public Cursor getdata (){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails", null);
        return cursor;
    }



}
