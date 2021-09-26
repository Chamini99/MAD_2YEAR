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

public class PeopleDetails extends AppCompatActivity {


    DatabaseHelper dbMain;
    SQLiteDatabase sqLiteDatabase;
    ListView listView;
    String[] PeopleName;
    String[] PeopleAddress;
    String[] PeopleMobile;
    int[] PeopleID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_details);


        dbMain = new DatabaseHelper(PeopleDetails.this);
        findid();
        dis();

    }






    private void dis() {
        sqLiteDatabase = dbMain.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from People_Details", null);
        if (cursor.getCount() > 0) {
            PeopleID = new int[cursor.getCount()];
            PeopleName = new String[cursor.getCount()];
            PeopleAddress = new String[cursor.getCount()];
            PeopleMobile = new String[cursor.getCount()];

            int i = 0;
            while (cursor.moveToNext()) {
                PeopleID[i] = cursor.getInt(0);
                PeopleName[i] = cursor.getString(1);
                PeopleAddress[i] = cursor.getString(2);
                PeopleMobile[i] = cursor.getString(3);
                i++;
            }
            Custom adapter = new Custom();
            listView.setAdapter(adapter);
        }


    }

    private void findid() {
        listView = findViewById(R.id.listviewpeople);

    }


    private class Custom extends BaseAdapter {

        @Override
        public int getCount() {
            return PeopleName.length;
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
            convertView = LayoutInflater.from(PeopleDetails.this).inflate(R.layout.singledatapeople, parent, false);
            textView = convertView.findViewById(R.id.txt_peoplename);
            textdate = convertView.findViewById(R.id.txt_peoplenic);

            edit = convertView.findViewById(R.id.edit_poepledata);
            delete = convertView.findViewById(R.id.delete_peopledata);

            textView.setText(PeopleName[position]);
            textdate.setText(PeopleMobile[position]);

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("PeopleID", PeopleID[position]);
                    bundle.putString("Name", PeopleName[position]);
                    bundle.putString("MobileNo", PeopleMobile[position]);
                    Intent intent = new Intent(PeopleDetails.this, UpdateRequestVaccine.class);
                    intent.putExtra("userdata", bundle);
                    startActivity(intent);
                }
            });
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sqLiteDatabase = dbMain.getReadableDatabase();
                    long recd = sqLiteDatabase.delete("People_Details", "PeopleID=" + PeopleID[position], null);
                    if (recd != -1) {
                        Toast.makeText(PeopleDetails.this, "Deleted", Toast.LENGTH_LONG).show();
                        dis();
                    }
                }
            });


            return convertView;
        }
    }


}