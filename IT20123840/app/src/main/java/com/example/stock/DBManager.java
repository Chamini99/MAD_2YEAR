package com.example.stock;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBManager extends SQLiteOpenHelper {
    private static final String dbname="SuwasethaVaccine.db";

    public DBManager(Context context) {
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="create table tbl_stock (vaccine_id integer primary key autoincrement, vaccine_name text, " +
                "quantity text, price text)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tbl_stock");
        onCreate(db);
    }

    public String addRecords(String p1, String p2, String p3){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues cv=new ContentValues();
        cv.put("vaccine_name", p1);
        cv.put("quantity", p2);
        cv.put("price", p3);

       long res=db.insert("tbl_stock", null, cv);

       if(res==-1)
           return "Failed";
       else
           return "Successfully inserted";
    }

    public String updateRecords(String p1, String p2, String p3) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("vaccine_name", p1);
        cv.put("quantity", p2);
        cv.put("price", p3);

        Cursor cursor = db.rawQuery("select * from tbl_stock where vaccine_name=?", new String[]{p1});
        if (cursor.getCount() > 0) {
            long res = db.update("tbl_stock", cv, "p1=?", new String[]{p1});

            if (res == -1)
                return "Failed";
            else
                return "Successfully inserted";
        }
        else
            return "Vaccine not found";
    }

    public Cursor viewRecords(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query="select vaccine_name, quantity from tbl_stock";
        Cursor cursor=db.rawQuery(query, null);

        return cursor;
    }
}
