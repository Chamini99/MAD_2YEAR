package com.example.stock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    EditText txt_vaccineName,txt_vaccineQty,txt_addPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        txt_vaccineName=(EditText)findViewById(R.id.txt_vaccineName) ;
        txt_vaccineQty=(EditText)findViewById(R.id.txt_vaccineQty);
        txt_addPrice=(EditText)findViewById(R.id.txt_addPrice);
    }

    public void addRecords (View view){
        DBManager db=new DBManager(this);
        String res=db.addRecords(txt_vaccineName.getText().toString(), txt_vaccineQty.getText().toString(),
                txt_addPrice.getText().toString());

        Toast.makeText(this,res,Toast.LENGTH_LONG).show();

        txt_vaccineName.setText("");
        txt_vaccineQty.setText("");
        txt_addPrice.setText("");

    }
}