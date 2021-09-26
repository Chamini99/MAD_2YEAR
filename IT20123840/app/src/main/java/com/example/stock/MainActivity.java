package com.example.stock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btn_availableStock(View view) {
        Intent intent = new Intent(this,AvailableStock.class);
        startActivity(intent);
    }

    public void btn_requestedStock(View view) {
        Intent intent = new Intent(this,Orders.class);
        startActivity(intent);
    }
}