package com.example.suwasethavaccinemanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBManager extends SQLiteOpenHelper {

    public DBManager(Context context) {
        super(context, "SuwasethaMedicalCenter.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String qry ="create Table ReservationDetails(ID INTEGER primary key,NAME TEXT, AGE TEXT, DATE TEXT, ALLERGIES TEXT, DOSE TEXT)";
        db.execSQL(qry);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists ReservationDetails ");
        onCreate(db);
    }

    public boolean MakeReservation(String rec1,String rec2,String rec3,String rec4,String rec5, String rec6){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv= new ContentValues();

        cv.put("ID",rec1);
        cv.put("NAME",rec2);
        cv.put("AGE",rec3);
        cv.put("DATE",rec4);
        cv.put("ALLERGIES",rec5);
        cv.put("DOSE",rec6);

        long res = db.insert("ReservationDetails",null,  cv);
        if(res==-1)
            return false;
        else
            return true;
    }

    public boolean UpdateReservation(String rec1, String rec2, String rec3, String rec4, String rec5,String rec6){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv= new ContentValues();

        cv.put("ID",rec1);
        cv.put("NAME",rec2);
        cv.put("AGE",rec3);
        cv.put("DATE",rec4);
        cv.put("ALLERGIES",rec5);
        cv.put("DOSE",rec6);

        Cursor cursor = db.rawQuery("select * from ReservationDetails where ID=?",new String[]{rec1});
        if(cursor.getCount()>0) {

            long res = db.update("ReservationDetails", cv, "ID=?", new String[]{rec1});
            if (res == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }

    }

    public boolean DeleteReservation(String rec1) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from ReservationDetails where ID=?", new String[]{rec1});
        if (cursor.getCount() > 0) {

            long res = db.delete("ReservationDetails", "ID=?", new String[]{rec1});
            if (res == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Cursor getReservationData() {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from ReservationDetails",null);
        return cursor;
    }

    public Cursor getdata(String NAME){
        SQLiteDatabase db = this.getWritableDatabase();

        String qry="select * from ReservationDetails where NAME="+NAME;
        Cursor crs = db.rawQuery(qry,null);

        return crs;
    }
}
