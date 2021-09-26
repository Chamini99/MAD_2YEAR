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
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class UpdateRequestVaccine extends AppCompatActivity {

    private  static DatabaseHelper dbMain;
    Button edit;
    EditText _txtID,  _txtAmount;
    TextView _txtDate, _txtType;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    private static int id=0;
    DatePickerDialog picker;
    EditText eText;
    ImageView updateListing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_request_vaccine);

        updateListing=(ImageView) findViewById(R.id.UpdateListing);
        updateListing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),VaccineDetails.class);
                startActivity(i);
            }
        });



//get the data from text field

        dbMain = new DatabaseHelper(UpdateRequestVaccine.this);
        edit = (Button)findViewById(R.id.btn_RequestVaccineUpdate);
        _txtAmount = (EditText) findViewById(R.id.txtAmountup);
        _txtType = (TextView) findViewById(R.id.txtRequestTypeup);
        _txtDate = (TextView) findViewById(R.id.txtRequestDateUp);

        editData();


//update data from the table
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("amount", _txtAmount.getText().toString());

                db = dbMain.getWritableDatabase();
                long recid = db.update(DatabaseHelper.TABLE_NAME, contentValues, "ID=" +id, null);

                if (recid != -1) {
                    Toast.makeText(UpdateRequestVaccine.this, "Data Update Successfully", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);


                } else {
                    Toast.makeText(UpdateRequestVaccine.this, "Something went wrong", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


        private void editData () {
            if (getIntent().getBundleExtra("userdata") != null) {
                Bundle bundle = getIntent().getBundleExtra("userdata");
                id = bundle.getInt("ID");
                _txtAmount.setText(bundle.getString("amount"));
                _txtDate.setText(bundle.getString("date"));
                _txtType.setText(bundle.getString("type"));

            }
        }


    }
