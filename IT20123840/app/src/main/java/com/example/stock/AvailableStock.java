package com.example.stock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class AvailableStock extends AppCompatActivity {

    TextInputLayout vaccinaName, quantity, price;
    ArrayList<String> listItem;
    ArrayAdapter adapter;

    ListView vaccinelist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_stock);

        listItem = new ArrayList<>();

        vaccinelist=findViewById(R.id.vaccine_list);

        viewRecords();

        vaccinelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String text = vaccinelist.getItemAtPosition(i).toString();
                Toast.makeText(AvailableStock.this, ""+text, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void viewRecords() {
        DBManager db=new DBManager(this);
        Cursor cursor = db.viewRecords();

        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data to show", Toast.LENGTH_SHORT).show();
        }
        else{
            while (cursor.moveToNext()){
                listItem.add(cursor.getInt(1), cursor.getString(2));
            }

            adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItem);
            vaccinelist.setAdapter((adapter));
        }
    }

    public void btn_updateStock(View view) {
        Intent intent = new Intent(this,UpdateStock2.class);
        startActivity(intent);
    }

    public void btn_addVaccine(View view) {
        new DBManager(this);

        Intent intent = new Intent(this,MainActivity2.class);
        startActivity(intent);
    }
}