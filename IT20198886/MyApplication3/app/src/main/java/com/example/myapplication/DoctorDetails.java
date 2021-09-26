package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DoctorDetails extends AppCompatActivity {


    DatabaseHelper dbMain;
    SQLiteDatabase sqLiteDatabase;
    ListView listView;
    String[] DocName;
    String[] DocHospital;
    String[] DocMobile;
    int[] DocID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);


        dbMain = new DatabaseHelper(DoctorDetails.this);
        findid();
        dis();

    }





//select all doctors
    private void dis() {
        sqLiteDatabase = dbMain.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from Doctor_Details", null);
        if (cursor.getCount() > 0) {
            DocID = new int[cursor.getCount()];
            DocName = new String[cursor.getCount()];
            DocHospital = new String[cursor.getCount()];
            DocMobile = new String[cursor.getCount()];

            int i = 0;
            while (cursor.moveToNext()) {
                DocID[i] = cursor.getInt(0);
                DocName[i] = cursor.getString(1);
                DocHospital[i] = cursor.getString(2);
                DocMobile[i] = cursor.getString(3);
                i++;
            }
            Custom adapter = new Custom();
            listView.setAdapter(adapter);
        }


    }

    private void findid() {
        listView = findViewById(R.id.listviewdoc);

    }

//send data to view
    private class Custom extends BaseAdapter {

        @Override
        public int getCount() {
            return DocName.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView,textdate,txtQty;
            ImageView edit, delete;
            convertView = LayoutInflater.from(DoctorDetails.this).inflate(R.layout.singledatadoctor, parent, false);
            textView = convertView.findViewById(R.id.txt_docname);
            textdate = convertView.findViewById(R.id.txt_docHospital);

            edit = convertView.findViewById(R.id.edit_datadoc);
            delete = convertView.findViewById(R.id.delete_datadoc);

            textView.setText(DocName[position]);
            textdate.setText(DocHospital[position]);

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("DoctorID", DocID[position]);
                    bundle.putString("Name", DocName[position]);
                    bundle.putString("Hospital", DocHospital[position]);
                    Intent intent = new Intent(DoctorDetails.this, UpdateRequestVaccine.class);
                    intent.putExtra("userdata", bundle);
                    startActivity(intent);
                }
            });
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sqLiteDatabase = dbMain.getReadableDatabase();
                    long recd = sqLiteDatabase.delete("Doctor_Details", "DoctorID=" + DocID[position], null);
                    if (recd != -1) {
                        Toast.makeText(DoctorDetails.this, "Deleted", Toast.LENGTH_LONG).show();
                        dis();
                    }
                }
            });


            return convertView;
        }
    }


}