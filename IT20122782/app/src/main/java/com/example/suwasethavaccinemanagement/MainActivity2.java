package com.example.suwasethavaccinemanagement;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.ResultSet;
import java.util.ResourceBundle;


public class MainActivity2 extends AppCompatActivity {

         EditText notenumber, date, about;
         Button newnote, update1, delete, view;
         DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        notenumber = findViewById(R.id.notenumber);
        date = findViewById(R.id.date);
        about = findViewById(R.id.about);
        newnote = findViewById(R.id.btn_newnote);
        update1 = findViewById(R.id.btn_update1);
        delete = findViewById(R.id.btn_delete);
        view = findViewById(R.id.btn_view);
        DB = new DBHelper(this);

        newnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String notenumberTXT = notenumber.getText().toString();
                String dateTXT = date.getText().toString();
                String aboutTXT = about.getText().toString();


                Boolean checkinsertdata = DB.insertuserdata(notenumberTXT, dateTXT, aboutTXT);
                if(checkinsertdata==true)
                    Toast.makeText(MainActivity2.this, "New note added", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity2.this, "New note not added", Toast.LENGTH_SHORT).show();
            }
        });

        update1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String notenumberTXT = notenumber.getText().toString();
                String dateTXT = date.getText().toString();
                String aboutTXT = about.getText().toString();


                Boolean checkupdatedata = DB.updateuserdata(notenumberTXT, dateTXT, aboutTXT);
                if(checkupdatedata==true)
                    Toast.makeText(MainActivity2.this, "Note updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity2.this, "Note not updated", Toast.LENGTH_SHORT).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String notenumberTXT = notenumber.getText().toString();



                Boolean checkdeletedata = DB.deletedata(notenumberTXT);
                if(checkdeletedata==true)
                    Toast.makeText(MainActivity2.this, "Note deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity2.this, "Note not deleted", Toast.LENGTH_SHORT).show();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if (res.getCount() == 0) {
                    Toast.makeText(MainActivity2.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("Note no " + res.getString(0) + "\n");
                    buffer.append("Date " + res.getString(1) + "\n");
                    buffer.append("About " + res.getString(2) + "\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
                builder.setCancelable(true);
                builder.setTitle("View Notes");
                builder.setMessage(buffer.toString());
                builder.show();

            }
        });




    }
}