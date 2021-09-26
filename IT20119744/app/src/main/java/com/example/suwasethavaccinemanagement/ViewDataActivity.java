package com.example.suwasethavaccinemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ViewDataActivity extends AppCompatActivity {
    EditText entername;
    TextView displayid,displayname, diplayage, displaydate, displayallergies, displaydose;
    DBManager obj=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);


        entername = (EditText) findViewById(R.id.st1);
        displayid = (TextView) findViewById(R.id.tres);
        displayname = (TextView) findViewById(R.id.tres2);
        diplayage = (TextView) findViewById(R.id.tres3);
        displaydate = (TextView) findViewById(R.id.tres4);
        displayallergies = (TextView) findViewById(R.id.tres5);
        displaydose = (TextView) findViewById(R.id.tres6);

        /*TextView textView = findViewById(R.id.textView);

        Cursor cursor = db.ViewData();

        StringBuilder stringBuilder = new StringBuilder();

        while(cursor.moveToNext()){
            stringBuilder.append("\nAppoinment Number : "+cursor.getInt(0)+"\nName : "+cursor.getString(1)+"\nAge : "+cursor.getString(2)+"\nDate : "+cursor.getString(3)+"\nAllergies : "+cursor.getString(4)+"\nDose : "+cursor.getString(5));
        }

        textView.setText(stringBuilder);*/
    }


    public void searchdata(View view){
        obj = new DBManager(this);

        Cursor result = obj.getdata(entername.getText().toString());

        while(result.moveToNext()){
            displayid.setText(result.getString(0));
            displayname.setText(result.getString(1));
            diplayage.setText(result.getString(2));
            displaydate.setText(result.getString(3));
            displayallergies.setText(result.getString(4));
            displaydose.setText(result.getString(5));
        }

    }
}