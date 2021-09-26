package com.example.stock;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class UpdateStock2 extends AppCompatActivity {

    EditText txtArea_vaccineType,txtArea_quantity, txt_addUPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_stock2);



        txtArea_vaccineType=(EditText)findViewById(R.id.txtArea_vaccineType) ;
        txtArea_quantity=(EditText)findViewById(R.id.txtArea_quantity);
        txt_addUPrice=(EditText)findViewById(R.id.txt_addUPrice);

    }

    public void updateRecords(View view) {
        DBManager db=new DBManager(this);

        String res=db.updateRecords(txtArea_vaccineType.getText().toString(), txtArea_quantity.getText().toString(),
                txt_addUPrice.getText().toString());

        Toast.makeText(this,res,Toast.LENGTH_LONG).show();

        txtArea_vaccineType.setText("");
        txtArea_quantity.setText("");
        txt_addUPrice.setText("");
    }

}