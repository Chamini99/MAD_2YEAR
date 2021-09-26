package com.example.suwasethavaccinemanagement;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;

public class MainActivity4 extends AppCompatActivity {
    public Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        button =(Button) findViewById(R.id.btn_update_view);
        button.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                Intent intent= new Intent(MainActivity4.this,MainActivity3.class);
                startActivity(intent);
            }
        });
    }
}