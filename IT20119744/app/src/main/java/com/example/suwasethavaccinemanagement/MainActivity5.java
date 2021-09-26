package com.example.suwasethavaccinemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


public class MainActivity5 extends AppCompatActivity {
    EditText editname, editaddress, editphone, editemail, editpassword, editconfirmpass;
    Spinner edittype;
    Button btnsignin;

    //DBHelper UserDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        editname = findViewById(R.id.name);
        editphone = findViewById(R.id.phone);
        editemail = findViewById(R.id.email);
        editpassword = findViewById(R.id.edit_password);
        editconfirmpass = findViewById(R.id.edit_password_confirm);
        edittype = findViewById(R.id.type_spinner);
        btnsignin = findViewById(R.id.btn_confirm);

        /*UserDB = new DBHelper(this);

        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = editname.getText().toString();
                String Address = editaddress.getText().toString();
                String Phone = editphone.getText().toString();
                String Email = editemail.getText().toString();
                String Password = editpassword.getText().toString();
                String Type = edittype.getSelectedItem().toString();

                if(Name.equals("") || Address.equals("") || Phone.equals("") || Email.equals("") || Password.equals(""))
                    Toast.makeText(MainActivity5.this, "Text fields can not be empty",Toast.LENGTH_SHORT).show();
                else {
                    boolean inserted=UserDB.insertData(Name, Address, Phone, Email, Password, Type);
                    if(inserted==true){
                        Toast.makeText(MainActivity5.this, "Successfully SignUp!!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),MainActivity8.class);
                        startActivity((intent));
                    }else{
                        Toast.makeText(MainActivity5.this, "Failed to Signup", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });*/
    }
}
