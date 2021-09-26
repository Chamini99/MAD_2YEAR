package com.example.suwasethavaccinemanagement;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;



public class MainActivity6 extends AppCompatActivity {
    EditText username, password;
    Button btnlogin, btnsignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        username = (EditText)findViewById(R.id.edit_login_un);
        password = (EditText) findViewById(R.id.edit_login_pass);
        btnlogin = (Button) findViewById(R.id.btn_login);



        btnsignup =(Button) findViewById(R.id.btn_signup);
        btnsignup.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                Intent intent= new Intent(MainActivity6.this,MainActivity5.class);
                startActivity(intent);
            }
        });

        btnlogin =(Button) findViewById(R.id.btn_login);
        btnlogin.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                Intent intent= new Intent(MainActivity6.this,MainActivity8.class);
                startActivity(intent);
            }
        });


    }
}