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

public class VaccineDetails extends AppCompatActivity {


    DatabaseHelper dbMain;
    SQLiteDatabase sqLiteDatabase;
    ListView listView;
    String[] date;
    String[] type;
    String[] amount;
ImageView detailAddList;
    int[] id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_details);


        detailAddList=(ImageView)findViewById(R.id.DetailAddList);

        detailAddList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),RequestVaccine.class);
                startActivity(i);
            }
        });








        dbMain = new DatabaseHelper(VaccineDetails.this);
        findid();
        dis();

    }






    private void dis() {
        sqLiteDatabase = dbMain.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from REQUEST_VACCINE", null);
        if (cursor.getCount() > 0) {
            id = new int[cursor.getCount()];
            date = new String[cursor.getCount()];
            type = new String[cursor.getCount()];
            amount = new String[cursor.getCount()];

            int i = 0;
            while (cursor.moveToNext()) {
                id[i] = cursor.getInt(0);
                date[i] = cursor.getString(1);
                type[i] = cursor.getString(2);
                amount[i] = cursor.getString(3);
                i++;
            }
            Custom adapter = new Custom();
            listView.setAdapter(adapter);
        }


    }

    private void findid() {
        listView = findViewById(R.id.listview);

    }


    private class Custom extends BaseAdapter {

        @Override
        public int getCount() {
            return date.length;
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
            convertView = LayoutInflater.from(VaccineDetails.this).inflate(R.layout.singledatavaccine, parent, false);
            textView = convertView.findViewById(R.id.txt_name);
            textdate = convertView.findViewById(R.id.txt_date);
            txtQty = convertView.findViewById(R.id.txtQty);

            edit = convertView.findViewById(R.id.edit_data);
            delete = convertView.findViewById(R.id.delete_data);

            textView.setText(type[position]);
            textdate.setText(date[position]);
            txtQty.setText(amount[position]);

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("ID", id[position]);
                    bundle.putString("date", date[position]);
                    bundle.putString("type", type[position]);
                    bundle.putString("amount", amount[position]);
                    Intent intent = new Intent(VaccineDetails.this, UpdateRequestVaccine.class);
                    intent.putExtra("userdata", bundle);
                    startActivity(intent);
                }
            });
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sqLiteDatabase = dbMain.getReadableDatabase();
                    long recd = sqLiteDatabase.delete("REQUEST_VACCINE", "ID=" + id[position], null);
                    if (recd != -1) {
                        Toast.makeText(VaccineDetails.this, "Deleted", Toast.LENGTH_LONG).show();
                        dis();
                    }
                }
            });


            return convertView;
        }
    }


}