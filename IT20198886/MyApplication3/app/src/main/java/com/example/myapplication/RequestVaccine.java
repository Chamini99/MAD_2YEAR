package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Calendar;

public class RequestVaccine extends AppCompatActivity {

    Button _btnInsert,edit;
    EditText _txtID, _txtDate, _txtType, _txtAmount;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    DatePickerDialog picker;
    EditText eText;
    ImageView addListing;


    //set Oncreate event
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_vaccine);

//Get the values from the textfields
        eText=(EditText) findViewById(R.id.txtRequestDate);
        eText.setInputType(InputType.TYPE_NULL);
        eText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // set date picker
                picker = new DatePickerDialog(RequestVaccine.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        eText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, year, month, day);
                picker.show();
            }
        });



        addListing=(ImageView)findViewById(R.id.addListing);
        addListing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),VaccineDetails.class);
                startActivity(i);
            }
        });






//Pass the params
        _btnInsert = (Button)findViewById(R.id.btn_RequestVaccine);
        _txtDate = (EditText) findViewById(R.id.txtRequestDate);
        _txtType = (EditText) findViewById(R.id.txtRequestType);
        _txtAmount = (EditText) findViewById(R.id.txtAmount);

        openHelper=new DatabaseHelper(this);
        _btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date=_txtDate.getText().toString();
                String type=_txtType.getText().toString();
                String amount=_txtAmount.getText().toString();
                db=openHelper.getWritableDatabase();
                insertData(date,type,amount);
            }
        });






    }
    //Insert Query to the table
    public void insertData(String date,String type,String amount){
        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseHelper.COLS_2,date);
        contentValues.put(DatabaseHelper.COLS_3,type);
        contentValues.put(DatabaseHelper.COLS_4,amount);
        long id=db.insert(DatabaseHelper.TABLE_NAME,null,contentValues);
        Toast.makeText(RequestVaccine.this, "Order Added Successfully", Toast.LENGTH_LONG).show();

        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
//restarting and loading
    }





}