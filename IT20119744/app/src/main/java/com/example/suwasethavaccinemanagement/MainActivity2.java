package com.example.suwasethavaccinemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    EditText txtid, txtname, txtage, txtdate, txtallergies,txtdose;

    Button reserve,update,delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        txtid = (EditText) findViewById(R.id.txtid);
        txtname = (EditText) findViewById(R.id.txtname);
        txtage = (EditText) findViewById(R.id.txtage);
        txtdate = (EditText) findViewById(R.id.txtdate);
        txtallergies = (EditText) findViewById(R.id.txtallergies);
        txtdose =  (EditText) findViewById(R.id.txtdose);

        reserve = findViewById(R.id.btn_reserve_add);
        update = findViewById(R.id.btn_update);
        delete = findViewById(R.id.btn_delete);

        DBManager db = new DBManager(this);

        reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Id = txtid.getText().toString();
                String Name = txtname.getText().toString();
                String Age = txtage.getText().toString();
                String Date = txtdate .getText().toString();
                String Allergies = txtallergies.getText().toString();
                String Dose = txtdose.getText().toString();

                Boolean checkinsertdata = db.MakeReservation(Id,Name,Age,Date,Allergies,Dose);
                if (checkinsertdata==true){
                    Toast.makeText(MainActivity2.this,"Successfully inserted", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity2.this,"Failed to inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Id = txtid.getText().toString();
                String Name = txtname.getText().toString();
                String Age = txtage.getText().toString();
                String Date = txtdate .getText().toString();
                String Allergies = txtallergies.getText().toString();
                String Dose = txtdose.getText().toString();

                Boolean checkupdatedata = db.UpdateReservation(Id,Name,Age,Date,Allergies,Dose);
                if (checkupdatedata==true){
                    Toast.makeText(MainActivity2.this,"Successfully Updated", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity2.this,"Failed to Updated", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Id = txtid.getText().toString();
                Boolean checkdeletedata = db.DeleteReservation(Id);
                if (checkdeletedata==true){
                    Toast.makeText(MainActivity2.this,"Successfully Deleted", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity2.this,"Failed to Deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /*public void addReservation(View view){
        DBManager db = new DBManager(this);
        String res = db.MakeReservation(txtname.getText().toString(),txtage.getText().toString(),txtdate.getText().toString(),txtallergies.getText().toString(),txtdose.getText().toString());

        Toast.makeText(this,res,Toast.LENGTH_SHORT).show();

        txtname.setText("");
        txtage.setText("");
        txtdate.setText("");
        txtallergies.setText("");
        txtdose.setText("");

    }*/

    public void gotonextpage(View view) {
        startActivity(new Intent(this, ViewDataActivity.class));
    }
}


